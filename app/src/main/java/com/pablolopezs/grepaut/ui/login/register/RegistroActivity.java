package com.pablolopezs.grepaut.ui.login.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pablolopezs.grepaut.R;
import com.pablolopezs.grepaut.data.model.Usuario;
import com.pablolopezs.grepaut.data.repositories.UsuarioRepositories;
import com.pablolopezs.grepaut.ui.login.LoginActivity;

import java.util.regex.Pattern;

/**Activity que controla los registros de nuevos usuaraios*/
public class RegistroActivity extends AppCompatActivity {

    private Button btnguardar;
    private TextInputLayout tilemail;
    private TextInputLayout tilnombre;
    private TextInputLayout tilpassword;
    private TextInputLayout tilConfirmPassword;
    private TextInputLayout tilNombreEmpresa;
    private TextInputEditText tedemail;
    private TextInputEditText tednombre;
    private TextInputEditText tedpassword;
    private TextInputEditText tedconfirmpassword;
    //private TextInputEditText tednombreempresa;
    private RadioButton rbAdministrador;
    private RadioButton rbUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//Para evitar que concretamente esta activity se gire
        setContentView(R.layout.activity_registro);

        //Enlace a componentes de Interfaz
        btnguardar = findViewById(R.id.btnguardar);
        tilemail = findViewById(R.id.tilemail);
        tilnombre = findViewById(R.id.tilnombre);
        tilpassword = findViewById(R.id.tilpassword);
        tilConfirmPassword = findViewById(R.id.tilConfirmPassword);
        //tilNombreEmpresa = findViewById(R.id.tilNombreEmpresa);
        tedemail = findViewById(R.id.tedemail);
        tednombre = findViewById(R.id.tednombre);
        tedpassword = findViewById(R.id.tedpassword);
        tedconfirmpassword = findViewById(R.id.tedconfirmpassword);
        //tednombreempresa = findViewById(R.id.tednombreempresa);
        rbAdministrador = findViewById(R.id.rbAdministrador);
        rbUsuario = findViewById(R.id.rbUsuario);


        /**
         * Guardamos los datos y Nos dirigimos a la Activity de Login*/
        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validar()) {
                    //Guardar datos
                    UsuarioRepositories.getInstance().getList().add(getObjeto());
                    Toast.makeText(getApplicationContext(), "¡Registro realizado con exito!.", Toast.LENGTH_SHORT).show();
                    //Dirigirse al Login de nuevo
                    Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();//Esta activity quedara finalizada al guaradar, ya que no se volvera esta de nuevo(como norma general).
                }
            }
        });

    }

    //Valida los datos introducidos, si es correcto devuelve true
    private boolean validar(){
        if(TextUtils.isEmpty(tedemail.getText()))
        {
            Toast.makeText(getApplicationContext(), "El email no puede estar vacio", Toast.LENGTH_SHORT).show();
            return  false;
        }
        if(TextUtils.isEmpty(tednombre.getText()))
        {
            Toast.makeText(getApplicationContext(), "El nombre no puede estar vacio", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(tedpassword.getText()) || TextUtils.isEmpty(tedconfirmpassword.getText()))
        {
            Toast.makeText(getApplicationContext(), "El campo contraseña y el confirmar contraseña no pueden ser vacios", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!tedpassword.getText().toString().equals(tedconfirmpassword.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Las contraseñas deben coincidir 1º"+tedpassword.getText().toString() +" 2º "+tedconfirmpassword.getText().toString(), Toast.LENGTH_SHORT).show();
            return false;
        }
        Pattern patron = Patterns.EMAIL_ADDRESS;
        if(!patron.matcher(tedemail.getText().toString()).matches())
        {
            Toast.makeText(getApplicationContext(), "El correo debe cumplir el patron ejemplo@dtudominio.com", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!rbAdministrador.isChecked() && !rbUsuario.isChecked()){
            Toast.makeText(getApplicationContext(), "Debes selecionar un rol [Administrador o Usuario]", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //Recoge los datos del nuevo usuario
    public Usuario getObjeto(){
        Usuario u = new Usuario();
        u.setNombre(tednombre.getText().toString());
        u.setEmail(tedemail.getText().toString());
        u.setPassword(tedpassword.getText().toString());
        if(rbAdministrador.isChecked()) {
            u.setTipoUsuario("Admin");
            u.setTienePermiso(true);
        }
        if(rbUsuario.isChecked()){//Los de tipo usuario, cuando se crean por defecto no tienen permiso para acceder
            u.setTipoUsuario("User");
            u.setTienePermiso(false);
        }
        return u;
    }
}

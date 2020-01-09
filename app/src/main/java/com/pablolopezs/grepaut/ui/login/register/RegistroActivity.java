package com.pablolopezs.grepaut.ui.login.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pablolopezs.grepaut.R;
import com.pablolopezs.grepaut.ui.login.LoginActivity;

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
    private TextInputEditText tednombreempresa;
    private RadioButton rbAdministrador;
    private RadioButton rbUsuario;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//Para evitar que concretamente esta activity se gire
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //Enlace a componentes de Interfaz
        btnguardar = findViewById(R.id.btnguardar);
        tilemail = findViewById(R.id.tilemail);
        tilnombre = findViewById(R.id.tilnombre);
        tilpassword = findViewById(R.id.tilpassword);
        tilConfirmPassword = findViewById(R.id.tilConfirmPassword);
        tilNombreEmpresa = findViewById(R.id.tilNombreEmpresa);
        tedemail = findViewById(R.id.tedemail);
        tednombre = findViewById(R.id.tednombre);
        tedpassword = findViewById(R.id.tedpassword);
        tedconfirmpassword = findViewById(R.id.tedconfirmpassword);
        tednombreempresa = findViewById(R.id.tednombreempresa);
        rbAdministrador = findViewById(R.id.rbAdministrador);
        rbUsuario = findViewById(R.id.rbUsuario);


        /**
         * Guardamos los datos y Nos dirigimos a la Activity de Login*/
        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Guardar datos

                //Dirigirse al Login de nuevo
                Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();//Esta activity quedara finalizada al guaradar, ya que no se volvera esta de nuevo(como norma general).
            }
        });
    }
}

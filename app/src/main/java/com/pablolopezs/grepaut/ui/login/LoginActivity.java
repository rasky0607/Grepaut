package com.pablolopezs.grepaut.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pablolopezs.grepaut.R;
import com.pablolopezs.grepaut.data.model.Usuario;
import com.pablolopezs.grepaut.data.repositories.UsuarioRepositories;
import com.pablolopezs.grepaut.ui.MainActivity.MainActivity;
import com.pablolopezs.grepaut.ui.login.register.RegistroActivity;

public class LoginActivity extends AppCompatActivity {


       private TextInputLayout tilemail;
       private TextInputLayout tilpassword;
       private TextInputEditText tedemail;
       private TextInputEditText tedpassword;
       private Button btnIniciasSesion;
       private Button btnregistro;
       private CheckBox chrecordar;
       private ImageButton btnGoogle;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Enlace a componentes de Interfaz
        btnIniciasSesion=findViewById(R.id.btnIniciarSesion);
        btnregistro=findViewById(R.id.btnregistro);
        chrecordar= findViewById(R.id.chrecordar);//TODO Si este componente esta a marcado, se guardara el email y password en el dispositivo e iniciara sesion de forma automatica.
        btnGoogle=findViewById(R.id.btnGoogle);
        tilemail=findViewById(R.id.tilemail);
        tilpassword=findViewById(R.id.tilpassword);
        tedemail=findViewById(R.id.tedemail);
        tedpassword=findViewById(R.id.tedpassword);
        //Para testear
        tedemail.setText("email1@gmail.com");



        /**
         * Guardamos el usuario que inicio sesion y nos dirigimos a la Activity del menu Navegation Drawer llamada--> (MainActivity)*/
        btnIniciasSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Guardamos el usuario
                //TODO si el usuario tiene permiso a true puede ver los registros(si es administrador lo tendra a true desde la creaciom, si es Usuario a false)
                //Usuario miUsuario = new Usuario(tedemail.getText().toString(),tedpassword.getText().toString(),null,null,true,null);
                if(UsuarioRepositories.getInstance().buscarUsuario(tedemail.getText().toString(),tedpassword.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Usuario correcto", Toast.LENGTH_SHORT).show();
                    //Nos dirigimos a la Activity del menu Navegation Drawer llamada--> (MainActivity)
                   // MainActivity.nombreUsuario=UsuarioRepositories.getInstance().getUsuario(tedemail.getText().toString(),tedpassword.getText().toString()).getNombre();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();//Terminamos esta activity, ya que una vez dentro no va voler a Login
                }
                else
                    Toast.makeText(getApplicationContext(), "Usuario o password incorrecto o no tiene permisos, contacta con el administrador", Toast.LENGTH_LONG).show();
                Log.d("PRUEBA","Datos -> "+tedemail.getText().toString()+" "+tedpassword.getText().toString());


            }
        });


        /**
         * Nos dirigimos a la Activity de registro*/
        btnregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(intent);//A diferencia del caso anterior, no finalizamos la activity login , ya que una vez registrado se le devolvera al login.
            }
        });
    }


}

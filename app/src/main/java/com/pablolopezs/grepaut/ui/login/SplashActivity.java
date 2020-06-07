package com.pablolopezs.grepaut.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.pablolopezs.grepaut.R;

public class SplashActivity extends AppCompatActivity {

    private  static  final long WAIT_TIME=3000;//tiempo de espera
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }


    /*Este objeto  ejecuta el codigo  del metoro  run fuera del hilo de la IU (Interfaz grafica)*/
    @Override
    protected void onStart() {
        super.onStart();
        //Manejador
        Handler handler = new Handler();
        //Objeto de la interfaz Runnable  para hilos
        Runnable runnable= new Runnable() {
            @Override
            public void run() {
                InitLogin();

            }
        };

        //Metodo del manejador que provoca un retardo
        handler.postDelayed(runnable,WAIT_TIME);

    }
    //endregion

     /* Este metodo arranca la siguiente activity*/
    private void InitLogin() {
        Intent intent=new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        /*
         * Esta activida  va ejecutar OnDestroy al llamar a finish()
         * En este caso lo llamamos ya que esta ventana de splash, no nos hace falta para nada mas
         * despues de iniciar la App no volveremos a ella.
         * */
        finish();
    }
}

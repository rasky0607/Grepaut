package com.pablolopezs.grepaut.ui.ManagerActivity;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.pablolopezs.grepaut.R;
import com.pablolopezs.grepaut.ui.ManagerActivity.cliente.FragmentCliente;
import com.pablolopezs.grepaut.ui.ManagerActivity.factura.FragmentFactura;
import com.pablolopezs.grepaut.ui.ManagerActivity.reparacion.FragmentReparacion;
import com.pablolopezs.grepaut.ui.ManagerActivity.servicio.FragmentServicio;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Toast;

import java.nio.file.OpenOption;

public class ManagerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private  DrawerLayout drawer=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        Toolbar toolbar = findViewById(R.id.toolbar);//enlazamos la barra de la toolbar
        FloatingActionButton fab = findViewById(R.id.fab);
        //Componentes para enlazar la barra de navegacion y cada una de sus opciones.
        //Final DrawerLayout drawer drawer = findViewById(R.id.drawer_layout);
         drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        setSupportActionBar(toolbar);//cargamos la toolbar

        /*Consejo de Niko a la solucion que tenia aplicada yo respecto al inflado de los fragment de forma manual en el (fragment_content_manager),
        Inflando el boton que desplega el menu lateral (conocido como hamburguer)*/
        /*----------------------------------------------------------------------*/
        ActionBar supportActionBar=getSupportActionBar();
        if(supportActionBar!=null){
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            supportActionBar.setDisplayHomeAsUpEnabled(true);

        }
        /*----------------------------------------------------------------------*/

        /*Evento Click del boton superior izquierdo para desplegar el menu lateral(conocido como boton hamburguer)*/
        /*----------------------------------------------------------------------*/
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
        /*----------------------------------------------------------------------*/

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Toast.makeText(ManagerActivity.this,"Vista añadir aun sin implementar",Toast.LENGTH_SHORT).show();
            }
        });


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_clientes, R.id.nav_reparaciones, R.id.nav_servicios,
                R.id.nav_facturas, R.id.nav_compartir).setDrawerLayout(drawer).build();

        //NavController Obtine de (mobile_navigation.xml) el primer fragmen que va arrancar la activity en este caso es el fragmentReparacion
        NavController navController = Navigation.findNavController(this, R.id.nav_contenedor_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        /*Inicializamos el listener para onNavigationItemSelected(@NonNull MenuItem item).
         Tambien podriamos haberlo realizado con navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() */
        navigationView.setNavigationItemSelectedListener(this);

        //Inicializamos el fragmento con el que se va ha iniciar la actividad
        /*----------------------------------------------------------------------*/
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.nav_contenedor_fragment, new FragmentReparacion());
        fragmentTransaction.commit();
        setTitle(R.string.menu_reparaciones);
        /*----------------------------------------------------------------------*/



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //Infla la barra de accion o menu y añade los componentes que habra en este
        getMenuInflater().inflate(R.menu.manager, menu);
        return true;
    }


    //Para la actionbar donde encontramos el menu despleagable a la derecha
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    /*Manejo de menu lateral (navigation Drawer) donde se indicara la logica, o las tareas a llevar a cabo segun la opcion seleccionada*/
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();

        switch ((id)) {
            case R.id.nav_reparaciones:
                Log.d("PRUEBA", "PULSASTE REPARCIONES");
                fragmentTransaction.replace(R.id.nav_contenedor_fragment, new FragmentReparacion());
                fragmentTransaction.commit();
                setTitle(R.string.menu_reparaciones);


                break;

            case R.id.nav_clientes:
                Log.d("PRUEBA", "PULSASTE CLIENTES");
                fragmentTransaction.replace(R.id.nav_contenedor_fragment,new FragmentCliente());
                fragmentTransaction.commit();
                setTitle(R.string.menu_clientes);

                break;

            case R.id.nav_servicios:
                Log.d("PRUEBA", "PULSASTE SERVICIOS");
                fragmentTransaction.replace(R.id.nav_contenedor_fragment,new FragmentServicio());
                fragmentTransaction.commit();
                setTitle(R.string.menu_servicios);

                break;

            case R.id.nav_facturas:
                Log.d("PRUEBA", "PULSASTE FACTURAS");
                fragmentTransaction.replace(R.id.nav_contenedor_fragment,new FragmentFactura());
                fragmentTransaction.commit();
                setTitle(R.string.menu_facturas);


                break;
            case R.id.nav_compartir:

                Toast.makeText(ManagerActivity.this,"Pulsaste compartir!",Toast.LENGTH_SHORT).show();

                break;
        }



        //Creeamos el navegador desplegable
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}


//Metodos posiblemente incesarios ( o candidatos a limpieza)

/*  //Maneja el click en el boton superior izquierdo(conocido como hamburguer) que desplega la barra de navegacion lateral //Todo este metodo aun no termina de quedarme claro
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_contenedor_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    } */
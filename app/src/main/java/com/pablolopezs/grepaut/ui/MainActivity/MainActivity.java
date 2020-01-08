package com.pablolopezs.grepaut.ui.MainActivity;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.pablolopezs.grepaut.R;
import com.pablolopezs.grepaut.data.model.Reparacion;
import com.pablolopezs.grepaut.ui.MainActivity.cliente.ClienteListView;
import com.pablolopezs.grepaut.ui.MainActivity.factura.FacturaListView;
import com.pablolopezs.grepaut.ui.MainActivity.reparacion.ReparacionDetailView;
import com.pablolopezs.grepaut.ui.MainActivity.reparacion.ReparacionListPresenter;
import com.pablolopezs.grepaut.ui.MainActivity.reparacion.ReparacionListView;
import com.pablolopezs.grepaut.ui.MainActivity.servicio.ServicioListView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ReparacionListView.clickVerReparacionListener {

    private AppBarConfiguration mAppBarConfiguration;
    private  DrawerLayout drawer=null;
    private NavigationView navigationView;
    private  ReparacionListView fragmentReparacionListView;
    private ReparacionListPresenter presenterReparacion;
    private ReparacionDetailView fragmentReparacionDetailView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);//enlazamos la barra de la toolbar
        FloatingActionButton fab = findViewById(R.id.fab);
        //Componentes para enlazar la barra de navegacion y cada una de sus opciones.
        //Final DrawerLayout drawer drawer = findViewById(R.id.drawer_layout);
         drawer = findViewById(R.id.drawer_layout);
         navigationView = findViewById(R.id.nav_view);

        setSupportActionBar(toolbar);// Seteamos o cargamos la toolbar

        /*Consejo de Niko a la solucion que tenia aplicada yo respecto al inflado de los fragment de forma manual en el (fragment_content_main),
        Inflando el boton que desplega el menu lateral (conocido como hamburguer)*/
        /*----------------------------------------------------------------------*/
       /* ActionBar supportActionBar=getSupportActionBar();
        if(supportActionBar!=null){
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            supportActionBar.setDisplayHomeAsUpEnabled(true);

        }*/
        /*----------------------------------------------------------------------*/

        /*Evento Click del boton superior izquierdo  de la toolbar para desplegar el menu lateral(conocido como boton hamburguer)*/
        /*----------------------------------------------------------------------*/
       /* toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });*/
        /*----------------------------------------------------------------------*/

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Toast.makeText(MainActivity.this,"Vista añadir aun sin implementar",Toast.LENGTH_SHORT).show();
            }
        });


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_clientes, R.id.nav_reparaciones, R.id.nav_servicios,
                R.id.nav_facturas, R.id.nav_compartir).setDrawerLayout(drawer).build();

        /*Inicializamos el listener para onNavigationItemSelected(@NonNull MenuItem item).
         Tambien podriamos haberlo realizado con navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() */
        navigationView.setNavigationItemSelectedListener(this);

        //Inicializamos el fragmento que va mostrar el navigationDrawer por defecto al iniciar su actividad
        /*----------------------------------------------------------------------*/

        Inicializar();

        /*----------------------------------------------------------------------*/



    }

    /*Inicializamos el fragmento*/
    private void Inicializar(){
        fragmentReparacionListView =(ReparacionListView)getSupportFragmentManager().findFragmentByTag(ReparacionListView.TAG);
        if(fragmentReparacionListView==null)
        {
            fragmentReparacionListView= ReparacionListView.newInstance(null);
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_contenedor_fragment,fragmentReparacionListView,ReparacionListView.TAG).commit();

        }
        presenterReparacion= new ReparacionListPresenter(fragmentReparacionListView);
        fragmentReparacionListView.setPresenter(presenterReparacion);

        Log.d("PRUEBA", "PULSASTE REPARCIONES");
                 /*fragmentTransaction.replace(R.id.nav_contenedor_fragment, new ReparacionListView());
                fragmentTransaction.commit();*/
        setTitle(R.string.menu_reparaciones);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //Infla la barra de accion o menu y añade los componentes que habra en este indicados en el XML menu_de_app_bar.xml
        getMenuInflater().inflate(R.menu.menu_de_app_bar, menu);
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
                fragmentReparacionListView =(ReparacionListView)getSupportFragmentManager().findFragmentByTag(ReparacionListView.TAG);
                if(fragmentReparacionListView==null)
                {
                    fragmentReparacionListView= ReparacionListView.newInstance(null);
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_contenedor_fragment,fragmentReparacionListView,ReparacionListView.TAG).commit();
                }
                presenterReparacion= new ReparacionListPresenter(fragmentReparacionListView);
                fragmentReparacionListView.setPresenter(presenterReparacion);
               Log.d("PRUEBA", "PULSASTE REPARCIONES");
                setTitle(R.string.menu_reparaciones);
                break;

            case R.id.nav_clientes:
                Log.d("PRUEBA", "PULSASTE CLIENTES");
                fragmentTransaction.replace(R.id.nav_contenedor_fragment,new ClienteListView());
                fragmentTransaction.commit();
                setTitle(R.string.menu_clientes);
                break;

            case R.id.nav_servicios:
                Log.d("PRUEBA", "PULSASTE SERVICIOS");
                fragmentTransaction.replace(R.id.nav_contenedor_fragment,new ServicioListView());
                fragmentTransaction.commit();
                setTitle(R.string.menu_servicios);
                break;

            case R.id.nav_facturas:
                Log.d("PRUEBA", "PULSASTE FACTURAS");
                fragmentTransaction.replace(R.id.nav_contenedor_fragment,new FacturaListView());
                fragmentTransaction.commit();
                setTitle(R.string.menu_facturas);
                break;
            case R.id.nav_compartir:
                Toast.makeText(MainActivity.this,"Pulsaste compartir!",Toast.LENGTH_SHORT).show();
                break;
        }



        //Creeamos el navegador desplegable
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    //Cuando se hace click sobre la lista de reparaciones para abrir una nueva vista con todos los datos de esta
    @Override
    public void clickVerReparacionListener() {

        Log.d("CAMBIO","ENTRO a cambiar la vista");
        //Bundle b = null;
        fragmentReparacionDetailView = (ReparacionDetailView) getSupportFragmentManager().findFragmentByTag(ReparacionDetailView.TAG);
        fragmentReparacionDetailView= new ReparacionDetailView();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_contenedor_fragment,fragmentReparacionDetailView,ReparacionDetailView.TAG).addToBackStack(null)
                .commit();
        setTitle("Detalles de la Reparación.");
        Log.d("CAMBIO","SE creo la nueva VISTA??");

    }
}

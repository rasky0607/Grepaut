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
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.navigation.NavigationView;
import com.pablolopezs.grepaut.R;
import com.pablolopezs.grepaut.ui.cliente.ClienteListView;
import com.pablolopezs.grepaut.ui.factura.FacturaListView;
import com.pablolopezs.grepaut.ui.reparacion.ReparacionAdd;
import com.pablolopezs.grepaut.ui.reparacion.ReparacionDetailListView;
import com.pablolopezs.grepaut.ui.reparacion.ReparacionListPresenter;
import com.pablolopezs.grepaut.ui.reparacion.ReparacionListView;
import com.pablolopezs.grepaut.ui.servicio.ServicioListView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Toast;

/**Clase que gestiona las opciones del nuestra barra
 * de navegacion Navigation Drawer y el cocntrol de los fragmnet que se crean o destruyen*/
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ReparacionListView.clickVerReparacionListener {

    private AppBarConfiguration mAppBarConfiguration;
    private FloatingActionButton fabadd;
    private  DrawerLayout drawer=null;
    private NavigationView navigationView;
    private  ReparacionListView fragmentReparacionListView;
    private ReparacionListPresenter presenterReparacion;
    private ReparacionDetailListView fragmentReparacionDetailView;
    private ReparacionAdd fragmentoReparacionAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);//enlazamos la barra de la toolbar
        fabadd =(FloatingActionButton) findViewById(R.id.fabadd);
        //Componentes para enlazar la barra de navegacion y cada una de sus opciones.
        //Final DrawerLayout drawer drawer = findViewById(R.id.drawer_layout);
         drawer = findViewById(R.id.drawer_layout);
         navigationView = findViewById(R.id.nav_view);

        setSupportActionBar(toolbar);// Seteamos/Cargamos la toolbar

//region Basura que puede que podamos eliminar más tarde
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
//endregion

        /**Abre el fragmento que permite añadir un elemento a la lista, segun en el listado que estamos en este momento*/
        fabadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Toast.makeText(MainActivity.this,"Vista añadir aun sin implementar",Toast.LENGTH_SHORT).show();
                String titulo = getTitle().toString();
                switch (titulo)
                {
                    case "Reparaciones":
                        //Abrimos fragment añadir de Reparaciones
                        fragmentoReparacionAdd = (ReparacionAdd) getSupportFragmentManager().findFragmentByTag(fragmentoReparacionAdd.TAG);
                        fragmentoReparacionAdd= new ReparacionAdd();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.nav_contenedor_fragment,fragmentReparacionDetailView, ReparacionDetailListView.TAG).addToBackStack(null)
                                .commit();

                        //presenterReparacion= new ReparacionListPresenter(fragmentReparacionDetailView);
                        //fragmentoReparacionAdd.setPresenter(presenterReparacion);
                        break;
                    case "Clientes":
                        //Abrimos fragment añadir de Cliente
                        break;
                    case "Servicios":
                        //Abrimos fragment añadir de Servicios
                        break;
                    case "Facturas":
                        //Abrimos fragment añadir de Facturas
                        break;

                }
                ocultarMostrarFloatinButtom();
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

    private void ocultarMostrarFloatinButtom(){
        String titulo = getTitle().toString();
        switch (titulo)
        {
            case "Reparaciones":
               fabadd.show();
                break;
            case "Clientes":
                fabadd.show();
                break;
            case "Servicios":
                fabadd.show();
                break;
            case "Facturas":
                fabadd.show();
                break;
             default:
                 fabadd.hide();
                 break;

        }

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
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_contenedor_fragment,fragmentReparacionListView,ReparacionListView.TAG).commit();
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
        ocultarMostrarFloatinButtom();

        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    //Cuando se hace click sobre la lista de reparaciones para abrir una nueva vista con todos los datos de esta
    @Override
    public void clickVerReparacionListener() {

        //TODO PENDIENTE DE PASAR LA LISTA AL ADAPTER DE ReparacionDetailListAdapter
        Log.d("CAMBIO","ENTRO a cambiar la vista");
        fragmentReparacionDetailView = (ReparacionDetailListView) getSupportFragmentManager().findFragmentByTag(ReparacionDetailListView.TAG);
        fragmentReparacionDetailView= new ReparacionDetailListView();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_contenedor_fragment,fragmentReparacionDetailView, ReparacionDetailListView.TAG).addToBackStack(null)
                .commit();
        presenterReparacion= new ReparacionListPresenter(fragmentReparacionDetailView);
        fragmentReparacionDetailView.setPresenter(presenterReparacion);

        setTitle("Detalles de la Reparación.");
        ocultarMostrarFloatinButtom();


    }

}

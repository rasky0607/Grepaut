package com.pablolopezs.grepaut.ui.MainActivity;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;

import com.google.android.material.navigation.NavigationView;
import com.pablolopezs.grepaut.R;
import com.pablolopezs.grepaut.data.model.Cliente;
import com.pablolopezs.grepaut.data.model.Servicio;
import com.pablolopezs.grepaut.ui.cliente.ClienteAddyEditPresenter;
import com.pablolopezs.grepaut.ui.cliente.ClienteAddyEditView;
import com.pablolopezs.grepaut.ui.cliente.ClienteListPresenter;
import com.pablolopezs.grepaut.ui.cliente.ClienteListView;
import com.pablolopezs.grepaut.ui.factura.FacturaListView;
import com.pablolopezs.grepaut.ui.reparacion.ReparacionAddPresenter;
import com.pablolopezs.grepaut.ui.reparacion.ReparacionAddView;
import com.pablolopezs.grepaut.ui.reparacion.ReparacionDetailListView;
import com.pablolopezs.grepaut.ui.reparacion.ReparacionListPresenter;
import com.pablolopezs.grepaut.ui.reparacion.ReparacionListView;
import com.pablolopezs.grepaut.ui.servicio.ServicioAddEditPresenter;
import com.pablolopezs.grepaut.ui.servicio.ServicioAddyEditView;
import com.pablolopezs.grepaut.ui.servicio.ServicioListPresenter;
import com.pablolopezs.grepaut.ui.servicio.ServicioListView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Toast;

/**Clase que gestiona las opciones del nuestra barra
 * de navegacion Navigation Drawer y el cocntrol de los fragmnet que se crean o destruyen*/
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ReparacionListView.clickVerReparacionListener, ServicioListView.manipularDatosServicioAddOEdit, ClienteListView.manipularDatosClienteAddOEdit {

    //private AppBarConfiguration mAppBarConfiguration;
    private FloatingActionButton fabadd;
    private  DrawerLayout drawer=null;
    private NavigationView navigationView;
    private ReparacionListView fragmentReparacionListView;
    private ReparacionListPresenter presenterReparacion;
    private ReparacionDetailListView fragmentReparacionDetailView;//Ver Reparacion
    private ClienteListView fragmentClienteListView;
    private ClienteListPresenter presenterCliente;
    private ServicioListView fragmentServicioListView;
    private ServicioListPresenter presenterServicio;
    private FacturaListView fragmentFacturaListView;
    private static int idItemNvDrawerSelect =-1;//id de la opciond e menu Drawer selecionada
    private ServicioAddyEditView fragmentServicioAddyEditView;
    private ServicioAddEditPresenter servicioAddEditPresenter;
    private ClienteAddyEditView fragmentClienteAddyEditView;
    private ClienteAddyEditPresenter clienteAddyEditPresenter;
    private ReparacionAddView fragmentReparacionAddView;
    private ReparacionAddPresenter reparacionAddPresenter;

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
                //Toast.makeText(MainActivity.this,"Vista añadir aun sin implementar",Toast.LENGTH_SHORT).show();
                String titulo = getTitle().toString();
                switch (titulo)
                {
                    case "Reparaciones":
                        fragmentAnadirReparacionAdd();
                        break;
                    case "Clientes":
                        //Abrimos fragment añadir de Cliente
                        fragmentManipularDatosClienteAddOEdit(null,-1);
                        break;
                    case "Servicios":
                        //Abrimos fragment añadir de Servicios
                        fragmentManipularDatosServicioAddOEdit(null,-1);//Le pasamos -1 ya que no va editar ningune elemento de la lista de repositorie si no que va añadir un elemento nuevo
                        break;
                    case "Facturas":
                        //Abrimos fragment añadir de Facturas
                        break;

                }
                ocultarMostrarFloatinButtom();
            }
        });


        //Nos permite inflar los iconos de cada una de las opciones del menu drawer, aunq ue NO SE USA AHORA MISMO ya que las inflamos desde el XML
        /*mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_clientes, R.id.nav_reparaciones, R.id.nav_servicios,
                R.id.nav_facturas, R.id.nav_compartir).setDrawerLayout(drawer).build();*/

        /*Inicializamos el listener para onNavigationItemSelected(@NonNull MenuItem item).
         Tambien podriamos haberlo realizado con navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() */
        navigationView.setNavigationItemSelectedListener(this);

        //Inicializamos el fragmento que va mostrar el navigationDrawer por defecto al iniciar su actividad
        /*----------------------------------------------------------------------*/

        Inicializar();

        /*----------------------------------------------------------------------*/

    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);

    }

    /*Restauramos la visa , segun la ultima opcion selecionada*/
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e("AQUI","Estoy en onRestoreInstanceState :"+idItemNvDrawerSelect);
          pintarFragmentSelecioando(idItemNvDrawerSelect);

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

    private void pintarFragmentSelecioando(int itemSeleciolado){

        switch ((itemSeleciolado)) {
            case R.id.nav_reparaciones:
                fragmentReparacionListView =(ReparacionListView)getSupportFragmentManager().findFragmentByTag(ReparacionListView.TAG);
                if(fragmentReparacionListView==null)
                {
                    fragmentReparacionListView= ReparacionListView.newInstance(null);
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_contenedor_fragment,fragmentReparacionListView,ReparacionListView.TAG).commit();
                presenterReparacion= new ReparacionListPresenter(fragmentReparacionListView);
                fragmentReparacionListView.setPresenter(presenterReparacion);
                setTitle(R.string.menu_reparaciones);
                break;

            case R.id.nav_clientes:
                fragmentClienteListView = (ClienteListView) getSupportFragmentManager().findFragmentByTag(fragmentClienteListView.TAG);
                if(fragmentClienteListView ==null)
                {
                    fragmentClienteListView = ClienteListView.newInstance(null);
                }
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_contenedor_fragment, fragmentClienteListView, ClienteListView.TAG)
                        .commit();
                presenterCliente = new ClienteListPresenter(fragmentClienteListView);
                fragmentClienteListView.setPresenter(presenterCliente);
                setTitle(R.string.menu_clientes);
                break;

            case R.id.nav_servicios:
                Log.d("PRUEBA", "PULSASTE SERVICIOS");
                fragmentServicioListView = (ServicioListView) getSupportFragmentManager().findFragmentByTag(fragmentServicioListView.TAG);
                if(fragmentServicioListView==null)
                {
                    fragmentServicioListView= ServicioListView.newInstance(null);
                }
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_contenedor_fragment,fragmentServicioListView, ServicioListView.TAG)
                        .commit();
                presenterServicio=new ServicioListPresenter(fragmentServicioListView);
                fragmentServicioListView.setPresenter(presenterServicio);
                setTitle(R.string.menu_servicios);
                break;

            case R.id.nav_facturas:
                fragmentFacturaListView = (FacturaListView) getSupportFragmentManager().findFragmentByTag(fragmentFacturaListView.TAG);
                if(fragmentFacturaListView==null)
                {
                    fragmentFacturaListView= FacturaListView.newInstance();
                }
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_contenedor_fragment,fragmentFacturaListView, FacturaListView.TAG)
                        .commit();
                setTitle(R.string.menu_facturas);
                break;
            case R.id.nav_compartir:
                Toast.makeText(MainActivity.this,"Pulsaste compartir!",Toast.LENGTH_SHORT).show();
                break;
        }

    }

    /**Muestra o oculta el boton flotante de añadir,
     *  segun en el titulo de la toolbar el cual identifica el fragmento*/
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

    /**Infla las opciones del menu superior derechod e la toolbar*/
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

         idItemNvDrawerSelect = item.getItemId();
        Log.e("AQUI","Estoy en onNavigationItemSelected :"+idItemNvDrawerSelect);
        pintarFragmentSelecioando(idItemNvDrawerSelect);
        //Cerramos el navegador desplegable
        drawer.closeDrawer(GravityCompat.START);
        ocultarMostrarFloatinButtom();
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /**Cuando el usuario presiona el boton de back*/
   @Override
    public void onBackPressed() {
        super.onBackPressed();
        //Todo no me gusta ese numero magico getSupportFragmentManager().getFragments().get(0).getTag()
        /**Segun el fragmento al que vamos a volver en la pila,
         *  renombramos el titulo de la toolbar, con el que controlamos el FloatingActionButton de añadir,
         *  de este modo según en el fragmento de listado que nos encontremos, como ReparacionListView,
         *  mostraremos un fragmento de añadir o otro.
         *  Tambien volveremos a mostrar o ocultar el boton de añadir,
         *  segun si esta en uno de estos listados generales como el anteriormente mencionado o no*/
        if(getSupportFragmentManager().getFragments()!=null) {
            switch (getSupportFragmentManager().getFragments().get(0).getTag()) {
                case ReparacionListView.TAG:
                    setTitle(R.string.menu_reparaciones);
                    break;
                case ClienteListView.TAG:
                    setTitle(R.string.menu_clientes);
                    break;
                case ServicioListView.TAG:
                    setTitle(R.string.menu_servicios);
                    break;
                case FacturaListView.TAG:
                    setTitle(R.string.menu_facturas);
                    break;

            }
            ocultarMostrarFloatinButtom();
        }
    }

    //region Implementado por la interfaz ReparacionListView.clickVerReparacionListener
      /*Cuando se hace click sobre la lista de reparaciones
       para abrir un nuevo fragmento con todos los datos de esta*/
    @Override
    public void clickVerReparacionListener() {
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
  //endregion

    // ### ADD y EDITAR o MODIFICAR ### de las distintos modelos, exceptuando las facturas(sin add ni ediciones) y las reparaciones (las cuales no tienen ediciones)

    //region Intefaz implementada ServicioAddyEditView.addOEditServicio,ClienteListView.manipularDatosClienteAddOEdit
    @Override
    public void fragmentManipularDatosServicioAddOEdit(Servicio servicio,int pos) {
        Log.d("pulsacionMAIN "," posicion "+ pos);
        fragmentServicioAddyEditView =(ServicioAddyEditView) getSupportFragmentManager().findFragmentByTag(ServicioAddyEditView.TAG);
        if(fragmentServicioAddyEditView==null)
        {
           Bundle bundle= null;
           if(servicio!=null)
           {
               bundle=new Bundle();
               bundle.putParcelable(Servicio.TAG,servicio);
           }
           fragmentServicioAddyEditView= ServicioAddyEditView.newInstance(bundle,pos);
        }
        servicioAddEditPresenter= new ServicioAddEditPresenter(fragmentServicioAddyEditView);
        fragmentServicioAddyEditView.setPresenter(servicioAddEditPresenter);

        getSupportFragmentManager().beginTransaction().replace(R.id.nav_contenedor_fragment,fragmentServicioAddyEditView,ServicioAddyEditView.TAG).addToBackStack(null).commit();

        setTitle(R.string.anadir_servicios);
        ocultarMostrarFloatinButtom();
    }

    @Override
    public void fragmentManipularDatosClienteAddOEdit(Cliente cliente, int pos) {
        Log.d("pulsacionMAIN "," posicion "+ pos);
        fragmentClienteAddyEditView =(ClienteAddyEditView) getSupportFragmentManager().findFragmentByTag(ClienteAddyEditView.TAG);
        if(fragmentClienteAddyEditView==null)
        {
            Bundle bundle= null;
            if(cliente!=null)
            {
                bundle=new Bundle();
                bundle.putParcelable(Cliente.TAG,cliente);
            }
            fragmentClienteAddyEditView= ClienteAddyEditView.newInstance(bundle,pos);
        }
        clienteAddyEditPresenter= new ClienteAddyEditPresenter(fragmentClienteAddyEditView);
        fragmentClienteAddyEditView.setPresenter(clienteAddyEditPresenter);

        getSupportFragmentManager().beginTransaction().replace(R.id.nav_contenedor_fragment,fragmentClienteAddyEditView,ClienteAddyEditView.TAG).addToBackStack(null).commit();

        setTitle(R.string.anadir_cliente);
        ocultarMostrarFloatinButtom();
    }
    //endregion

    //Add Reparaciones
    public void fragmentAnadirReparacionAdd() {
        fragmentReparacionAddView = (ReparacionAddView) getSupportFragmentManager().findFragmentByTag(fragmentReparacionAddView.TAG);
        if(fragmentReparacionAddView ==null)
        {
            fragmentReparacionAddView = ReparacionAddView.newInstance();
        }
        reparacionAddPresenter = new ReparacionAddPresenter(fragmentReparacionAddView);
        fragmentReparacionAddView.setPresenter(reparacionAddPresenter);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_contenedor_fragment, fragmentReparacionAddView, ReparacionAddView.TAG).addToBackStack(null)
                .commit();
        setTitle(R.string.anadir_reparacion);
        ocultarMostrarFloatinButtom();
    }


}

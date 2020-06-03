package com.pablolopezs.grepaut.ui.reparacion;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.pablolopezs.grepaut.R;
import com.pablolopezs.grepaut.adapter.ReparacionDetailListAdapter;
import com.pablolopezs.grepaut.data.model.Factura;
import com.pablolopezs.grepaut.data.model.Reparacion;
import com.pablolopezs.grepaut.data.repositories.FacturaRepositories;

import java.util.ArrayList;
import java.util.Calendar;

public class ReparacionDetailListView extends Fragment implements ReparacionListContract.View {
    public static final String TAG="ReparacionDetailListView";
    private  ReparacionDetailListAdapter reparacionDetailListAdapter;
    private RecyclerView rvReparacionDetail;
    ReparacionListContract.Presenter presenter;
    FloatingActionButton fbFacturar;//Boton que genera factura de todas las reparaciones asocaiadas a un cliente sobre u n mismo vehiculo en un mismo dia(Es decir todo el listado del adapter de este fragment)



    /*Crear una unica instancia de clase*/
    public static ReparacionDetailListView newInstance(Bundle args){
        ReparacionDetailListView fragment = new ReparacionDetailListView();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_reparacion_list_detail_view, container, false);
        fbFacturar=view.findViewById(R.id.fabFacturar);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Para mantener los datos o estado al girar la actividad
        setRetainInstance(true);
        reparacionDetailListAdapter = new ReparacionDetailListAdapter();
        rvReparacionDetail = view.findViewById(R.id.rvReparacionDetalle);
        inicializarRvReparacionDetail();
        presenter.cargarDatosDeDetallesDeReparacion();

        //Boton de facturar
        fbFacturar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //--------------Ventana de AlerDialog----------
                AlertDialog alerta = new AlertDialog.Builder(getContext()).
                        setMessage("¿Estas seguro de generar una factura de estas reparaciones?").setTitle("Aviso. ").
                        setPositiveButton("Si", new DialogInterface.OnClickListener() {  //Aceptamos el generar la factura
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Recogemos todos los elementos de la lista de reparaciones en detalle que estan sin facturar
                                ArrayList<Reparacion> listReparSinFacturar= reparacionDetailListAdapter.reparacionesSinFacturar();
                                //Si la lista obtenida tiene algun elemento, es que vamos a facturar alguan reparacion
                                if(listReparSinFacturar.size()>0)
                                {
                                    //Recogemos el ultimo numero de factura
                                    int ultimoNumFactura = FacturaRepositories.getInstance().ultimoNumeroFact();
                                    int numeroNuevaFactura = ultimoNumFactura + 1;//Nuevo nuemero de factura, para la que vamos a crear
                                    //Fecha actual en la que creamos la factura en string
                                    Calendar cFechaHoy = Calendar.getInstance();
                                    int day = cFechaHoy.get(Calendar.DAY_OF_MONTH);
                                    int month = cFechaHoy.get(Calendar.MONTH);
                                    int year = cFechaHoy.get(Calendar.YEAR);
                                    String fechaActualFacturacion = Integer.toString(day) + "/" + Integer.toString(month+1) + "/" + Integer.toString(year);//el mes mas 1 por que este empieza en 0
                                    for (Reparacion item : listReparSinFacturar) {
                                        Factura f = new Factura(numeroNuevaFactura, item.getNumeroReparacion(), fechaActualFacturacion, true, item.getFecha(),  item.getMatriculaCoche(), item.getEmailUsuario());
                                        FacturaRepositories.getInstance().add(f);//Añadimos la factura al repositorio
                                        Log.d("factura", "Numero factura: " + f.getNumeroFactura() + " linea fac: " + f.getLineaFactura() + " fecha facturacion: " + f.getFechaFacturacion() + " Estado factura: " + f.getEstadoFactura() + " Matricula coche: " + f.getMatriculaCocheRepara() + " Fecha reapracion: " + f.getFechaReparacion() );
                                    }
                                    //Marcamos estas reapraciones como facturadas, para que no vuelvan a facturarse
                                    reparacionDetailListAdapter.marcarReparaComoFacturadas();
                                }else
                                {
                                    Toast.makeText(getContext(),"Estas reparaciones ya estan facturadas!",Toast.LENGTH_LONG).show();
                                }
                            }

                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Negativo
                                Toast.makeText(getContext(),"Generacion de factura cancelada!",Toast.LENGTH_LONG).show();
                            }
                        }).create();
                alerta.show();
                //--------------FIN Ventana de AlerDialog----------


            }
        });

    }

    public  void inicializarRvReparacionDetail(){
        rvReparacionDetail.setAdapter(reparacionDetailListAdapter);
        rvReparacionDetail.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void hayDatos(ArrayList<Reparacion> list) {
        reparacionDetailListAdapter.addAll(list);
        reparacionDetailListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    /**Metodos implementados porla interfaz*/
    @Override
    public void setPresenter(ReparacionListContract.Presenter presenter) {
        this.presenter= presenter;
    }

    @Override
    public void mostrarError(String msg) {
        Toast.makeText(getContext(),"Error",Toast.LENGTH_LONG).show();
    }

    @Override
    public void noDatos() {
        Toast.makeText(getContext(),"NO hay datos",Toast.LENGTH_LONG).show();;
    }

    @Override
    public void mensaje(String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_LONG).show();
    }

    /**---------------------------------------------*/
}

package com.pablolopezs.grepaut.ui.reparacion;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.pablolopezs.grepaut.R;
import com.pablolopezs.grepaut.adapter.ReparacionListAdapter;
import com.pablolopezs.grepaut.adapter.TouchCallback;
import com.pablolopezs.grepaut.data.model.Reparacion;

import java.util.ArrayList;
import androidx.appcompat.app.AlertDialog;


/**
 * Frament que gestiona una lista general
 * de todas las reparaciones existentes
 * en el sistema de una determinada cuenta
 */
public class ReparacionListView extends Fragment implements ReparacionListContract.View {

    public static final String TAG = "ReparacionListView";
    private ReparacionListAdapter reparacionListAdapter;
    private RecyclerView rvReparacion;
    ReparacionListContract.Presenter presenter;
    clickVerReparacionListener clickVerReparacionListener;
    private ItemTouchHelper mItemTouchHelperListener;


    /*Constructor que crear una unica instancia de clase*/
    public static ReparacionListView newInstance(Bundle args) {
        ReparacionListView fragment = new ReparacionListView();
        fragment.setArguments(args);
        return fragment;
    }

    /*Crea/iInfla la vista*/
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reparacion_list_view, container, false);
        Log.d("PRUEBA", "ReparacionListView: onCreateView ");
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /*Justo despues de crear la vista*/
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Para mantener los datos o estado al girar la actividad
        setRetainInstance(true);
        rvReparacion = view.findViewById(R.id.rvReparacion);
        inicializarRvReparacion();
        presenter.cargarDatos();
        Log.d("PRUEBA", "ReparacionListView: onViewCreated() ");

    }

    /**
     * Método que inicializa el RecyclerView que muestra todas el adapter de Reparaciones
     */
    public void inicializarRvReparacion() {
        //1. Crear adapter
        reparacionListAdapter = new ReparacionListAdapter(new ReparacionListAdapter.manipularDatos() {
            @Override//NO USADO ACTUALMENTE
            public void miOnLOngClick(int posicion) {//NO USADO ACTUALMENTE
                /*Eliminamos el elemento de la lista del Repositorio*/
                /*presenter.eliminar(posicion);
                reparacionListAdapter.notifyDataSetChanged();//Para que actualice los datos*/
            }

            /*Cuando el usuario intenta editar un registro de reparación, le mostramos informacion de todas las reparaciones (de un cliente sobre un coche en una misma fecha)
             ya que no es posible editarlas,(debe eliminarlo y crear uno nuevo)*/
            @Override
            public void miClick() {
                Log.d("CAMBIO", "ENTRO a cambiar la vista");
                clickVerReparacionListener.clickVerReparacionListener();
            }

            @Override
            public void confirmarBorrado(final int adapterPosition) {
                //--------------Ventana de AlerDialog----------
                AlertDialog alerta = new AlertDialog.Builder(getContext()).
                        setMessage("¿Estas seguro de eliminar este elemento?").setTitle("Aviso. ").
                        setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            /*Se ha confirmado el borrado del item en la ventana de AlerDialog y se prodece a mostrar
                                un scankbar a bajo que permite deshacer esta opcion durante un breve periodo de tiempo antes de convertirla en irreversible*/
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Eliminar el objeto reparacion de la lista del Repositiorio a traves del presenter
                                presenter.eliminar(adapterPosition);

                                //Eliminamos la Reparacion y la guardamos, ya que se puede RESTAURAR antes de 10 segundos con el snackbar
                               final Reparacion r = reparacionListAdapter.eliminar(adapterPosition);
                               Log.d("Deshacer",Integer.toString(r.getNumeroReparacion()));
                                //----------Deshacer/Restaurar eliminacion------------
                                Snackbar snackbar = Snackbar
                                        .make(getActivity().findViewById(R.id.contenedorPadre),"Reparación del cliente: "+ r.getNombreCliente() + " Deshacer el borrrado", 10000);
                                snackbar.setAction("Deshacer", new View.OnClickListener() {
                                    /*Se pulso el boton "Deshacer" del snackbar y
                                     se restaura el elemento de nuevo en la vista del ReciclerView*/
                                    @Override
                                    public void onClick(View view) {
                                        reparacionListAdapter.deshacerBorrado(adapterPosition,  r);
                                        //Añadimos el objeto de nuevo en su posicion original en el repositiorio
                                        presenter.anadirPorPos(adapterPosition,r);
                                    }
                                });
                                snackbar.setActionTextColor(Color.WHITE);
                                snackbar.show();
                                //---------Fin de barra de Deshacer------------
                            }
                        }).
                        setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Negativo
                                reparacionListAdapter.cancelacionDeBorrado(adapterPosition);
                            }
                        }).create();
                alerta.show();
                //--------------FIN Ventana de AlerDialog----------
            }


        });
        //2. Crear diseño del RecyclerView
        // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT, RecyclerView.VERTICAL, false);
        //rvReparacion.setLayoutManager(linearLayoutManager);
        //3. Vincular la vista al modelo (RecyclerView al Adapter)
        rvReparacion.setAdapter(reparacionListAdapter);
        rvReparacion.setLayoutManager(new LinearLayoutManager(getContext()));


        //Incializamos la clase TouchCallback para poder realizar el efecto de eleminar al desplazar el dedo hacia la izquierda sobre un elemento
        TouchCallback callback = new TouchCallback(reparacionListAdapter);
        mItemTouchHelperListener = new ItemTouchHelper(callback);
        mItemTouchHelperListener.attachToRecyclerView(rvReparacion);
        Log.d("PRUEBA", "ReparacionListView: initRvReparacion() ");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        clickVerReparacionListener = (clickVerReparacionListener) context;//Ayuda de Adri, pero nola entiendo TODO PREGUNTARLE POR QUE?? (No termino de entender la funcion de onAttach)
    }

    @Override
    public void onDetach() {
        super.onDetach();
        clickVerReparacionListener = null;//Ayuda de Adri, pero nola entiendo TODO PREGUNTARLE POR QUE?? (No termino de entender la funcion de onDetach)
    }


    //region Metodos implementados por la interfaz View
    @Override
    public void hayDatos(ArrayList<Reparacion> list) {
        reparacionListAdapter.addAll(list);
        reparacionListAdapter.notifyDataSetChanged();//Para que actualice los datos
    }

    @Override
    public void noDatos() {
        Toast.makeText(getContext(), "NO hay datos", Toast.LENGTH_LONG).show();
    }

    @Override
    public void mensaje(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarError(String msg) {
        Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
    }

    @Override
    public void setPresenter(ReparacionListContract.Presenter presenter) {
        this.presenter = presenter;
    }
    /*------------Fin metodos implementados por la interfaz ReparacionListContract.View ----------------*/
    //endregion

    /*Interfaz que implementamos como escuchador para a la hora de clicar en un elemento de la vista, mostrar la vista de edicion con los datos de el elemento selecionado
    (En este caso la lista de datos relacionados de las reparaciones sobre un mismo cliente en una misma fecha sobr eun mismo vehiuculo)*/
    public interface clickVerReparacionListener {
        void clickVerReparacionListener();
    }
    //endregion
}
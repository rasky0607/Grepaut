package com.pablolopezs.grepaut.ui.MainActivity.reparacion;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.pablolopezs.grepaut.R;
import com.pablolopezs.grepaut.adapter.ReparacionListAdapter;
import com.pablolopezs.grepaut.data.model.Reparacion;

import java.util.ArrayList;

public class ReparacionListView extends Fragment implements  ReparacionListContract.View {

    public static  String TAG = "ReparacionListView";
    private ReparacionListAdapter reparacionListAdapter;
    private  RecyclerView rvReparacion;
    ReparacionListContract.Presenter presenter;
    clickVerReparacionListener clickVerReparacionListener;

    /*Crear una unica instancia de clase*/
    public static ReparacionListView  newInstance(Bundle args){
        ReparacionListView fragment = new ReparacionListView();
        fragment.setArguments(args);
        return fragment;

    }

    /*Crea/iInfla la vista*/
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reparacion_list_view, container, false);
        Log.d("PRUEBA", "ReparacionListView: onCreateView ");
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Para mantener los datos o estado al girar la actividad
        //setRetainInstance(true);
    }

    /*Justo despues de crear la vista*/
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvReparacion=view.findViewById(R.id.rvReparacionlist);
        inicializarRvReparacion();
       presenter.cargarDatos();
        Log.d("PRUEBA", "ReparacionListView: onViewCreated() ");

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        clickVerReparacionListener=(clickVerReparacionListener)context;//TODO POR QUE?? (No entiendo la funcion de onAttach)
    }

    @Override
    public void onDetach() {
        super.onDetach();
        clickVerReparacionListener=null;//TODO POR QUE?? (No entiendo la funcion de onDetach)
    }

    /*
            Método que inicializa el RecyclerView que muestra todas el adapter de Reparaciones
            */
    public void inicializarRvReparacion() {
        //1. Crear adapter

        //Cuando se hace click en el ReciclerView
        reparacionListAdapter = new ReparacionListAdapter(new ReparacionListAdapter.manipularDatos() {
            @Override
            public void miOnLOngClick(int posicion) {
                /*Eliminamos el elemento de la lista del Repositorio*/
                presenter.eliminar(posicion);
               /* if(presenter.eliminar(posicion))
                    Toast.makeText(getContext(),"Registro eliminado",Toast.LENGTH_SHORT);
                else
                    Toast.makeText(getContext(),"Un registro Facturado NO puede ser eliminado",Toast.LENGTH_LONG);*/

                reparacionListAdapter.notifyDataSetChanged();//Para que actualice los datos
            }

            /*Cuando el usuario intenta editar unr egistro de reparación, le infomamos de que no es posible(debe eliminarlo y crear uno nuevo)*/
            @Override
            public void miClick() {
                Log.d("CAMBIO","ENTRO a cambiar la vista");
               clickVerReparacionListener.clickVerReparacionListener();
                //Todo inflamos aqui otro fragment que muestrel detalle de esta reparacion??????
            }

        });
        //2. Crear diseño del RecyclerView
       // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT, RecyclerView.VERTICAL, false);
        //rvReparacion.setLayoutManager(linearLayoutManager);
        //3. Vincular la vista al modelo (RecyclerView al Adapter)
        rvReparacion.setAdapter(reparacionListAdapter);
        rvReparacion.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.d("PRUEBA", "ReparacionListView: initRvReparacion() ");
    }

    //region Metodos implementados por la interfaz View
    @Override
    public void hayDatos(ArrayList<Reparacion> list) {
     reparacionListAdapter.addAll(list);
     reparacionListAdapter.notifyDataSetChanged();//Para que actualice los datos
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


    @Override
    public void setPresenter(ReparacionListContract.Presenter presenter) {
        this.presenter=presenter;
    }

    /*Interfaz que implementamos como escuchador para a la hora de clicar en un elemento de la vista, mostrar la vista de edicion con los datos de el elemento selecionado*/
    public  interface clickVerReparacionListener{
        void clickVerReparacionListener();

    }
    //endregion
}
package com.pablolopezs.grepaut.ui.MainActivity.reparacionlist;

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

public class ReparacionListView extends Fragment implements ReparacionListContract.View {

    public static  String TAG = "ReparacionListView";
    private ReparacionListAdapter reparacionListAdapter;
    private  RecyclerView rvReparacion;
    ReparacionListContract.Presenter presenter;

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
        setRetainInstance(true);
    }

    /*Justo despues de crear la vista*/
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvReparacion=view.findViewById(R.id.rvReparacion);
        inicializarRvReparacion();
       presenter.cargarDatos();
        Log.d("PRUEBA", "ReparacionListView: onViewCreated() ");

    }

    /*
    Método que inicializa el RecyclerView que muestra todas el adapter de Reparaciones
    */
    public void inicializarRvReparacion() {
        //1. Crear adapter
        reparacionListAdapter = new ReparacionListAdapter(new ReparacionListAdapter.manipularDatos() {
            @Override
            public void miOnLOngClick(int posicion) {
                /*Eliminamos el elemento de la lista del Repositorio*/
                if(presenter.eliminar(posicion))
                    Toast.makeText(getContext(),"Registro eliminado",Toast.LENGTH_SHORT);
                else
                    Toast.makeText(getContext(),"Un registro Facturado NO puede ser eliminado",Toast.LENGTH_LONG);

                reparacionListAdapter.notifyDataSetChanged();//Para que actualice los datos
            }
            /*Cuando el usuario intenta editar unr egistro de reparación, le infomamos de que no es posible(debe eliminarlo y crear uno nuevo)*/
            @Override
            public void miClick(String msg) {
                Toast.makeText(getContext(),msg,Toast.LENGTH_LONG);//TODO no muestra los toast al eliminar algo con exito falla o al intentar editar una reparacion
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
     Toast.makeText(getContext(),"Correcto",Toast.LENGTH_LONG);
    }

    @Override
    public void mostrarError(String msg) {
        Toast.makeText(getContext(),"Error",Toast.LENGTH_LONG);
    }

    @Override
    public void noDatos() {
        Toast.makeText(getContext(),"NO hay datos",Toast.LENGTH_LONG);
    }

    @Override
    public void mensaje(String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_LONG);
    }

    @Override
    public void setPresenter(ReparacionListContract.Presenter presenter) {
        this.presenter=presenter;
    }
    //endregion
}
package com.pablolopezs.grepaut.ui.reparacion;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pablolopezs.grepaut.R;
import com.pablolopezs.grepaut.adapter.ReparacionDetailListAdapter;
import com.pablolopezs.grepaut.data.model.Reparacion;

import java.util.ArrayList;

public class ReparacionDetailListView extends Fragment implements ReparacionListContract.View {
    public static final String TAG="ReparacionDetailListView";
    private  ReparacionDetailListAdapter reparacionDetailListAdapter;
    private RecyclerView rvReparacionDetail;
    ReparacionListContract.Presenter presenter;

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

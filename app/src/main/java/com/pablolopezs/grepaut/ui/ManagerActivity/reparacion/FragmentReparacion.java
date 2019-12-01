package com.pablolopezs.grepaut.ui.ManagerActivity.reparacion;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pablolopezs.grepaut.R;
import com.pablolopezs.grepaut.adapter.ReparacionAdapter;
import com.pablolopezs.grepaut.data.model.Reparacion;

public class FragmentReparacion extends Fragment {

    public static  String TAG = "Fragmento Reparaciones";
    private ReparacionAdapter reparacionAdapter;
    private  RecyclerView rvReparacion;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reparacion, container, false);

        Log.d("PRUEBA", "FragmentReparacion: onCreateView ");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvReparacion=view.findViewById(R.id.rvReparacion);
        initRvDependency();
        Log.d("PRUEBA", "FragmentReparacion: onViewCreated ");

    }

    /*
    Método que inicializa el RecyclerView que muestra todas las dependencias
    */
    private void initRvDependency() {

        //1. Crear adapter
        reparacionAdapter = new ReparacionAdapter();


        //2. Crear diseño del RecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

        //GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT, RecyclerView.VERTICAL, false);
        rvReparacion.setLayoutManager(linearLayoutManager);

        //3. Vincular la vista al modelo (RecyclerView al Adapter)
        rvReparacion.setAdapter(reparacionAdapter);
    }
}
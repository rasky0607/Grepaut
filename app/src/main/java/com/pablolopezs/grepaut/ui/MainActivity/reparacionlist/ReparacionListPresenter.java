package com.pablolopezs.grepaut.ui.MainActivity.reparacionlist;

import android.util.Log;

import com.pablolopezs.grepaut.data.repository.ReparacionRepositories;

public class ReparacionListPresenter implements ReparacionListContract.Presenter {

    ReparacionListContract.View view;

    public ReparacionListPresenter(ReparacionListContract.View view) {
        this.view = view;
    }


    @Override
    public void cargarDatos() {
        Log.d("PRUEBA", "ReparacionListPresenter: cargarDatos() ");
        view.mensaje("Cargando datos...");
        if(ReparacionRepositories.getInstance().getList().isEmpty()) {
            view.noDatos();
            Log.d("PRUEBA", "ReparacionListPresenter: cargarDatos() VACIO ");
        }
        else
            view.hayDatos(ReparacionRepositories.getInstance().getList());
        Log.d("PRUEBA", "ReparacionListPresenter: cargarDatos() SALIENDO ");
    }
}

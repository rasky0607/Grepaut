package com.pablolopezs.grepaut.ui.MainActivity.reparacionlist;

import android.util.Log;
import android.view.View;

import com.pablolopezs.grepaut.adapter.ReparacionListAdapter;
import com.pablolopezs.grepaut.data.model.Reparacion;
import com.pablolopezs.grepaut.data.repositories.ReparacionRepositories;

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

    }

    //Elimina la reparacion  de una determinada posicion de la lista de ReparacionRepositories
    @Override
    public boolean eliminar(int posicion) {
        Reparacion reparacion= ReparacionRepositories.getInstance().getList().get(posicion);//Optenemos el elementos de esa posicion de la l ista
        if(!reparacion.getEstadoFacturado())//Si esta reparacion NO esta facturada, podra eliminarse, de lo contrario no podra ser eliminada y se devolvera false.
        {
            ReparacionRepositories.getInstance().getList().remove(posicion);
            return true;
        }
        else
            return false;
    }

    @Override
    public void anadir(Reparacion reparacion) {

    }


}

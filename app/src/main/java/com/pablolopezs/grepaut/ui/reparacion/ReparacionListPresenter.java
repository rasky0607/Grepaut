package com.pablolopezs.grepaut.ui.reparacion;

import android.util.Log;

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
        if(ReparacionRepositories.getInstance().getList().isEmpty()) {
            view.noDatos();
            Log.d("PRUEBA", "ReparacionListPresenter: cargarDatos() VACIO ");
        }
        else{
            view.hayDatos(ReparacionRepositories.getInstance().getList());
            view.mensaje("Datos cargados.");
        }

    }

    /**Carga los datos de una lista de reparaciones efectuadas sobre un mismo cliente enun mismo dia sobre un mismo vehiculo*/
    @Override
    public void cargarDatosDeDetallesDeReparacion() {
        Log.d("PRUEBA", "ReparacionListPresenter: cargarDatos() ");
        if(ReparacionRepositories.getInstance().getListReparacionesComunes().isEmpty()) {
            view.noDatos();
            Log.d("PRUEBA", "ReparacionListPresenter: cargarDatos() VACIO ");
        }
        else{
            view.hayDatos(ReparacionRepositories.getInstance().getListReparacionesComunes());
            view.mensaje("Datos cargados.");
        }
    }

    //Elimina la reparacion  de una determinada posicion de la lista de ReparacionRepositories
    @Override
    public boolean eliminar(int posicion) {
        Reparacion reparacion= ReparacionRepositories.getInstance().getList().get(posicion);//Optenemos el elementos de esa posicion de la l ista
        if(!reparacion.getEstadoFacturado())//Si esta reparacion NO esta facturada, podra eliminarse, de lo contrario no podra ser eliminada y se devolvera false.
        {
            ReparacionRepositories.getInstance().getList().remove(posicion);
            view.mensaje("Reparacion eliminada con exito.");
            return true;
        }
        else {
            view.mensaje("Esta reparacion no puede eliminarse por que ya esta facturada.");
            return false;
        }
    }
    //Muestra un elemento seleccionado en detalle
    @Override
    public void editar(int pos, Reparacion reparacion) {


    }

    @Override
    public void anadir(Reparacion reparacion) {

    }

    @Override
    public void anadirPorPos(int pos,Reparacion objeto) {
        ReparacionRepositories.getInstance().getList().add(pos,objeto);
    }


}

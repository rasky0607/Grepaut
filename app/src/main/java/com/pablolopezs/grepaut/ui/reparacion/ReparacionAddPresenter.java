package com.pablolopezs.grepaut.ui.reparacion;

import com.pablolopezs.grepaut.data.model.Reparacion;
import com.pablolopezs.grepaut.data.repositories.ReparacionRepositories;

import java.util.List;

public class ReparacionAddPresenter implements ReparacionAddContract.Presenter {
    ReparacionAddContract.View view;

    public ReparacionAddPresenter(ReparacionAddContract.View view)
    {
        this.view=view;
    }

    @Override
    public void anadir(Reparacion objeto) {
        ReparacionRepositories.getInstance().insert(objeto);
        //view.mensaje("Añadida Reparación con Fecha: " + objeto.getFecha() + ", para vehiculo con matricula: " + objeto.getMatriculaCoche());
        //ReparacionRepositories.getInstance().getList().add(objeto);
        /*if(ReparacionRepositories.getInstance().insert(objeto)) {
            //ReparacionRepositories.getInstance().insert(objeto);
            view.mensaje("Añadida Reparación con Fecha: " + objeto.getFecha() + ", para vehiculo con matricula: " + objeto.getMatriculaCoche());
        }
        else
            view.mostrarError("No se puedo añadir la reparacion numero "+objeto.getNumeroReparacion()+ " con Fecha "+objeto.getFecha()+", para vehiculo con matricula: "+objeto.getMatriculaCoche());*/
    }

    @Override
    public void modificar(Reparacion objeto) {
        ReparacionRepositories.getInstance().update(objeto);
    }

    @Override
    public boolean validar() {
        return view.esValido();
    }

    //Obtiene la lista de reparacioens que coinciden en fecha y matricula, para conocer el numero de reparacion, de la ultima reparacion insertada que coincide
    @Override
    public int getNumeroUltimaReparacion(Reparacion reparacion){
        List<Reparacion> list=null;
        ReparacionRepositories.getInstance().setListReparacionesComunes(reparacion.getFecha(),reparacion.getMatriculaCoche());
        list= ReparacionRepositories.getInstance().getListReparacionesComunes();
        int n= list.size();
        if(list.size()>0) {
            Reparacion r = list.get(list.size() - 1);
            return r.getNumeroReparacion();
        }
        return 0;
    }
}

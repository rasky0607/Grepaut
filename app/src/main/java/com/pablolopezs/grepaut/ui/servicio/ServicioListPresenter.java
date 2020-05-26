package com.pablolopezs.grepaut.ui.servicio;

import com.pablolopezs.grepaut.data.model.Servicio;
import com.pablolopezs.grepaut.data.repositories.ServicioRepositories;

public class ServicioListPresenter implements ServicioListContract.Presenter {
    ServicioListContract.View view;

    public  ServicioListPresenter(ServicioListContract.View view){
        this.view=view;
    }

    @Override
    public void cargarDatos() {
        if(ServicioRepositories.getInstance().getList().isEmpty()){
            view.noDatos();
        } else{
            view.hayDatos(ServicioRepositories.getInstance().getList());
            view.mensaje("Datos Cargados");
        }
    }

    @Override
    public void anadir(Servicio objeto) {

    }

    @Override
    public void anadirPorPos(int pos, Servicio objeto) {
        ServicioRepositories.getInstance().getList().add(pos,objeto);
    }

    @Override
    public void editar(Servicio objeto) {

    }

    //Añadimos el elmento pasado en una posicion concreta, como cuando deshacemos un borrado con el snakbar<
    @Override
    public boolean eliminar(int posicion) {
        ServicioRepositories.getInstance().getList().remove(posicion);
        return true;
    }
}

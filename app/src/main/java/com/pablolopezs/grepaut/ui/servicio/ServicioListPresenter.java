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
        if(ServicioRepositories.getInstance().getList()== null){
            view.noDatos();
        } else{
            view.hayDatos(ServicioRepositories.getInstance().getList());
            view.mensaje("Datos Cargados");
        }
    }

    //para cuando se deshace un borraddo, volvermos a insertarlo
    @Override
    public void anadir(Servicio objeto) {
        ServicioRepositories.getInstance().insert(objeto);
    }


    @Override
    public void actualizar(int pos, Servicio objeto) {
        //ServicioRepositories.getInstance().getList().add(pos,objeto);
        ServicioRepositories.getInstance().update(objeto);
    }

    @Override
    public boolean eliminar(int posicion,Servicio objeto) {
        //ServicioRepositories.getInstance().getList().remove(posicion);
        ServicioRepositories.getInstance().delete(objeto);


        return true;
    }
}

package com.pablolopezs.grepaut.ui.servicio;

import com.pablolopezs.grepaut.data.model.Servicio;
import com.pablolopezs.grepaut.data.repositories.ServicioRepositories;
import com.pablolopezs.grepaut.ui.Base.BaseAddEdit;

import java.util.ArrayList;

public class ServicioAddEditPresenter implements ServicioAddEditContract.Presenter{
    ServicioAddEditContract.View view;
    public ServicioAddEditPresenter(ServicioAddEditContract.View view){
        this.view=view;
    }

    @Override
    public void anadir(Servicio objeto) {
        //ServicioRepositories.getInstance().getList().add(objeto);
        if(ServicioRepositories.getInstance().insert(objeto))
            view.mensaje("Insercion de servicio "+objeto.getNombre());
        else
            view.mostrarError("No se pudo insertar el servicio "+objeto.getNombre());

    }

    @Override
    public void modificar(Servicio objeto) {
        //ServicioRepositories.getInstance().getList().add(pos,objeto);
        if(ServicioRepositories.getInstance().update(objeto))
            view.mensaje("Modificación de servicio "+objeto.getNombre());
        else
            view.mostrarError("No se pudo modificar el registro "+objeto.getNombre());
    }

    @Override
    public boolean validar() {
        return view.esValido();
    }
}

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
        ServicioRepositories.getInstance().getList().add(objeto);
        view.mensaje("Insercion de servicio "+objeto.getNombre());
    }

    @Override
    public void modificar(int pos, Servicio objeto) {
        ServicioRepositories.getInstance().getList().add(pos,objeto);
        view.mensaje("Modificación de servicio "+objeto.getNombre());
    }

    @Override
    public boolean validar(Servicio objeto) {
        return true;
    }
}

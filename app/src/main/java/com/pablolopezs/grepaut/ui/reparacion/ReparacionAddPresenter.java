package com.pablolopezs.grepaut.ui.reparacion;

import com.pablolopezs.grepaut.data.model.Reparacion;
import com.pablolopezs.grepaut.data.repositories.ReparacionRepositories;

public class ReparacionAddPresenter implements ReparacionAddContract.Presenter {
    ReparacionAddContract.View view;

    public ReparacionAddPresenter(ReparacionAddContract.View view)
    {
        this.view=view;
    }

    @Override
    public void anadir(Reparacion objeto) {
        ReparacionRepositories.getInstance().getList().add(objeto);
        view.mensaje("Insercion de Reparación  con Fecha: "+objeto.getFecha()+", para vehiculo con matricula: "+objeto.getMatriculaCoche()+" realizada");
    }

    @Override
    public void modificar(int pos, Reparacion objeto) {

    }

    @Override
    public boolean validar() {
        return view.esValido();
    }
}

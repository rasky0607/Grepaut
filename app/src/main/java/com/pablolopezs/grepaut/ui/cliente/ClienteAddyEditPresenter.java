package com.pablolopezs.grepaut.ui.cliente;

import com.pablolopezs.grepaut.data.model.Cliente;
import com.pablolopezs.grepaut.data.repositories.ClienteRepositories;

public class ClienteAddyEditPresenter  implements ClienteAddyEditContract.Presenter{
    ClienteAddyEditContract.View view;

    public ClienteAddyEditPresenter(ClienteAddyEditContract.View view){
        this.view=view;
    }

    @Override
    public void anadir(Cliente objeto) {
        ClienteRepositories.getInstance().getList().add(objeto);
        view.mensaje("Insercion de cliente con matricula "+objeto.getMatriculaCoche());
    }

    @Override
    public void modificar(int pos, Cliente objeto) {
        ClienteRepositories.getInstance().getList().add(pos,objeto);
        view.mensaje("Modificación de cliente "+objeto.getMatriculaCoche());
    }

    @Override
    public boolean validar() {
        return view.esValido();
    }
}

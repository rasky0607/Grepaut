package com.pablolopezs.grepaut.ui.cliente;

import com.pablolopezs.grepaut.data.model.Cliente;
import com.pablolopezs.grepaut.data.repositories.ClienteRepositories;

public class ClienteListPresenter implements ClienteListContract.Presenter {
    ClienteListContract.View view;

    public ClienteListPresenter(ClienteListContract.View view) {
        this.view = view;
    }

    @Override
    public void cargarDatos() {
        if(ClienteRepositories.getInstance().getList().isEmpty()) {
            view.noDatos();
        }
        else{
            view.hayDatos(ClienteRepositories.getInstance().getList());
            view.mensaje("Datos Cargados");
        }
    }

    @Override
    public boolean eliminar(int posicion) {
        return false;
    }

    @Override
    public void editar(Cliente cliente) {

    }

    @Override
    public void anadir(Cliente cliente) {

    }
}

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
        ClienteRepositories.getInstance().getList().remove(posicion);
        return true;
    }

    @Override
    public void editar(int pos, Cliente cliente) {
        ClienteRepositories.getInstance().getList().add(pos, cliente);
    }

    @Override
    public void anadir(Cliente cliente) {

    }

    //Añadimos el elmento pasado en una posicion concreta, como cuando deshacemos un borrado con el snakbar<
    @Override
    public void anadirPorPos(int pos, Cliente objeto) {
        ClienteRepositories.getInstance().getList().add(pos,objeto);
    }
}

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
    public boolean eliminar(int posicion,Cliente objeto) {
        //ClienteRepositories.getInstance().getList().remove(posicion);
        //ROOM
        ClienteRepositories.getInstance().delete(objeto);
        return true;
    }

    //Añadimos el elmento pasado en una posicion concreta, como cuando deshacemos un borrado con el snakbar<
    @Override
    public void anadir(Cliente cliente) {
        //ROOM
        ClienteRepositories.getInstance().insert(cliente);
    }


    @Override
    public void actualizar(int pos, Cliente objeto) {
        ClienteRepositories.getInstance().getList().add(pos,objeto);
        //ROOM
        ClienteRepositories.getInstance().update(objeto);
    }
}

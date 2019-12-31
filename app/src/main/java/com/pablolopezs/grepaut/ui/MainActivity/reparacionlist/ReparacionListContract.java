package com.pablolopezs.grepaut.ui.MainActivity.reparacionlist;

import com.pablolopezs.grepaut.data.model.Reparacion;

import java.util.ArrayList;

public class ReparacionListContract {

    public interface View{
        void hayDatos(ArrayList<Reparacion>list);
        void mostrarError(String msg);
        void noDatos();
        void mensaje(String msg);
        void setPresenter(Presenter presenter);

    }

    public interface Presenter{
        void cargarDatos();
    }


}

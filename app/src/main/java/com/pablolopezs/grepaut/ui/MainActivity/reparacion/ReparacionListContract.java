package com.pablolopezs.grepaut.ui.MainActivity.reparacion;

import com.pablolopezs.grepaut.ui.Base.Base;
import com.pablolopezs.grepaut.data.model.Reparacion;

import java.util.ArrayList;

public class ReparacionListContract{

    public interface View extends Base.View<Reparacion> {

        void setPresenter(ReparacionListContract.Presenter presenter);

    }

    public interface Presenter{
        void cargarDatos();
        boolean eliminar(int posicion);
        void editar(Reparacion reparacion);
        void anadir(Reparacion reparacion);
    }



}

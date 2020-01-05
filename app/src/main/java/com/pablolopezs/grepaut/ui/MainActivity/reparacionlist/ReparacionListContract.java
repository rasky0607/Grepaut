package com.pablolopezs.grepaut.ui.MainActivity.reparacionlist;

import com.pablolopezs.grepaut.ui.Base.Base;
import com.pablolopezs.grepaut.data.model.Reparacion;

import java.util.ArrayList;

public class ReparacionListContract{

    public interface View extends Base.View {
        void hayDatos(ArrayList<Reparacion> list);
        void setPresenter(Presenter presenter);

    }

    public interface Presenter{
        void cargarDatos();
        boolean eliminar(int posicion);
        void anadir(Reparacion reparacion);
    }


}

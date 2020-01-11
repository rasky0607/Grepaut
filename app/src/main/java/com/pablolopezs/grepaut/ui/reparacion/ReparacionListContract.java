package com.pablolopezs.grepaut.ui.reparacion;

import com.pablolopezs.grepaut.ui.Base.Base;
import com.pablolopezs.grepaut.data.model.Reparacion;

public class ReparacionListContract{

    public interface View extends Base.View<Reparacion> {
        void setPresenter(Presenter presenter);
    }

    public interface Presenter extends Base.Presenter<Reparacion>{
        void cargarDatosDeDetallesDeReparacion();
    }



}

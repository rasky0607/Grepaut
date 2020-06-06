package com.pablolopezs.grepaut.ui.reparacion;

import com.pablolopezs.grepaut.data.model.Reparacion;
import com.pablolopezs.grepaut.ui.Base.BaseAddEdit;

public class ReparacionAddContract {
    public interface View extends BaseAddEdit.View<Reparacion>{
        void setPresenter(Presenter presenter);
    }
    public interface Presenter extends BaseAddEdit.Presenter<Reparacion> {
        int getNumeroUltimaReparacion(Reparacion reparacion);
    }
}

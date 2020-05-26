package com.pablolopezs.grepaut.ui.servicio;

import com.pablolopezs.grepaut.data.model.Servicio;
import com.pablolopezs.grepaut.ui.Base.Base;

public class ServicioListContract {
    public interface View extends Base.View<Servicio> {
        void setPresenter(Presenter presenter);
    }

    public interface Presenter extends Base.Presenter<Servicio>{

    }
}

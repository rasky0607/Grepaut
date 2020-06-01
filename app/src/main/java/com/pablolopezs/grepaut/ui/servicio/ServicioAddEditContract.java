package com.pablolopezs.grepaut.ui.servicio;

import com.pablolopezs.grepaut.data.model.Servicio;
import com.pablolopezs.grepaut.ui.Base.BaseAddEdit;

public class ServicioAddEditContract {
   public interface View extends BaseAddEdit.View<Servicio>{
        void setPresenter(Presenter presenter);
    }

   public interface Presenter extends BaseAddEdit.Presenter<Servicio>{

    }
}

package com.pablolopezs.grepaut.ui.cliente;

import com.pablolopezs.grepaut.data.model.Cliente;
import com.pablolopezs.grepaut.ui.Base.BaseAddEdit;

public class ClienteAddyEditContract {
    public interface View extends BaseAddEdit.View<Cliente>{
        void setPresenter(Presenter presenter);
    }

    public interface Presenter extends BaseAddEdit.Presenter<Cliente>{

    }
}

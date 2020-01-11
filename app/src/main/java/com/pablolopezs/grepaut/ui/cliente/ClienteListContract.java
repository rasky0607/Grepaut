package com.pablolopezs.grepaut.ui.cliente;

import com.pablolopezs.grepaut.data.model.Cliente;
import com.pablolopezs.grepaut.ui.Base.Base;

public class ClienteListContract {

    public interface View extends Base.View<Cliente>{
        void setPresenter(Presenter presenter);
    }
    public interface Presenter extends Base.Presenter<Cliente>{

    }
}

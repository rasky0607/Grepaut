package com.pablolopezs.grepaut.ui.factura;

import com.pablolopezs.grepaut.data.model.Factura;
import com.pablolopezs.grepaut.ui.Base.Base;

public class FacturaListContract {
    public interface View extends Base.View<Factura>{
        void setPresenter(Presenter presenter);
    }
    public  interface  Presenter extends Base.Presenter<Factura>{
        void cargarDatosDeDetallesDeFactura();
    }
}

package com.pablolopezs.grepaut.ui.reparacion;

import com.pablolopezs.grepaut.data.model.Factura;
import com.pablolopezs.grepaut.ui.Base.Base;
import com.pablolopezs.grepaut.data.model.Reparacion;

public class ReparacionListContract{

    public interface View extends Base.View<Reparacion> {
        void setPresenter(Presenter presenter);
    }

    public interface Presenter extends Base.Presenter<Reparacion>{
        void cargarDatosDeDetallesDeReparacion();
        int ultimoNumeroDeFactura();//Nos devuelve el numero de la ultima factura que se dio de alta
        void crearFactura(Factura factura);
    }



}

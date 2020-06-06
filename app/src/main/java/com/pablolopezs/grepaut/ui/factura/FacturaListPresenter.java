package com.pablolopezs.grepaut.ui.factura;

import android.util.Log;

import com.pablolopezs.grepaut.data.model.Factura;
import com.pablolopezs.grepaut.data.repositories.FacturaRepositories;

public class FacturaListPresenter implements FacturaListContract.Presenter {


    FacturaListContract.View view;

    public FacturaListPresenter(FacturaListContract.View view){
        this.view=view;
    }

    @Override
    public void cargarDatos() {
        if(FacturaRepositories.getInstance().getList().isEmpty()) {
            view.noDatos();
            Log.d("Facturas","No hay nah!");
        }
        else
        {
            Log.d("Facturas","Si hay de toh!");
            view.hayDatos(FacturaRepositories.getInstance().getList());
            view.mensaje("Datos cargados.");
        }
    }

    @Override
    public void anadir(Factura objeto) {

    }

    @Override
    public void actualizar(int pos, Factura objeto) {

    }

    @Override
    public boolean eliminar(int posicion,Factura objeto) {
        return false;
    }

    @Override
    public void cargarDatosDeDetallesDeFactura() {

    }

}

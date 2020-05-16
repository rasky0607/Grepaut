package com.pablolopezs.grepaut.adapter;

public class AdapterContrac {

    public interface BaseAdapterContract<T>{
        T remove(int position);
        void confirmarBorrado(int adapterPosition);
    }

    public interface ContractAdapterReparacion extends BaseAdapterContract {
        boolean estaFacturado(int position);
    }

    public interface ContractAdapterCliente extends BaseAdapterContract {
        boolean tieneReparacion(int position);
    }

    public interface ContractAdapterServicio extends BaseAdapterContract {
        boolean estaEnUnaReparacion(int position);
    }


    //IMPORTANTE:las facturas no pueden ser anuladas


}

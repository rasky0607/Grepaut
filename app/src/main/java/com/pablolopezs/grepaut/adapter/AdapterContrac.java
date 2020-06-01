package com.pablolopezs.grepaut.adapter;

/*Esta clase contiene todos los interfaz o contratos que tienen cada uno de los adapter
* Tanto los que tiene en comun en "BaseAdapterContract" como por separado cada uno en concreto*/
public class AdapterContrac {

    //Interfaz Base que implementan todos los adapter
    public interface BaseAdapterContract<T> {
        T eliminar(int position);
        void confirmarBorrado(int adapterPosition);
        T getItemList(int pos);//Devuelve un objeto de la lista dada una posicion concreta

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

}

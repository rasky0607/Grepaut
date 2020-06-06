package com.pablolopezs.grepaut.adapter;

import com.pablolopezs.grepaut.data.model.Cliente;
import com.pablolopezs.grepaut.data.model.Factura;
import com.pablolopezs.grepaut.data.model.Reparacion;
import com.pablolopezs.grepaut.data.model.Servicio;

import java.util.ArrayList;
import java.util.List;

/*Esta clase contiene todos los interfaz o contratos que tienen cada uno de los adapter
* Tanto los que tiene en comun en "BaseAdapterContract" como por separado cada uno en concreto*/
public class AdapterContrac {

    //Interfaz Base que implementan todos los adapter
    public interface BaseAdapterContract<T> {
        T eliminar(int position);
        void confirmarBorrado(int adapterPosition);
        T getItemList(int pos);//Devuelve un objeto de la lista dada una posicion concreta
        void add(T objeto);
        void addAll(List<T> list);

        public interface ContractAdapterReparacion extends BaseAdapterContract<Reparacion> {
            boolean estaFacturado(int position);
        }

        public interface ContractAdapterCliente extends BaseAdapterContract<Cliente> {
            boolean tieneReparacion(int position);
        }

        public interface ContractAdapterServicio extends BaseAdapterContract<Servicio> {
            boolean estaEnUnaReparacion(int position);
        }

        public interface ContractAdapterFactura extends BaseAdapterContract<Factura>{
            boolean facturaAnulada(Factura factura);
        }


        //IMPORTANTE:las facturas no pueden ser anuladas
    }

}

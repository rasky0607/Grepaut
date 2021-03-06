package com.pablolopezs.grepaut.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pablolopezs.grepaut.R;
import com.pablolopezs.grepaut.data.model.Reparacion;
import com.pablolopezs.grepaut.data.repositories.ReparacionRepositories;

import java.util.ArrayList;
import java.util.List;

/**clase Adapter que gestiona la lista general y eventos generados en dicha lista,
 *  que se mostrara en ReparacionListView*/
public class ReparacionListAdapter extends RecyclerView.Adapter<ReparacionListAdapter.ViewHolder> implements AdapterContrac.BaseAdapterContract.ContractAdapterReparacion {
    private List<Reparacion> listReparacion;
    private manipularDatos manipularDatos;
    private List<Reparacion> listRepaMismoCliyFecha;//Listado de reparacion recibidas por un cliente en una fecha sobre un vehiculo concreto

    //Constructor
    public ReparacionListAdapter(manipularDatos manipularDatos) {

        this.listReparacion = new ArrayList<Reparacion>();
        this.manipularDatos = manipularDatos;
    }



    //Inflamos la vista y la convertimos en ViewHolder(Es decir un bloque del listado del recyclerView
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reparacion_list_view, parent, false);
        Log.d("PRUEBA", "ADAPTER: onCreateViewHolder()");
        return new ViewHolder(v);
    }

    /*Inyectamos los datos a cada ViewHolder*/
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        //holder.icon.setLetter(Integer.toString(listReparacion.get(position).getNumeroReparacion()));
        holder.tvNumeroReparacion.setText(Integer.toString(listReparacion.get(position).getNumeroReparacion()));
        holder.tvFecha.setText("Fecha: " + listReparacion.get(position).getFecha());
        holder.tvMatriculaCoche.setText("Matricula: " + listReparacion.get(position).getMatriculaCoche());

        /*Segun si esta factura o no, el fondo tendra ImagenButtom visible o invisible
         facturada= true -> visible
         facturada  = false -> invisible */
        if (listReparacion.get(position).getEstadoFacturado()) {
            holder.estadoFacturado.setVisibility(View.VISIBLE);
            holder.estadoFacturado.setBackgroundResource(R.drawable.ic_facturado);
        }else {
            holder.estadoFacturado.setVisibility(View.INVISIBLE);
        }

         /*Segun si esta finalizada la reparacion o no,el fondo de el circulito que indica el numero de reparacion tendra un color
         estadoDeReparacion= true -> color verde, es decir (finalizada)
         estadoDeReparacion  = false -> color rojo (en curso o sin finalizar)*/
        if (listReparacion.get(position).getEstadoReparacion()) {
            holder.tvNumeroReparacion.setBackgroundResource(R.drawable.circulo_lista_repa_ok);
        } else if(!listReparacion.get(position).getEstadoReparacion()){
            holder.tvNumeroReparacion.setBackgroundResource(R.drawable.ciruclo_lista_repa_no_ok);
        }

        /*Con el click editamos un elemento(Aun que reparaciones no tiene permitido editarse, por lo que se mostrara al usuario infomacion de todas las reparaciones de ese mismo dia sobre el mismo vehiculo a el mismo cliente)*/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posReparacionSelecionada = holder.getAdapterPosition();
                Reparacion reparacionBuscada = listReparacion.get(posReparacionSelecionada);
                //ROOM
                ReparacionRepositories.getInstance().setListReparacionesComunes(reparacionBuscada.getFecha(),reparacionBuscada.getMatriculaCoche());
                //region NO ROOOM
                //listRepaMismoCliyFecha = new ArrayList<Reparacion>();
                //Buscamos elementos de la lista con el  mismo Cliente,fecha y matricula de reparacion.
                /*for (Reparacion item : listReparacion) {
                    if (item.getNombreCliente() == reparacionBuscada.getNombreCliente() && item.getFecha().equals(reparacionBuscada.getFecha()) && item.getMatriculaCoche().equals(reparacionBuscada.getMatriculaCoche())) {
                        listRepaMismoCliyFecha.add(item);//Lista de reparaciones con mismo cliente en una misma fecha con una misma matricula(es decir reparacion sobre la que mas tarde se emitira una factura)
                    }
                }
                if(listRepaMismoCliyFecha.size()>0) {
                    //Cargamos en el repositorio esta lista de reparaciones concretas de un cliente en una determinada fecha sobre un mismo vehiculo en repository para recogerla conReparacionDetailListAdapter
                    //ReparacionRepositories.getInstance().setListReparacionesComunes(listRepaMismoCliyFecha);

                }*/
                //endregion
                manipularDatos.miClick();
            }
        });

        /*Con el long click eliminamos un elemento(POR AHORA)*/
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //Si el imagenButton llamado "estadoFacturado" NO esta visible, es que esa reapracion no esta facturada, por lo que puede ser eliminada
              /* if (holder.estadoFacturado.getVisibility() != View.VISIBLE) {
                    //Eliminamos el elemento de la lista del Adapter
                    listReparacion.remove(holder.getAdapterPosition());
                    //Pasamos la posicion del elemento en la lista para eliminar el elemento de la lista del Repositorio
                    manipularDatos.miOnLOngClick(holder.getAdapterPosition());
                    Log.d("PRUEBA", "ADAPTER: clic LARGO posicion " + holder.getAdapterPosition());
                }*/
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listReparacion.size();
    }

    /*Implementado por la interfaz AdapterContrac.BaseAdapterContract.ContractAdapterReparacion */
    @Override
    public Reparacion eliminar(int position){
        Reparacion repaBorrar= listReparacion.get(position);//Obtenemos el objeto que vamos a borrar para devolverlo
        listReparacion.remove(position);
        notifyItemRemoved(position);
        return repaBorrar;

    }
    //Una vez confirmada la eliminacion desde el la ventana que muestra el alert dialog
    @Override
    public void confirmarBorrado(int position){
        manipularDatos.confirmarBorrado(position);//Este informa a la vista de que se confirman el borrado
    }

    //Cuando se le da a "NO" en el alerDialog, volvemos a reinsertar el elemento en el reciclerView
    public void cancelacionDeBorrado(int position){
        notifyItemChanged(position);
    }

    //cuando se ha comfirmado el borrado en el alerDialog y se pulsa deshacer desde snackbar
    public void deshacerBorrado(int position, Reparacion r){
        Log.d("DeshacerBorrado","tamanio antes de restaurar  "+listReparacion.size());
        Log.d("DeshacerBorrado","Restaurar posicion "+position+" objeto"+ r.getNumeroReparacion());
        listReparacion.add(position,r);
        notifyDataSetChanged();//Arregla el fallo de "notifyItemInserted(position)" de la linea de abajo
        //notifyItemInserted(position);//TODO  ERROR con esta linea Se restaura pero no se notifica en la vista cuando se elemina el 1º elemento
        Log.d("DeshacerBorrado","tamanio despues de restaurar  "+listReparacion.size());
    }

    @Override
    public boolean estaFacturado(int position) {
        return listReparacion.get(position).getEstadoFacturado();
    }

    //Devuelve un elemento concreto de la lista
    public Reparacion getItemList(int pos)
    {
        Reparacion reparacion= listReparacion.get(pos);
        return reparacion;
    }

    public void add(Reparacion reparacion) {
        this.listReparacion.add(reparacion);
    }

    public void addAll(List<Reparacion> list) {
        this.listReparacion.addAll(list);
    }
    /*--------------------------------------------------------------------*/

    /*Clase interna en la que definimos nuestro propio ViewHolder*/
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNumeroReparacion;
        TextView tvMatriculaCoche;
        TextView tvFecha;
        //TODO la idea es que este ImgenButtom tenga un evento click el cual si se lanza llevará al usuario al la información de esa factura(todas las reparaciones de ese dia para ese vehiculo)
        ImageButton estadoFacturado;

        //Constructor
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Asignamos/Asociamos componentes del item_reparacion_list_view al holder del recyclerView fragment_reparacion_list_view
            tvNumeroReparacion = itemView.findViewById(R.id.tvNumeroReparacion);
            tvMatriculaCoche = itemView.findViewById(R.id.tvMatriculaCoche);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            estadoFacturado = itemView.findViewById(R.id.estadoFacturado);
        }

    }

    /*Interfaz implementadas para los metodos que necesitamos ejecutar en el evento onClick o onLongClick y confirmacionDeBorrado (para snackbar de la vista que deshace borrados) de el Adapter*/
    public interface manipularDatos {
        void miOnLOngClick(int posicion);
        void miClick();//Envia al usuario a un listado de las reparaciones con todo detalle que recibio un cliente en una fecha para un vehiculo concreto
        void confirmarBorrado(int adapterPosition);//Este metodo comunica con  La clase TouchCallback con la clase  ReparacionListView a través de la clase ReparacionListAdapter
    }





}

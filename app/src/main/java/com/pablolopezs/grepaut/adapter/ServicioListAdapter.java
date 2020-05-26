package com.pablolopezs.grepaut.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pablolopezs.grepaut.R;
import com.pablolopezs.grepaut.data.model.Reparacion;
import com.pablolopezs.grepaut.data.model.Servicio;
import com.pablolopezs.grepaut.data.repositories.ReparacionRepositories;

import java.util.ArrayList;

public class ServicioListAdapter extends RecyclerView.Adapter<ServicioListAdapter.ViewHolder>implements AdapterContrac.BaseAdapterContract.ContractAdapterServicio {

    private ArrayList<Servicio> list;
    private manipularDatos manipularDatos;
    public ServicioListAdapter(manipularDatos manipularDatos){
        this.list=new ArrayList<Servicio>();
        this.manipularDatos=manipularDatos;
    }


    public void add(Servicio servicio)
    {
        this.list.add(servicio);
    }

    public void  addAll(ArrayList<Servicio> list)
    {
        this.list.addAll(list);
        Log.d("SERVICIO",this.list.get(0).getNombre());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_servicio_list_view, parent, false);
        Log.d("SERVICIO", "ADAPTER: onCreateViewHolder()");
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvNombreServicio.setText(list.get(position).getNombre());
        holder.tvPrecio.setText(Double.toString(list.get(position).getPrecio())+"€");

        //Envento click de  un item del reciclerView
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //Envento longclick de  un item del reciclerView
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
//region Metodos implmentados por la interfaz AdapterContrac.BaseAdapterContract.ContractAdapterServicio
    @Override
    public Servicio eliminar(int position) {
        Servicio sBorrar =list.get(position);
        list.remove(position);
        notifyItemRemoved(position);
        return sBorrar;
    }

    @Override
    public void confirmarBorrado(int adapterPosition) {
        manipularDatos.confirmarBorrado(adapterPosition);//Llama al confirmar borrador implmentado en la vista ServicioListView
    }

    @Override
    public boolean estaEnUnaReparacion(int position) {
        Servicio s = list.get(position);
        for(Reparacion r : ReparacionRepositories.getInstance().getList())
        {
            if(s.getNombre()==r.getNombreServicio())//Si encuntre que este servicio esta dado de alta en una reparación, este no podra ser borrado
            {
                return true;
            }
        }
        return false;
    }

    //----Fin implementacion de la interfaz AdapterContrac.BaseAdapterContract.ContractAdapterServicio---/
//endregion

    //Cuando se le da a "NO" en el alerDialog, volvemos a reinsertar el elemento en el reciclerView
    public void cancelacionDeBorrado(int position){
        notifyItemChanged(position);//Notificamos el cambio al recicler para que actualice el elemento (que se desplazo hacia el lado ) donde estaba
    }

    //cuando se ha comfirmado el borrado en el alerDialog y se pulsa deshacer desde snackbar
    public void deshacerBorrado(int position, Servicio s){
        Log.d("DeshacerBorrado","tamanio antes de restaurar  "+ list.size());
        Log.d("DeshacerBorrado","Restaurar posicion "+position+" objeto"+ s.getNombre());
        list.add(position,s);
        notifyDataSetChanged();//Arregla el fallo de "notifyItemInserted(position)" de la linea de abajo
        //notifyItemInserted(position);//TODO  ERROR con esta linea Se restaura pero no se notifica en la vista cuando se elemina el 1º elemento
        Log.d("DeshacerBorrado","tamanio despues de restaurar  "+list.size());
    }

    /*Interfaz implementadas para los metodos que necesitamos ejecutar en el evento onClick o onLongClick y confirmacionDeBorrado (para snackbar de la vista que deshace borrados) de el Adapter*/
    public interface manipularDatos {
        void miOnLOngClick(int posicion);
        void miClick();//Envia al usuario a una nueva vista con todos  los detalle de un cliente y la posibilidad de editar o modificarlo
        void confirmarBorrado(int adapterPosition);//Este metodo comunica con  La clase TouchCallback con la clase  ClienteListView a través de la clase ClienteListAdapter
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNombreServicio;
        TextView tvPrecio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreServicio=itemView.findViewById(R.id.tvNombreServicio);
            tvPrecio=itemView.findViewById(R.id.tvPrecio);
        }
    }
}

package com.pablolopezs.grepaut.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pablolopezs.grepaut.R;
import com.pablolopezs.grepaut.data.model.Cliente;
import com.pablolopezs.grepaut.data.model.Reparacion;
import com.pablolopezs.grepaut.data.repositories.ReparacionRepositories;

import java.util.ArrayList;
import java.util.List;

public class ClienteListAdapter extends RecyclerView.Adapter<ClienteListAdapter.ViewHolder> implements AdapterContrac.BaseAdapterContract.ContractAdapterCliente {
    private ArrayList<Cliente> listClientes;
    private manipularDatos manipularDatos;

    public ClienteListAdapter(manipularDatos manipularDatos) {
        this.listClientes = new ArrayList<Cliente>();
        this.manipularDatos=manipularDatos;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cliente_list_view, parent, false);
        Log.d("PRUEBA", "ADAPTER: onCreateViewHolder()");
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.tvNombreApellidos.setText("Nombre: "+ listClientes.get(position).getNombre());
        holder.tvMatriculaCoche.setText("Matricula: "+ listClientes.get(position).getMatriculaCoche());
        holder.tvTlf.setText("Tlf:"+listClientes.get(position).getTlf());
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
                manipularDatos.miOnLOngClick(holder.getAdapterPosition());
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.listClientes.size();
    }

//region Metodos implementados por la interfaz AdapterContrac.BaseAdapterContract.ContractAdapterCliente

    @Override
    public Cliente eliminar(int position) {
        Cliente cBorrar =listClientes.get(position);
       listClientes.remove(position);
        notifyItemRemoved(position);
       return cBorrar;
    }

    @Override
    public void confirmarBorrado(int adapterPosition) {
        manipularDatos.confirmarBorrado(adapterPosition);//Llama al confirmar borrado implementado en la vista ClienteListView
    }

    @Override
    public boolean tieneReparacion(int position) {
        Cliente cBuscar =listClientes.get(position);
        //Buscamos en la lista de reparaciones si ese cliente con ese coche tiene una reparacion ya creada
        for (Reparacion reparacion : ReparacionRepositories.getInstance().getList()){
                //Si el cliente que intentamos borrar tiene ya creada una reparacion,NO podra ser borrado, en caso contrario si podra
            if(cBuscar.getMatriculaCoche().equals(reparacion.getMatriculaCoche()))
            {
                return  true;
            }
        }
        return false;
    }

    //Devuelve un elemento concreto de la lista
    public Cliente getItemList(int pos)
    {
        Cliente cliente= listClientes.get(pos);
        return cliente;
    }

    public void add(Cliente cliente)
    {
        this.listClientes.add(cliente);
    }

    public void  addAll(List<Cliente> list)
    {
        this.listClientes.addAll(list);
    }
//--------------Fin metodos implementados por la interfaz--------------------//
//endregion

    //Cuando se le da a "NO" en el alerDialog, volvemos a reinsertar el elemento en el reciclerView
    public void cancelacionDeBorrado(int position){
        notifyItemChanged(position);//Notificamos el cambio al recicler para que actualice el elemento (que se desplazo hacia el lado ) donde estaba
    }

    //cuando se ha comfirmado el borrado en el alerDialog y se pulsa deshacer desde snackbar
    public void deshacerBorrado(int position, Cliente c){
        Log.d("DeshacerBorrado","tamanio antes de restaurar  "+ listClientes.size());
        Log.d("DeshacerBorrado","Restaurar posicion "+position+" objeto"+ c.getMatriculaCoche());
        listClientes.add(position,c);
        notifyDataSetChanged();//Arregla el fallo de "notifyItemInserted(position)" de la linea de abajo
        //notifyItemInserted(position);//TODO  ERROR con esta linea Se restaura pero no se notifica en la vista cuando se elemina el 1º elemento
        Log.d("DeshacerBorrado","tamanio despues de restaurar  "+listClientes.size());
    }


    /*Interfaz implementadas para los metodos que necesitamos ejecutar en el evento onClick o onLongClick y confirmacionDeBorrado (para snackbar de la vista que deshace borrados) de el Adapter*/
    public interface manipularDatos {
        void miOnLOngClick(int posicion);
        void miClick();//Envia al usuario a una nueva vista con todos  los detalle de un cliente y la posibilidad de editar o modificarlo
        void confirmarBorrado(int adapterPosition);//Este metodo comunica con  La clase TouchCallback con la clase  ClienteListView a través de la clase ClienteListAdapter
    }

    /*Clase interna en la que definimos nuestro propio ViewHolder*/
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreApellidos;
        TextView tvMatriculaCoche;
        TextView tvTlf;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreApellidos = itemView.findViewById(R.id.tvNombreApellidos);
            tvMatriculaCoche = itemView.findViewById(R.id.tvMatriculaCoche);
            tvTlf=itemView.findViewById(R.id.tvtlf);
        }
    }

}

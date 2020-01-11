package com.pablolopezs.grepaut.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.pablolopezs.grepaut.R;
import com.pablolopezs.grepaut.data.model.Cliente;

import java.util.ArrayList;

public class ClienteListAdapter extends RecyclerView.Adapter<ClienteListAdapter.ViewHolder> {
    private ArrayList<Cliente> list;

    public ClienteListAdapter() {
        this.list = new ArrayList<Cliente>();
    }

    public void add(Cliente cliente)
    {
        this.list.add(cliente);
    }

    public void  addAll(ArrayList<Cliente> list)
    {
        this.list.addAll(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cliente_list_view, parent, false);
        Log.d("PRUEBA", "ADAPTER: onCreateViewHolder()");
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvIdCliente.setText(Integer.toString(list.get(position).getId()));
        holder.tvNombreApellidos.setText("Nombre: "+list.get(position).getNombre()+" "+list.get(position).getApellidos());
        holder.tvMatriculaCoche.setText("Matricula: "+list.get(position).getMatriculaCoche());
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    /*Clase interna en la que definimos nuestro propio ViewHolder*/
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvIdCliente;
        TextView tvNombreApellidos;
        TextView tvMatriculaCoche;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIdCliente = itemView.findViewById(R.id.tvIdCliente);
            tvNombreApellidos = itemView.findViewById(R.id.tvNombreApellidos);
            tvMatriculaCoche = itemView.findViewById(R.id.tvMatriculaCoche);
        }
    }
}

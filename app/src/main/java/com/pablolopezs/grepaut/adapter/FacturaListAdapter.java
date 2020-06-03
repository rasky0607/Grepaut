package com.pablolopezs.grepaut.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pablolopezs.grepaut.R;
import com.pablolopezs.grepaut.data.model.Factura;

import java.util.ArrayList;

public class FacturaListAdapter extends RecyclerView.Adapter<FacturaListAdapter.ViewHolder> implements AdapterContrac.BaseAdapterContract.ContractAdapterFactura {

    private ArrayList<Factura>listFacturas;

    public FacturaListAdapter(){
        this.listFacturas=new ArrayList<Factura>();
    }

    //Inflamos la vista y la convertimos en ViewHolder(Es decir un bloque del listado del recyclerView
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_factura_list_view, parent, false);
        Log.d("Factura", "ADAPTER: onCreateViewHolder()");
        return new ViewHolder(v);
    }

    /*Inyectamos los datos a cada ViewHolder*/
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvNumeroFactura.setText(Integer.toString(listFacturas.get(position).getNumeroFactura()));
        holder.tvFechaFacturacion.setText("Fecha facturación: "+listFacturas.get(position).getFechaFacturacion());
        holder.tvFechaReparacion.setText("Fecha reparación: "+listFacturas.get(position).getFechaReparacion());
        holder.tvMatriculaCoche.setText("Matrícula: "+listFacturas.get(position).getMatriculaCocheRepara());
    }

    @Override
    public int getItemCount() {
        return this.listFacturas.size();
    }
    //region Interfaz implementada por AdapterContrac.BaseAdapterContract.ContractAdapterFactura
    @Override
    public Factura eliminar(int position) {
        return null;
    }

    @Override
    public void confirmarBorrado(int adapterPosition) {

    }

    @Override
    public Factura getItemList(int pos) {
        return null;
    }

    @Override
    public void add(Factura objeto) {
        this.listFacturas.add(objeto);
    }

    @Override
    public void addAll(ArrayList<Factura> list) {
        this.listFacturas.addAll(list);
    }

    @Override
    public boolean facturaAnulada(Factura factura) {
        return false;
    }
    //endregion

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvNumeroFactura;
        TextView tvMatriculaCoche;
        TextView tvFechaReparacion;
        TextView tvFechaFacturacion;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNumeroFactura=itemView.findViewById(R.id.tvNumeroFactura);
            tvMatriculaCoche=itemView.findViewById(R.id.tvMatriculaCoche);
            tvFechaReparacion=itemView.findViewById(R.id.tvFechaReparacion);
            tvFechaFacturacion=itemView.findViewById(R.id.tvFechaFacturacion);
        }
    }
}

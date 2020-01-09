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

/**
 * Esta clase  listara los detalles de las reparaciones realizadas
 * a un determinado cliente en una determinada fecha a un determinado vehiculo de este, tras hacer click
 * en una de las reparaciones del listado del layout fragment_reparacion_list_view ejecutado sobre la clase ReparacionListView*/
public class ReparacionDetailListAdapter extends RecyclerView.Adapter<ReparacionDetailListAdapter.ViewHolder> {

    private ArrayList<Reparacion> list;

    public ReparacionDetailListAdapter() {
        this.list= new ArrayList<Reparacion>();
    }

    public void addAll(ArrayList<Reparacion> list) {
        this.list.addAll(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reparacion_list_detail_view,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvNumeroReparacion.setText(Integer.toString(list.get(position).getNumeroReparacion()));
        holder.tvFecha.setText("Fecha: "+list.get(position).getFecha());
        holder.tvMatriculaCoche.setText("Matricula: "+list.get(position).getMatriculaCoche());
        holder.tvNomCliente.setText("Cliente: "+list.get(position).getNombreCliente());
        holder.tvNomServicio.setText("Servicio: "+list.get(position).getNombreServicio());
        holder.tvNombreUsuarioMecanico.setText("Técnico: "+list.get(position).getNombreUsuario());

         /*Segun si esta factura o no, el fondo tendra ImagenButtom visible o invisible
         facturada= true -> visible
         facturada  = false -> invisible */
        if (list.get(position).getEstadoFacturado()) {
            //holder.listReparacionItem.setBackgroundResource(R.color.colorReparacionSiFacturada);
            holder.estadoFacturado.setVisibility(View.VISIBLE);
            holder.estadoFacturado.setBackgroundResource(R.drawable.ic_facturado);

        } else {
            //holder.listReparacionItem.setBackgroundResource(R.color.colorReparacionNoFacturarada);
            holder.estadoFacturado.setVisibility(View.INVISIBLE);
        }

         /*Segun si esta finalizada la reparacion o no,el fondo de el circulito que indica el numero de reparacion tendra un color
         estadoDeReparacion= true -> color verde, es decir (finalizada)
         estadoDeReparacion  = false -> color rojo (en curso o sin finalizar)*/
        if (list.get(position).getEstadoReparacion()) {
            //holder.icon.setShapeColor(R.color.colorReparacionFinalizada);
            holder.tvNumeroReparacion.setBackgroundResource(R.drawable.circulo_lista_repa_ok);

        } else {
            //holder.icon.setShapeColor(R.color.colorReparacionNoFinalizada);
            holder.tvNumeroReparacion.setBackgroundResource(R.drawable.ciruclo_lista_repa_no_ok);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNumeroReparacion;
        ImageButton estadoFacturado;
        TextView tvFecha;
        TextView tvMatriculaCoche;
        TextView tvNomCliente;
        TextView tvNomServicio;
        TextView tvNombreUsuarioMecanico;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNumeroReparacion = itemView.findViewById(R.id.tvNumeroReparacion);
            estadoFacturado = itemView.findViewById(R.id.estadoFacturado);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            tvMatriculaCoche = itemView.findViewById(R.id.tvMatriculaCoche);
            tvNomCliente = itemView.findViewById(R.id.tvNomCliente);
            tvNomServicio = itemView.findViewById(R.id.tvNomServicio);
            tvNombreUsuarioMecanico = itemView.findViewById(R.id.tvNombreUsuarioMecanico);
        }
    }
}

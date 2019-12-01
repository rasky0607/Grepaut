package com.pablolopezs.grepaut.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pablolopezs.grepaut.R;
import com.github.ivbaranov.mli.MaterialLetterIcon;
import com.pablolopezs.grepaut.data.model.Reparacion;
import com.pablolopezs.grepaut.data.repository.ReparacionRepository;

import java.util.ArrayList;

public class ReparacionAdapter extends RecyclerView.Adapter<ReparacionAdapter.ViewHolder> {

    private ArrayList<Reparacion>listReparacion;

    //Constructor
    public  ReparacionAdapter(){
        listReparacion = (ArrayList<Reparacion>) ReparacionRepository.getInstance().getList();
        Log.d("PRUEBA", "ADAPTER: Construcor "+listReparacion.get(0));

    }

    //Inflamos la vista y la convertimos en ViewHolder(Es decir un bloque del listado del recyclerView
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.reparacion_item,parent,false);
        return new ViewHolder(v);
    }
//TODO Pendiente de revision, no da error, pero tampoco m uestra el recicler view
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.icon.setLetter(Integer.toString(listReparacion.get(position).getNumeroReparacion()));
        holder.tvServicio.setText(listReparacion.get(position).getNombreServicio());
        holder.tvNomCliente.setText(listReparacion.get(position).getIdcliente());
        holder.tvMatriculaCoche.setText(listReparacion.get(position).getMatriculaCoche());
        holder.tvFecha.setText(listReparacion.get(position).getFecha());
        Log.d("PRUEBA", "ADAPTER: onBindViewHolder()"+ holder.tvFecha.getText());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    //Clase intertna
    class ViewHolder extends RecyclerView.ViewHolder{
        MaterialLetterIcon icon;
        TextView tvServicio;
        TextView tvNomCliente;
        TextView tvMatriculaCoche;
        TextView tvFecha;


        //Constructor
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Asignamos/Asociamos componentes del reparacion_item al holder del recyclerView fragment_reparacion
            icon = itemView.findViewById(R.id.icon);
            tvServicio=itemView.findViewById(R.id.tvServicio);
            tvNomCliente=itemView.findViewById(R.id.tvNomCliente);
            tvMatriculaCoche=itemView.findViewById(R.id.tvMatriculaCoche);
            tvFecha=itemView.findViewById(R.id.tvFecha);
            Log.d("PRUEBA", "Clase interna ViewHolder");

        }

    }

}

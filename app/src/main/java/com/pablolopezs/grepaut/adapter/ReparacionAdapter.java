package com.pablolopezs.grepaut.adapter;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
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
        Log.d("PRUEBA", "ADAPTER: Construcor "+listReparacion.get(0).getNombreEmpresa());

    }

    //Inflamos la vista y la convertimos en ViewHolder(Es decir un bloque del listado del recyclerView
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.reparacion_item,parent,false);
        Log.d("PRUEBA", "ADAPTER: onCreateViewHolder()");
        return new ViewHolder(v);
    }
//TODO Pendiente de revision, no da error, pero tampoco muestra el recicler view
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //holder.icon.setLetter(Integer.toString(listReparacion.get(position).getNumeroReparacion()));
        //holder.tvServicio.setText(listReparacion.get(position).getNombreServicio());
        //holder.tvNomCliente.setText(Integer.toString(listReparacion.get(position).getIdcliente()));
        holder.tvNumeroReparacion.setText(Integer.toString(listReparacion.get(position).getNumeroReparacion()));
        holder.tvFecha.setText("Fecha: "+listReparacion.get(position).getFecha());
        holder.tvMatriculaCoche.setText("Matricula: "+listReparacion.get(position).getMatriculaCoche());


        /*Segun si esta factura o no, el fondo tendra ImagenButtom visible o invisible
         facturada= true -> visible
         facturada  = false -> invisible */
        if(listReparacion.get(position).getEstadoFacturado())
        {
            //holder.listReparacionItem.setBackgroundResource(R.color.colorReparacionSiFacturada);
            holder.estadoFacturado.setVisibility(View.VISIBLE);
            holder.estadoFacturado.setBackgroundResource(R.drawable.ic_facturado);

        }
        else
        {
            //holder.listReparacionItem.setBackgroundResource(R.color.colorReparacionNoFacturarada);
            holder.estadoFacturado.setVisibility(View.INVISIBLE);
        }

         /*Segun si esta finalizada la reparacion o no,el fondo de el circulito que indica el numero de reparacion tendra un color
         estadoDeReparacion= true -> color verde, es decir (finalizada)
         estadoDeReparacion  = false -> color rojo (en curso o sin finalizar)*/
        if(listReparacion.get(position).getEstadoReparacion())
        {
            //holder.icon.setShapeColor(R.color.colorReparacionFinalizada);
            holder.tvNumeroReparacion.setBackgroundResource(R.drawable.circulo_lista_repa_ok);

        }
        else{
            //holder.icon.setShapeColor(R.color.colorReparacionNoFinalizada);
            holder.tvNumeroReparacion.setBackgroundResource(R.drawable.ciruclo_lista_repa_no_ok);
        }

        Log.d("PRUEBA", "ADAPTER: onBindViewHolder()"+ holder.tvFecha.getText());

    }

    @Override
    public int getItemCount() {
        return listReparacion.size();
    }

    //Clase intertna
    class ViewHolder extends RecyclerView.ViewHolder{
        //MaterialLetterIcon icon;
        //TextView tvServicio;
        //TextView tvNomCliente;
        TextView tvNumeroReparacion;
        TextView tvMatriculaCoche;
        TextView tvFecha;
        ImageButton estadoFacturado;//TODO la idea es que este ImgenButtom tenga un evento click el cual si se lanza llevará al usuario al la información de esa factura(todas las reparaciones de ese dia para ese vehiculo)
        ConstraintLayout listReparacionItem;



        //Constructor
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Asignamos/Asociamos componentes del reparacion_item al holder del recyclerView fragment_reparacion
            //icon = itemView.findViewById(R.id.icon);
            //tvServicio=itemView.findViewById(R.id.tvServicio);
            //tvNomCliente=itemView.findViewById(R.id.tvNomCliente);
            tvNumeroReparacion = itemView.findViewById(R.id.tvNumeroReparacion);
            tvMatriculaCoche=itemView.findViewById(R.id.tvMatriculaCoche);
            tvFecha=itemView.findViewById(R.id.tvFecha);
            listReparacionItem = itemView.findViewById(R.id.listReparacionItem);
            estadoFacturado=itemView.findViewById(R.id.estadoFacturado);
            Log.d("PRUEBA", "Clase interna ViewHolder");

        }

    }

}

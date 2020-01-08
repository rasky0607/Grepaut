package com.pablolopezs.grepaut.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.pablolopezs.grepaut.R;
import com.pablolopezs.grepaut.data.model.Reparacion;
import com.pablolopezs.grepaut.data.repositories.ReparacionRepositories;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Objects;

public class ReparacionListAdapter extends RecyclerView.Adapter<ReparacionListAdapter.ViewHolder> {

    private ArrayList<Reparacion>listReparacion;
    private  manipularDatos manipularDatos;
    //Constructor
    public ReparacionListAdapter(manipularDatos manipularDatos){

        this.listReparacion = new ArrayList<Reparacion>();
        this.manipularDatos=manipularDatos;
        //this.listReparacion = (ArrayList<Reparacion>) ReparacionRepositories.getInstance().getList();
       // Log.d("PRUEBA", "ADAPTER: Construcor "+listReparacion.get(0).getNombreEmpresa());


    }

    public void add(Reparacion reparacion){
        this.listReparacion.add(reparacion);
    }

    public void addAll(ArrayList<Reparacion> list)
    {
        this.listReparacion.addAll(list);
    }




    //Inflamos la vista y la convertimos en ViewHolder(Es decir un bloque del listado del recyclerView
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reparacion_list_view,parent,false);
        Log.d("PRUEBA", "ADAPTER: onCreateViewHolder()");
        return new ViewHolder(v);
    }

    /*Inyectamos los datos a cada ViewHolder*/
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

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

        /*Con el click editamos un elemento(Aun que reparaciones no tiene permitido editarse, pero se enviara u n mensaje al usuario de recordatorio)*/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //manipularDatos.miClick(listReparacion.get(holder.getAdapterPosition()));
                manipularDatos.miClick();
                //manipularDatos.miClick("NO se puede editar una reparación, eliminela y cree una nueva.");
            }
        });

        /*Con el  long click eliminamos un elemento*/
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //Si el imagenButton llamado "estadoFacturado" NO esta visible, es que esa reapracion no esta facturada, por lo que puede ser eliminada
                if(holder.estadoFacturado.getVisibility()!= View.VISIBLE) {
                    //Eliminamos el elemento de la lista del Adapter
                    listReparacion.remove(holder.getAdapterPosition());
                    //Pasamos la posicion del elemento en la lista para eliminar el elemento de la lista del Repositorio
                    manipularDatos.miOnLOngClick(holder.getAdapterPosition());
                    Log.d("PRUEBA", "ADAPTER: clic LARGO posicion " + holder.getAdapterPosition());
                }
                return false;
            }
        });
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
        //TODO la idea es que este ImgenButtom tenga un evento click el cual si se lanza llevará al usuario al la información de esa factura(todas las reparaciones de ese dia para ese vehiculo)
        ImageButton estadoFacturado;
        ConstraintLayout listReparacionItem;



        //Constructor
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Asignamos/Asociamos componentes del item_reparacion_list_view al holder del recyclerView fragment_reparacion_list_view
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

    /*Interfaz implementadas para los metodos que necesitamos ejecutar en el evento onClick o onLongClick de el Adapter*/
    public  interface manipularDatos{
        void miOnLOngClick(int posicion);
        void miClick();//Envia un mensaje informando de que no puede editarse un regiistro de reparacion.
    }

}
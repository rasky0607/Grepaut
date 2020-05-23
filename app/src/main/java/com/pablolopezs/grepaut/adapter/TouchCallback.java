package com.pablolopezs.grepaut.adapter;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

/*Esta clase controla el deslizamiento de los item del reciclerView y el efecto que conlleva esta accion,
 en ete caso eliminar el item del reciclerView cuando se desliza el item hacia la izquierda y se confirma la eliminacion*/
public class TouchCallback extends ItemTouchHelper.Callback {
    private AdapterContrac.BaseAdapterContract.ContractAdapterCliente mAdapterCliente= null;
    private AdapterContrac.BaseAdapterContract.ContractAdapterReparacion mAdapterReparacion= null;
    private AdapterContrac.BaseAdapterContract.ContractAdapterServicio mAdapterServicio= null;


    //Constructor
    public TouchCallback(AdapterContrac.BaseAdapterContract adapter){
        if(adapter instanceof AdapterContrac.BaseAdapterContract.ContractAdapterCliente)
        {
            mAdapterCliente = (AdapterContrac.BaseAdapterContract.ContractAdapterCliente)adapter;
        }
        if(adapter instanceof AdapterContrac.BaseAdapterContract.ContractAdapterReparacion)
        {
            mAdapterReparacion =(AdapterContrac.BaseAdapterContract.ContractAdapterReparacion) adapter;
        }
        if(adapter instanceof AdapterContrac.BaseAdapterContract.ContractAdapterServicio)
        {
            mAdapterServicio =(AdapterContrac.BaseAdapterContract.ContractAdapterServicio) adapter;
        }

    }

    //A la hora de efectuar un movimiento con algun item(ya sea desplazar arrastrar etc
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        final int dragFlags = ItemTouchHelper.ACTION_STATE_IDLE;
        int swipeFlags = ItemTouchHelper.ACTION_STATE_IDLE;

        //permite el desplazamiento de un item hacia la izquierda
        if(mAdapterReparacion instanceof AdapterContrac.BaseAdapterContract.ContractAdapterReparacion) {
            if (!mAdapterReparacion.estaFacturado(viewHolder.getAdapterPosition())) {
                swipeFlags = ItemTouchHelper.LEFT;
            }
        }
        if(mAdapterCliente instanceof AdapterContrac.BaseAdapterContract.ContractAdapterCliente){
            if(!mAdapterCliente.tieneReparacion(viewHolder.getAdapterPosition())) {
                swipeFlags = ItemTouchHelper.LEFT;
            }
        }
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    //Efecto drag(Despazamiento hacia arriba o hacia abajo)
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }
//En caso de realizarse onSwiped/deslizamiento DEcide que acion se toma segun la direcion que le llega
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        //Hacia la izquierda borramos el elemento
        if( mAdapterReparacion instanceof AdapterContrac.BaseAdapterContract.ContractAdapterReparacion) {
            if (direction == ItemTouchHelper.LEFT) {
                mAdapterReparacion.confirmarBorrado(viewHolder.getAdapterPosition());
            }
        }
        //POR AQUI(POR PROBAR)
        if( mAdapterCliente instanceof AdapterContrac.BaseAdapterContract.ContractAdapterCliente) {
            //Si el cliente no tiene  una reparacion ya creada, podra borrarse, de lo contrario, NO podrá
            if (direction == ItemTouchHelper.LEFT) {
                mAdapterCliente.confirmarBorrado(viewHolder.getAdapterPosition());
            }
        }

    }
//Efecto de transicion ocurrido tras efectuar el deslizamientoo el desplazamiento(drag)
    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        final float ALPHA_FULL = 1.0f;
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            // Fade out the view as it is swiped out of the parent's bounds
            final float alpha = ALPHA_FULL - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
            viewHolder.itemView.setAlpha(alpha);
            viewHolder.itemView.setTranslationX(dX);
        } else {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    }


}

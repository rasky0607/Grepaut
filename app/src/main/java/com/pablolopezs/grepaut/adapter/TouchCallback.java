package com.pablolopezs.grepaut.adapter;

import android.graphics.Canvas;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class TouchCallback extends ItemTouchHelper.Callback {
    private AdapterContrac.ContractAdapterCliente mAdapterCliente= null;
    private AdapterContrac.ContractAdapterReparacion mAdapterReparacion= null;
    private AdapterContrac.ContractAdapterServicio mAdapterServicio= null;

    public TouchCallback(AdapterContrac.BaseAdapterContract adapter){
        if(adapter instanceof AdapterContrac.ContractAdapterCliente)
        {
            mAdapterCliente = (AdapterContrac.ContractAdapterCliente)adapter;
        }
        if(adapter instanceof AdapterContrac.ContractAdapterReparacion)
        {
            mAdapterReparacion =(AdapterContrac.ContractAdapterReparacion) adapter;
        }
        if(adapter instanceof AdapterContrac.ContractAdapterServicio)
        {
            mAdapterServicio =(AdapterContrac.ContractAdapterServicio) adapter;
        }

    }

    //A la hora de efectuar un movimiento con algun item(ya sea desplazar arrastrar etc
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        final int dragFlags = ItemTouchHelper.ACTION_STATE_IDLE;
        int swipeFlags = ItemTouchHelper.ACTION_STATE_IDLE;
        if(!mAdapterReparacion.estaFacturado(viewHolder.getAdapterPosition())) {
            swipeFlags = ItemTouchHelper.LEFT;
        }
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    //Efecto drag(Despazamiento hacia arriba o hacia abajo)
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }
//En caso de realizarse onSwiped/deslizamiento DEcide que hacion se toma segun la direcion que le llega
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        if(direction == ItemTouchHelper.LEFT && !mAdapterReparacion.estaFacturado(viewHolder.getAdapterPosition()))
            mAdapterReparacion.remove(viewHolder.getAdapterPosition());
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

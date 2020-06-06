package com.pablolopezs.grepaut.ui.factura;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pablolopezs.grepaut.R;
import com.pablolopezs.grepaut.adapter.FacturaListAdapter;
import com.pablolopezs.grepaut.data.model.Factura;
import com.pablolopezs.grepaut.ui.MainActivity.MainActivity;

import java.util.ArrayList;
import java.util.List;

/*Muestra un listado de las facturas existentes, tanto anuladas como vigentes, y estas no puden ser editadas ni borradas, solo anuladas para crear otra factura nueva que referencie a la anulada*/
public class FacturaListView extends Fragment implements FacturaListContract.View {

    public static final String TAG = "FacturaListView";
    FacturaListContract.Presenter presenter;
    private RecyclerView rvFactura;
    private FacturaListAdapter facturaListAdapter;

    public static FacturaListView newInstance() {
        FacturaListView fragment = new FacturaListView();
        return fragment;
    }

    //Para mantener la selecion de las opciones Drawer
    @Override
    public void onResume() {
        super.onResume();
        if(((MainActivity)getActivity()).navigationView.getCheckedItem().getItemId()==R.id.nav_facturas)
        {
            ((MainActivity)getActivity()).navigationView.setCheckedItem(R.id.nav_facturas);
            (getActivity()).setTitle(R.string.menu_facturas);
            ((MainActivity)getActivity()).ocultarMostrarFloatinButtom();
        }
    }
    /*Crea/Infla la vista*/
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_factura_list_view, container, false);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /*Justo despues de crear la vista*/
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Para mantener los datos o estado al girar la actividad
        setRetainInstance(true);
        rvFactura = view.findViewById(R.id.rvFactura);
        inicializarRvFactura();
        presenter.cargarDatos();
    }

    private void inicializarRvFactura(){
        facturaListAdapter = new FacturaListAdapter();
        rvFactura.setAdapter(facturaListAdapter);
        rvFactura.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    //region implementacion de la interfaz FacturaListContract.View
    @Override
    public void hayDatos(List<Factura> list) {
        facturaListAdapter.addAll(list);
        facturaListAdapter.notifyDataSetChanged();//Para que actualice los datos
    }

    @Override
    public void noDatos() {
        Toast.makeText(getContext(), "NO hay datos", Toast.LENGTH_LONG).show();
    }

    @Override
    public void mensaje(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void mostrarError(String msg) {
        Toast.makeText(getContext(), "Error "+msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setPresenter(FacturaListContract.Presenter presenter) {
        this.presenter=presenter;
    }
    //endregion
}
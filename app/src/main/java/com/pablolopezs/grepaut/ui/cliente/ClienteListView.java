package com.pablolopezs.grepaut.ui.cliente;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pablolopezs.grepaut.R;
import com.pablolopezs.grepaut.adapter.ClienteListAdapter;
import com.pablolopezs.grepaut.data.model.Cliente;

import java.util.ArrayList;

public class ClienteListView extends Fragment implements ClienteListContract.View {

    public static final String TAG = "ClienteListView";
    private ClienteListAdapter clienteListAdapter;
    private RecyclerView rvCliente;
    ClienteListContract.Presenter presenter;

    public static ClienteListView newInstance(Bundle args) {
        ClienteListView fragment = new ClienteListView();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cliente_list_view, container, false);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Para mantener los datos o estado al girar la actividad
        setRetainInstance(true);
        rvCliente=view.findViewById(R.id.rvCliente);
        inicializaRvCliente();
        presenter.cargarDatos();
    }

    public void inicializaRvCliente(){
        clienteListAdapter = new ClienteListAdapter();
        rvCliente.setAdapter(clienteListAdapter);
        rvCliente.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    /**Implementados por la interfaz*/

    @Override
    public void hayDatos(ArrayList<Cliente> list) {
        clienteListAdapter.addAll(list);
        clienteListAdapter.notifyDataSetChanged();
    }

    @Override
    public void noDatos() {
        Toast.makeText(getContext(),"NO hay datos",Toast.LENGTH_LONG).show();
    }

    @Override
    public void mensaje(String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_LONG).show();
    }


    @Override
    public void mostrarError(String msg) {
        Toast.makeText(getContext(),"Error",Toast.LENGTH_LONG).show();
    }

    @Override
    public void setPresenter(ClienteListContract.Presenter presenter) {
        this.presenter = presenter;
    }
   /**-------------------------------------------------------------------------------*/
}
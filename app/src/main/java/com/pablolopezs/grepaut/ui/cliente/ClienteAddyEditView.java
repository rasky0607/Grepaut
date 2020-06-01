package com.pablolopezs.grepaut.ui.cliente;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.pablolopezs.grepaut.R;
import com.pablolopezs.grepaut.data.model.Cliente;

import java.util.ArrayList;

/*Esta clase nos permite añadir o editar un cliente, segun el Bundel que llega en el constructor este a null, o no*/
public class ClienteAddyEditView extends Fragment implements ClienteAddyEditContract.View {
    public static final String TAG ="ClienteAddyEditView";
    ClienteAddyEditContract.Presenter presenter;
    // Componentes Vista
    TextInputEditText teNombreClienteAddEdit;
    TextInputEditText teApellidosClienteAddEdit;
    TextInputEditText teMatriculaClienteAddEdit;
    TextInputEditText tetlfClienteAddEdit;
    TextInputEditText teemailClienteAddEdit;
    Button btnGuardarCliente;
    static int posEditar=-1;//Posicion del elemento que estamos editando para reiscribirlo en la misma posicion de la lista de repositories
    //-----//

    //Cuando pos en el construcotr es menor que 0 es que  se  va añadir un nuevo elemento no a modificar u no existente.
    public static ClienteAddyEditView newInstance(Bundle args, int pos){
        ClienteAddyEditView  clienteAddyEditView = new ClienteAddyEditView();

        //Si el args NO es nulo, es edicion y la posicion no es menor que 0
        if(args!=null && pos>=0)
        {
            //Recogemos el bundle  creando un nuevo objeto con lo que nos llega
            clienteAddyEditView.setArguments(args);
        }
        posEditar=pos;//Posicion del elemento que estamos editando para reiscribirlo en la misma posicion de la lista de repositories

        return clienteAddyEditView;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_uncliente_view_add_y_edit, container, false);
        //Enlace de componentes de la vista con la clase
        teNombreClienteAddEdit= view.findViewById(R.id.teNombreClienteAddEdit);
        teApellidosClienteAddEdit= view.findViewById(R.id.teApellidosClienteAddEdit);
        teMatriculaClienteAddEdit= view.findViewById(R.id.teMatriculaClienteAddEdit);
        tetlfClienteAddEdit= view.findViewById(R.id.tetlfClienteAddEdit);
        teemailClienteAddEdit= view.findViewById(R.id.teemailClienteAddEdit);
        btnGuardarCliente= view.findViewById(R.id.btnGuardarCliente);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Para mantener los datos o estado al girar la actividad
        setRetainInstance(true);

        btnGuardarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    //region Implementado por la interfaz ClienteAddyEditContract.View
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public void Correcto() {

    }

    @Override
    public Cliente getObjeto() {
        return null;
    }

    @Override
    public void mensaje(String msg) {

    }

    @Override
    public void mostrarError(String msg) {

    }

    @Override
    public void setPresenter(ClienteAddyEditContract.Presenter presenter) {
        this.presenter=presenter;
    }


    //-------Fin implementacion de la interfaz
    //endregion
}

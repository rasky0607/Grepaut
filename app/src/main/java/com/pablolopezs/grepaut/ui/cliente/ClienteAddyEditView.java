package com.pablolopezs.grepaut.ui.cliente;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

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

        //Si getArguments() es distinto de null es por que e suna edicion, algo llego por el bundle
        //Colocamos los datos en los componentes de la vista, para ser listo para editar
        if(getArguments()!=null)
        {
            Cliente cliente=getArguments().getParcelable(Cliente.TAG);
            teNombreClienteAddEdit.setText(cliente.getNombre());
            teMatriculaClienteAddEdit.setText(cliente.getMatriculaCoche());
            tetlfClienteAddEdit.setText(cliente.getTlf());
            teemailClienteAddEdit.setText(cliente.getEmail());
        }

        //Guardar/añadir  cambios
        btnGuardarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(presenter.validar()){
                    if (posEditar >= 0)//Si es mayor o igual que 0, es una EDICION O MODIFICACION
                    {
                        presenter.modificar(posEditar, getObjeto());
                    }else{
                        presenter.anadir(getObjeto());
                    }
                    posEditar=-1;//reseteamos de nuevo esta variable tras realizar una insercion o modificacion, ya que de otro modo al intentar insertar un nuevo registro tras modificar otro, cogeria los datos del anterior
                    //#####PENDIENTE##### que al validar y realizarla inserción, vuelva al fragmen de ListarServicios osea, volver uno atras en la pila
                }

            }
        });

    }

    //region Implementado por la interfaz ClienteAddyEditContract.View
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Cliente getObjeto() {
        Cliente cliente = new Cliente();
        cliente.setNombre(teNombreClienteAddEdit.getText().toString());
        cliente.setMatriculaCoche(teMatriculaClienteAddEdit.getText().toString());
        cliente.setTlf(tetlfClienteAddEdit.getText().toString());
        cliente.setEmail(teemailClienteAddEdit.getText().toString());
        return cliente;
    }

    //Comprueba que los datos introducidos en los widget son validos
    @Override
    public boolean esValido() {
        if(TextUtils.isEmpty(teNombreClienteAddEdit.getText()))
        {
            mostrarError("El nombre del cliente no puede estar vacio.");
            return false;
        }
        if(TextUtils.isEmpty(teMatriculaClienteAddEdit.getText()))
        {
            mostrarError("La matricula del vehículo no puede estar vacia.");
            return false;
        }
        if(TextUtils.isEmpty(tetlfClienteAddEdit.getText()) || !TextUtils.isDigitsOnly(tetlfClienteAddEdit.getText()))
        {
            Log.d("ERROR","Aqui toy");
            mostrarError("El teléfono del cliente no puede estar vacio, y debe ser de tipo numérico.");
            return false;
        }

        return true;
    }

    @Override
    public void mensaje(String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarError(String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(ClienteAddyEditContract.Presenter presenter) {
        this.presenter=presenter;
    }


    //-------Fin implementacion de la interfaz
    //endregion
}

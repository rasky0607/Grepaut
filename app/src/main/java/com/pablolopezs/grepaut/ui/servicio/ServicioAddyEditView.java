package com.pablolopezs.grepaut.ui.servicio;

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
import com.google.android.material.textfield.TextInputLayout;
import com.pablolopezs.grepaut.R;
import com.pablolopezs.grepaut.data.model.Servicio;

import java.util.ArrayList;

public class ServicioAddyEditView extends Fragment implements ServicioAddEditContract.View {

    public static final String TAG ="ServicioAddyEditView";
    ServicioAddEditContract.Presenter presenter;
    // Componentes Vista
    TextInputLayout tiNombreServicioAddEdit;
    TextInputLayout tiPrecioServicioeAddEdit;
    TextInputLayout tiDescripcionServicioAddEdit;
    TextInputEditText teNombreServicioAddEdit;
    TextInputEditText tePrecioServicioeAddEdit;
    TextInputEditText teDescripcionServicioAddEdit;
    Button btnGuardarServicio;
    //-----//


    //Constructor [Cuando pos en el construcotr es menor que 0 es que  se  va añadir un nuevo elemento no a modificar uno existente.]
    public static ServicioAddyEditView newInstance(Bundle args){
        ServicioAddyEditView  servicioAddyEditView = new ServicioAddyEditView();
        //Si el args NO es nulo, es edicion
        if(args!=null)
        {
            //Recogemos el bundle  creando un nuevo objeto con lo que nos llega
            servicioAddyEditView.setArguments(args);
        }
        return servicioAddyEditView;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_unservicio_view_add_y_edit, container, false);
        tiNombreServicioAddEdit=view.findViewById(R.id.tiNombreServicioAddEdit);
        tiPrecioServicioeAddEdit=view.findViewById(R.id.tiPrecioServicioeAddEdit);
        tiDescripcionServicioAddEdit=view.findViewById(R.id.tiDescripcionServicioAddEdit);
        teNombreServicioAddEdit=view.findViewById(R.id.teNombreServicioAddEdit);
        tePrecioServicioeAddEdit=view.findViewById(R.id.tePrecioServicioeAddEdit);
        teDescripcionServicioAddEdit=view.findViewById(R.id.teDescripcionServicioAddEdit);
        btnGuardarServicio=view.findViewById(R.id.btnGuardarServicio);
        return view;
    }
    private void bloquearCamposDeClavesPrimarias(){
        teNombreServicioAddEdit.setEnabled(false);
    }
    private void desbloquearCamposDeClavesPrimarias(){
        teNombreServicioAddEdit.setEnabled(true);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Para mantener los datos o estado al girar la actividad
        setRetainInstance(true);

        //Si getArguments() es distinto de null es por que e suna edicion, algo llego por el bundle
        //Colocamos los datos en los componentes de la vista, para ser listo para editar
        if(getArguments()!=null){

            Servicio servicio=  getArguments().getParcelable(Servicio.TAG);
            teNombreServicioAddEdit.setText(servicio.getNombre());
            tePrecioServicioeAddEdit.setText(Double.toString(servicio.getPrecio()));
            teDescripcionServicioAddEdit.setText(servicio.getDescripcion());
            bloquearCamposDeClavesPrimarias();
        }

        //Guardar/añadir  cambios
        btnGuardarServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Validamos el objeto con el presenter
                if (presenter.validar())
                {
                    if (getArguments()!=null)//Si es distinto de null, es una EDICION O MODIFICACION
                    {
                        presenter.modificar(getObjeto());
                    } else//Si no, es una nueva INSERCION
                    {
                        presenter.anadir(getObjeto());
                    }
                    desbloquearCamposDeClavesPrimarias();

                }

            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //region Implementado por la interfaz ServicioListContract.View

    //Recoge los datos en cada widget de la vista para guardarlos en el objeto y devolverlo
    @Override
    public Servicio getObjeto() {
        Servicio servicio = new Servicio();
        servicio.setNombre(teNombreServicioAddEdit.getText().toString());
        servicio.setPrecio(Double.parseDouble(tePrecioServicioeAddEdit.getText().toString()));
        servicio.setDescripcion(teDescripcionServicioAddEdit.getText().toString());
        return servicio;
    }

    //Comprueba que los datos introducidos en los widget son validos
    @Override
    public boolean esValido() {
        Log.d("ERROR","FUERA DE IF Aqui toy");
        if(TextUtils.isEmpty(teNombreServicioAddEdit.getText()))
        {
            mostrarError("El nombre del servicio no puede estar vacio.");
            return false;
        }
        if(TextUtils.isEmpty(tePrecioServicioeAddEdit.getText()))
        {
            Log.d("ERROR","Aqui toy");
            mostrarError("El precio del servicio no puede estar vacio.");
            return false;
        }

        return true;
    }

    @Override
    public void mensaje(String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
        getActivity().onBackPressed();//Vuelve al fragment anterior tras insertar el registro
    }

    @Override
    public void mostrarError(String msg) {
        Toast.makeText(getContext(),"ERROR: "+msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(ServicioAddEditContract.Presenter presenter) {
        this.presenter=presenter;
    }


    //-------Fin implementacion de la interfaz
    //endregion

    private void setPosicionEditar(int pos){

    }

}

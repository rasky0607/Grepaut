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
    static int posEditar=-1;//Posicion del elemento que estamos editando para reiscribirlo en la misma posicion de la lista de repositories

    //Constructor [Cuando pos en el construcotr es menor que 0 es que  se  va añadir un nuevo elemento no a modificar uno existente.]
    public static ServicioAddyEditView newInstance(Bundle args,int pos){
        ServicioAddyEditView  servicioAddyEditView = new ServicioAddyEditView();
        //Si el args NO es nulo, es edicion
        if(args!=null)
        {
            //Recogemos el bundle  creando un nuevo objeto con lo que nos llega
            servicioAddyEditView.setArguments(args);
        }
        posEditar=pos;//Posicion del elemento que estamos editando para reiscribirlo en la misma posicion de la lista de repositories
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Para mantener los datos o estado al girar la actividad
        setRetainInstance(true);

        //Si getArguments() es distinto de null es por que e suna edicion, algo llego por el bundle
        //Colocamos los datos en los componentes de la vista, para ser listo para editar
        if(getArguments()!=null){
            Log.d("ServicioAdd","Posicion "+posEditar);
            Servicio servicio=  getArguments().getParcelable(Servicio.TAG);
            teNombreServicioAddEdit.setText(servicio.getNombre());
            tePrecioServicioeAddEdit.setText(Double.toString(servicio.getPrecio()));
            teDescripcionServicioAddEdit.setText(servicio.getDescripcion());
        }

        //Guardar/añadir  cambios
        btnGuardarServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Validamos el objeto con el presenter
                if (presenter.validar())
                {
                    if (posEditar >= 0)//Si es mayor o igual que 0, es una EDICION O MODIFICACION
                    {
                        presenter.modificar(posEditar, getObjeto());
                    } else//Si no, es una nueva INSERCION
                    {
                        presenter.anadir(getObjeto());
                    }
                    posEditar=-1;//reseteamos de nuevo esta variable tras realizar una insercion o modificacion, ya que de otro modo al intentar insertar un nuevo registro tras modificar otro, cogeria los datos del anterior
                    //#####PENDIENTE##### que al validar y realizarla inserción, vuelva al fragmen de ListarServicios osea, volver uno atras en la pila

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
        if(TextUtils.isEmpty(tePrecioServicioeAddEdit.getText()) || !TextUtils.isDigitsOnly(tePrecioServicioeAddEdit.getText()))
        {
            Log.d("ERROR","Aqui toy");
            mostrarError("El precio del servicio no puede estar vacio, y debe ser de tipo numérico.");
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
    public void setPresenter(ServicioAddEditContract.Presenter presenter) {
        this.presenter=presenter;
    }


    //-------Fin implementacion de la interfaz
    //endregion

    private void setPosicionEditar(int pos){

    }
//Interfaqz para realizar ediciones o bien abrir el fragment de añadir, la cual es la misma
  /*  public  interface addOEditServicio{
        void  fragmentServicioAddOEdit(Servicio servicio, int pos); //Pos indica la posicion en la lista de repositorio en la que se debe modificar el objeto
    }*/

}

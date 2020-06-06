package com.pablolopezs.grepaut.ui.servicio;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.pablolopezs.grepaut.R;
import com.pablolopezs.grepaut.adapter.ServicioListAdapter;
import com.pablolopezs.grepaut.adapter.TouchCallback;
import com.pablolopezs.grepaut.data.model.Servicio;

import java.util.List;

public class ServicioListView extends Fragment implements ServicioListContract.View {

    public static final String TAG = "ServicioListView";
    private ServicioListAdapter servicioListAdapter;
    private RecyclerView rvServicio;
    ServicioListContract.Presenter presenter;
    //ServicioListAdapter.manipularDatos manipularDatos;
    private ItemTouchHelper mItemTouchHelperListener;

    manipularDatosServicioAddOEdit manipularDatosServicioAddOEdit;

    public static ServicioListView newInstance(Bundle args) {
        ServicioListView fragment = new ServicioListView();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_servicio_list_view, container, false);
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
        rvServicio=view.findViewById(R.id.rvServicio);
        inicializaRvServicio();
        presenter.cargarDatos();
    }

    public void inicializaRvServicio(){
        servicioListAdapter=new ServicioListAdapter(new ServicioListAdapter.manipularDatos() {
            @Override
            public void miOnLOngClick(int posicion) {
                manipularDatosServicioAddOEdit.fragmentManipularDatosServicioAddOEdit(servicioListAdapter.getItemList(posicion));
            }

            @Override
            public void miClick() {

            }

            @Override
            public void confirmarBorrado(final int adapterPosition) {
                //--------------Ventana de AlerDialog----------
                AlertDialog alerta = new AlertDialog.Builder(getContext()).
                        setMessage("¿Estas seguro de eliminar este elemento?").setTitle("Aviso. ").
                        setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            /*Se ha confirmado el borrado del item en la ventana de AlerDialog y se prodece a mostrar
                                un scankbar a bajo que permite deshacer esta opcion durante un breve periodo de tiempo antes de convertirla en irreversible*/
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Eliminamos el Servicio del adapter y lo guardamos, ya que este que se puede  RESTAURAR antes de 10 segundos con el snackbar
                                final Servicio s =  servicioListAdapter.eliminar(adapterPosition);
                                Log.d("Deshacer",s.getNombre());
                                //Eliminar el objeto servicio de la lista del Repositiorio a traves del presenter
                                presenter.eliminar(adapterPosition,s);


                                //----------Deshacer/Restaurar eliminacion------------
                                Snackbar snackbar = Snackbar
                                        .make(getActivity().findViewById(R.id.contenedorPadre),"Servicio: "+ s.getNombre()+" borrado. " + " Deshacer el borrrado", 10000);
                                snackbar.setAction("Deshacer", new View.OnClickListener() {
                                    /*Se pulso el boton "Deshacer" del snackbar y
                                     se restaura el elemento de nuevo en la vista del ReciclerView*/
                                    @Override
                                    public void onClick(View view) {
                                        servicioListAdapter.deshacerBorrado(adapterPosition,  s);
                                        //Añadimos el objeto de nuevo en su posicion original en el repositiorio
                                        presenter.anadir(s);
                                    }
                                });
                                snackbar.setActionTextColor(Color.WHITE);
                                snackbar.show();
                                //---------Fin de barra de Deshacer------------
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Negativo
                                servicioListAdapter.cancelacionDeBorrado(adapterPosition);
                            }
                        }).show();
                //alerta.show();
            }
        });

        //2. Crear diseño del RecyclerView
        // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT, RecyclerView.VERTICAL, false);
        //rvReparacion.setLayoutManager(linearLayoutManager);
        //3. Vincular la vista al modelo (RecyclerView al Adapter)
        rvServicio.setAdapter(servicioListAdapter);
        rvServicio.setLayoutManager(new LinearLayoutManager(getContext()));
        //Incializamos la clase TouchCallback para poder realizar el efecto de eleminar al desplazar el dedo hacia la izquierda sobre un elemento
        TouchCallback callback = new TouchCallback(servicioListAdapter);
        mItemTouchHelperListener = new ItemTouchHelper(callback);
        mItemTouchHelperListener.attachToRecyclerView(rvServicio);
        Log.d("PRUEBA", "ServicioListView: inicializaRvServicio() ");
    }

    //region Metodos implementados por la interfaz ServicioListContract.View
    @Override
    public void hayDatos(List<Servicio> list) {
        servicioListAdapter.miaddAll(list);
        servicioListAdapter.notifyDataSetChanged();
    }

    @Override
    public void noDatos() {
        Toast.makeText(getContext(),"NO hay datos",Toast.LENGTH_LONG).show();
    }

    @Override
    public void mensaje(String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarError(String msg) {
        Toast.makeText(getContext(),"Error "+msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void setPresenter(ServicioListContract.Presenter presenter) {
        this.presenter=presenter;
    }

    //----Fin implementacion de la interfaz ServicioListContract.View---/
    //endregion



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Inicializamos la interfaz
        manipularDatosServicioAddOEdit = (manipularDatosServicioAddOEdit) context;
    }

    //Esta interfaz nos permite comunicar la clase MainActivity que gestiona los fragment y de este modo enviar informacion entre los distintos fragment haciendo uso de Bundle y la interfaz Parcelable
    public  interface manipularDatosServicioAddOEdit{
        void  fragmentManipularDatosServicioAddOEdit(Servicio servicio);
    }
}
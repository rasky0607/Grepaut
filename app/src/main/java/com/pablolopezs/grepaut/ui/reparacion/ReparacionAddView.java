package com.pablolopezs.grepaut.ui.reparacion;

import android.app.DatePickerDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.snackbar.Snackbar;
import com.pablolopezs.grepaut.R;
import com.pablolopezs.grepaut.data.model.Cliente;
import com.pablolopezs.grepaut.data.model.Reparacion;
import com.pablolopezs.grepaut.data.model.Servicio;
import com.pablolopezs.grepaut.data.model.Usuario;
import com.pablolopezs.grepaut.data.repositories.ClienteRepositories;
import com.pablolopezs.grepaut.data.repositories.ReparacionRepositories;
import com.pablolopezs.grepaut.data.repositories.ServicioRepositories;
import com.pablolopezs.grepaut.data.repositories.UsuarioRepositories;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
//Importada de arsenal android

public class ReparacionAddView extends Fragment implements ReparacionAddContract.View{
    public static final String TAG="ReparacionAdd";

    ReparacionAddContract.Presenter presenter;
//--------------------
    TextView tvFechaSelecionada;//Recogera la fecha seleccionada
    Button btnDatePicker;//Este iniciara la apertura d ela ventana de dialogo con el calendario
    Calendar c; //clase de la que obtendremos la fecha actual
    DatePickerDialog dpd;//Calendario de fecha de ventana emergente
    Button btnGuardarReparacion;
    ImageButton ibtnAnadirServicio;
    ImageButton ibtnInfo;
    Spinner spMatricula;
    Spinner spServicio;
    TextView tvNombreCliente;
    //----------------
    //Marcas de spinner para cuando son precargados al inciiar la vista no tener un item  selecionado
    String marcaSpMatricula="<Sin Matricula>";
    String marcaSpServicio="<Sin Servicio>";
    //Lista de reparaciones a añadir cuando se realizan varias inserciones
    ArrayList<Reparacion> listaReparacionAanadir;
    /*Esta variable nos indica el numero de reparacion que vamos a asignar a las nuevas reapraciones que el usuario añada en funcion de las que ya hay guardadas
    * de forma que si ya hay una reparacion  para una matricula en un dia concreto con el numero de reparacion por ejemplo 2,
    *  la siguiente que sea insertada para esa matricula en ese mismo dia, tendra el numero de reparacion3*/
    Context micontext;
    public static ReparacionAddView newInstance() {
        ReparacionAddView fragment = new ReparacionAddView();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //Inflamos la vista
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_reparacion_add, container, false);
        //Enlace de componentes
        tvFechaSelecionada=view.findViewById(R.id.tvFechaSelecionada);
        btnDatePicker=view.findViewById(R.id.btnDatePicker);
        btnGuardarReparacion=view.findViewById(R.id.btnGuardarReparacion);
        ibtnAnadirServicio=view.findViewById(R.id.ibtnAnadirServicio);
        ibtnInfo=view.findViewById(R.id.ibtnInfo);
        spMatricula=view.findViewById(R.id.spMatricula);
        spServicio=view.findViewById(R.id.spServicio);
        tvNombreCliente=view.findViewById(R.id.tvNombreCliente);
        //Desactivamos el boton de Guardar Reparaciones al inicio, hasta que se use una vez al menos el boton verde para que la lista listaReparacionAanadir no este vacia
        activarDesactivarbtnGuardar(false);
        //Recogemos la fecha actual de la clase calendar
       recogerFechaActualParaCalendario();
       cargarSpinners();
        //----------------
        listaReparacionAanadir=new ArrayList<Reparacion>();
        return view;
    }
    //Recoge la fecha actual para el calendario
    private  void recogerFechaActualParaCalendario(){
        //Recogemos la fecha actual de la clase calendar
        c=Calendar.getInstance();
        int day= c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        //Rellenamos el tvFecha con la Fecha actual
        tvFechaSelecionada.setText((day+"/"+(month+1)+"/"+year));//+1 por que los meses empiezan en 0
    }

    //Carga los spinner de este fragmen con las distintas claves de los clientes [es decir las matriculas] y las claves de los servicios [los nombres]
    private void cargarSpinners(){

        //region Matriculas de coches de clientes
        List<Cliente>listMatriculas= ClienteRepositories.getInstance().getList();
        String[] matriculaCochesClientes= new String[listMatriculas.size()+1];//+1 para añadir a la lista un elemento en primera posicion que sea un elemento "no selecionado" para a la hora de cargar el spinner sin seleccion por defecto
        for (int i =0; i<listMatriculas.size()+1;i++)
        {
            if(i==0){
                matriculaCochesClientes[i]=marcaSpMatricula;
            }else {
                matriculaCochesClientes[i] = listMatriculas.get(i-1).getMatriculaCoche();
            }
            Log.d("lista","Contenido lista Matriculas, numero "+i+"º de la lista "+matriculaCochesClientes[i]);
        }
        spMatricula.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,matriculaCochesClientes));
        //endregion

        //region Nombre de Servicios
        List<Servicio> listServicio= ServicioRepositories.getInstance().getList();
        String[] nombreServicios= new String[listServicio.size()+1];//+1 para añadir a la lista un elemento en primera posicion que sea un elemento "no selecionado" para a la hora de cargar el spinner sin seleccion por defecto
        for (int i =0; i<listServicio.size()+1;i++)
        {
            if(i==0){
                nombreServicios[i]=marcaSpServicio;
            }else {
                nombreServicios[i] = listServicio.get(i-1).getNombre();
            }
            Log.d("lista","Contenido lista Servicios, numero "+i+"º de la lista "+nombreServicios[i]);
        }
        spServicio.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,nombreServicios));
        //endregion

    }

    //Dada una Matricula selecionada en el spinner de Matricula vehiculo, busca el nombre del cliente relacionado con esa matricula para mostrarla en su textView
    private void buscarNombreDeCliente(String matricula){
        String nombreCliente= ClienteRepositories.getInstance().buscarNobreCliente(matricula);
        tvNombreCliente.setText(nombreCliente);//Asignamos el nombre al componentes de la vista
    }

    //Este metodo desactiva algunas componentes d ela vista cuando vamos a insertar varias reapraciones del mismo cliente el mismo dia(es decir usando el boton verde)
    private void desactivaElementos(){
        btnDatePicker.setEnabled(false);
        spMatricula.setEnabled(false);
        btnDatePicker.setTextColor(Color.parseColor("#B6B4B4"));
    }

    //Activa y desactiva el boton de guardar reparaciones
    private void activarDesactivarbtnGuardar(boolean valor){
        btnGuardarReparacion.setEnabled(valor);
        //Cambiamos el estilo del boton en funcion de si el valor es true o false, es decir si esta habilitado o no
        if(valor)
        {
            btnGuardarReparacion.setTextColor(Color.parseColor("#FFFFFF"));
        }
        else{
            btnGuardarReparacion.setTextColor(Color.parseColor("#B6B4B4"));
        }
    }
    //resetea todos los elementos de la vista y variables de la clase necesarias a la hora de hacer click en boton Guardar Reparacion
    private  void resetarElementos(){
        btnDatePicker.setEnabled(true);
        spMatricula.setEnabled(true);
        spMatricula.setSelection(0);
        spServicio.setSelection(0);
        recogerFechaActualParaCalendario();
    }

    /**Justo despues de crear la vista*/
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //region Evento Click que abre la ventana de dialog con el calendario
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Recogemos la fecha actual de la clase calendar
                c=Calendar.getInstance();
                int day= c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                //Abrimos el calendario de la ventana emergente o dialog con la fecha y mes actual
                dpd=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int myear, int mMonth, int mday) {
                        tvFechaSelecionada.setText((mday+"/"+(mMonth+1)+"/"+myear));//+1 por que los meses empiezan en 0
                    }
                },year,month,day);//Fecha a la que se inicia el calendario
                dpd.show();//Mostramos el calendario
            }
        });
        //endregion--------------//

        //region Guardar reparacion que ya fueron validades en la region siguiente "Añadir otra reparacion"
        btnGuardarReparacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int numReparacion=presenter.getNumeroUltimaReparacion(listaReparacionAanadir.get(0));
                for ( Reparacion reparacion : listaReparacionAanadir) {
                    reparacion.setNumeroReparacion(++numReparacion);
                    presenter.anadir(reparacion);
                }
                resetarElementos();
                volver();
            }
        });
        //endregion-----//

        //region Añadir otra reparacion a la lista de reparaciones que se van a crear[Es decir cuando vamos a introducir varias reparaciones para el mismo cliente en el mismo dia con servicios diferentes
        ibtnAnadirServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(presenter.validar()) //Comprobamos si los datos son validos
                {
                    //Añade reparaciones a una lista que crearemos de reparaciones, la cual al dar al boton de guardar se insertaran en la clase repositories
                    listaReparacionAanadir.add(getObjeto());
                    spServicio.setSelection(0);//Lo coloamos en la primera posicion para que tenga que volver a elegir un sercicio
                    desactivaElementos();
                    if(listaReparacionAanadir.size()>0)
                        activarDesactivarbtnGuardar(true);//Activamos el boton de guardar reparaciones
                }
            }
        });
        //endregion ----//

        //region Muentra un cuadro de dialogo donde se indica informacion sobre el boton de "Añadir otra reparacion [El verde con flecha de color blanco]"
        ibtnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //--------------Ventana de AlerDialog----------
                AlertDialog alerta = new AlertDialog.Builder(getContext()).
                        setMessage("Este boton con una flecha de color blanca y fondo verde, nos permite crear varias reparaciones para un mismo vehiculo en una misma fecha con diferentes servicios").setTitle("Información. ").
                        setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create();
                alerta.show();

            }
        });
        //endregion----//

        //region Cuando se seleciona un elemento en el spinner de Matricula vehiculo
        spMatricula.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String matriculaSelecionada = spMatricula.getSelectedItem().toString();
                    Log.d("Spinner", "Lo selecioando " + matriculaSelecionada);
                    buscarNombreDeCliente(matriculaSelecionada);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //endregion
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        micontext=context;

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

//region Implementado por la interfaz

    private void volver(){
        getActivity().onBackPressed();//Vuelve al fragment anterior tras insertar el registro **PROVISIONAL**
    }
    @Override
    public Reparacion getObjeto() {
        Reparacion r = new Reparacion();
        r.setFecha(tvFechaSelecionada.getText().toString());
        r.setMatriculaCoche(spMatricula.getSelectedItem().toString());
        r.setNombreServicio(spServicio.getSelectedItem().toString());
        r.setPrecioServicio(ServicioRepositories.getInstance().precioServicio(r.getNombreServicio()));
        r.setEstadoReparacion(false);
        r.setEstadoFacturado(false);
        r.setNombreCliente(tvNombreCliente.getText().toString());
        r.setTecnico(UsuarioRepositories.getInstance().getUsuarioActual().getNombre());//Nombre de tecnico
        return r;
    }

    @Override
    public boolean esValido() {
        if(spMatricula.getSelectedItem().toString().equals(marcaSpMatricula))
        {
            mostrarError("Debes selecionar una matricula.");
            return false;
        }
        if(spServicio.getSelectedItem().toString().equals(marcaSpServicio))
        {
            mostrarError("Debes selecionar un servicio.");
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
       Toast.makeText(getContext(),"ERROR: "+msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(ReparacionAddContract.Presenter presenter) {
        this.presenter=presenter;
    }
    //endregion
}

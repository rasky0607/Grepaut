package com.pablolopezs.grepaut.ui.reparacion;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;


import com.pablolopezs.grepaut.R;


import java.util.Calendar;
import java.util.Date;
//Importada de arsenal android







public class ReparacionAdd extends Fragment {
    public static final String TAG="ReparacionAdd";

//--------------------
    TextView tvFechaSelecionada;//Recogera la fecha seleccionada
    Button btnDatePicker;//Este iniciara la apertura d ela ventana de dialogo con el calendario
    Calendar c; //clase de la que obtendremos la fecha actual
    DatePickerDialog dpd;//Calendario de fecha de ventana emergente

    //----------------
    public static ReparacionAdd newInstance() {
        ReparacionAdd fragment = new ReparacionAdd();
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

        //Recogemos la fecha actual de la clase calendar
        c=Calendar.getInstance();
        int day= c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        //Rellenamos el tvFecha con la Fecha actual
        tvFechaSelecionada.setText((day+"/"+month+"/"+year));

        //----------------
        return view;
    }

    /**Justo despues de crear la vista*/
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //--------------Evento Click que abre la ventana de dialog cone l calendario
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
                        tvFechaSelecionada.setText((mday+"/"+mMonth+"/"+myear));
                    }
                },year,month,day);//Fecha a la que se inicia el calendario
                dpd.show();//Mostramos el calendario
            }
        });
        //--------------
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}

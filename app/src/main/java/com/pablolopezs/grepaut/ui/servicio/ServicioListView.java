package com.pablolopezs.grepaut.ui.servicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.pablolopezs.grepaut.R;

public class ServicioListView extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_servicio_list_view, container, false);
        final TextView textView = view.findViewById(R.id.text_slideshow);
        textView.setText("Soy el fragmento Servicios");

        return view;
    }
}
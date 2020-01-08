package com.pablolopezs.grepaut.ui.MainActivity.factura;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.pablolopezs.grepaut.R;

public class FacturaListView extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_factura_list_view, container, false);
        final TextView textView = view.findViewById(R.id.text_tools);
        textView.setText("Soy el fragmento Facturas");

        return view;
    }
}
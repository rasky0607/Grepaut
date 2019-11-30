package com.pablolopezs.grepaut.ui.ManagerActivity.factura;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.pablolopezs.grepaut.R;
import com.pablolopezs.grepaut.data.model.Factura;

public class FragmentFactura extends Fragment {

    private Factura toolsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(Factura.class);
        View view = inflater.inflate(R.layout.fragment_factura, container, false);
        final TextView textView = view.findViewById(R.id.text_tools);
        textView.setText("Soy el fragmento Facturas");
        /*toolsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return view;
    }
}
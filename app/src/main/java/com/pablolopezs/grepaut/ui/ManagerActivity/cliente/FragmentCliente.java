package com.pablolopezs.grepaut.ui.ManagerActivity.cliente;

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
import com.pablolopezs.grepaut.data.model.Cliente;

public class FragmentCliente extends Fragment {

    public static  String TAG = "Fragmento Cliente";
    private Cliente cliente;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cliente = ViewModelProviders.of(this).get(Cliente.class);
        View root = inflater.inflate(R.layout.fragment_cliente, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);

        cliente.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                s="Soy el fragment Cliente!";
                textView.setText(s);
            }
        });
        return root;
    }
}
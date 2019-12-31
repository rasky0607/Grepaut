package com.pablolopezs.grepaut.ui.MainActivity.clientelist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.pablolopezs.grepaut.R;

public class ClienteListView extends Fragment {

    public static  String TAG = "Fragmento Cliente";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cliente_list_view, container, false);
        final TextView textView = view.findViewById(R.id.text_gallery);
        textView.setText("Soy el fragmento Clientes");

        return view;
    }
}
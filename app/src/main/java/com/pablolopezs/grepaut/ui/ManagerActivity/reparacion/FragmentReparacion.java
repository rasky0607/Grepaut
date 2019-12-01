package com.pablolopezs.grepaut.ui.ManagerActivity.reparacion;

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
import com.pablolopezs.grepaut.data.model.Reparacion;

public class FragmentReparacion extends Fragment {

    public static  String TAG = "Fragmento Reparaciones";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reparacion, container, false);

        final TextView textView = view.findViewById(R.id.text_home);
        textView.setText("Soy el fragmento Reparaciones");

        return view;
    }
}
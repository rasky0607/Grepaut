package com.pablolopezs.grepaut.ui.ManagerActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.pablolopezs.grepaut.R;

public class FragmentContentManager extends Fragment {
    public static  String TAG = "Fragmento Contenedor";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*homeViewModel =
                ViewModelProviders.of(this).get(Reparacion.class);*/
        View view = inflater.inflate(R.layout.fragment_content_manager, container, false);

        /*homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return view;
    }

    public static Fragment newInstance(Bundle bundle){

        FragmentContentManager fragmentContentManager = new FragmentContentManager();

        if(bundle != null)
            fragmentContentManager.setArguments((bundle));

        return  fragmentContentManager;

    }
}

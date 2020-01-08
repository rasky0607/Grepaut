package com.pablolopezs.grepaut.ui.Base;

import java.util.ArrayList;

public class Base {
    // T sera el tipo de objeto que va recivir la interfaz que hereda de Base para la usarlo en alguno de los metoso como (hayDatos)
    public interface View<T>{
        void mostrarError(String msg);
        void noDatos();
        void mensaje(String msg);
        void hayDatos(ArrayList<T> list);
    }
}

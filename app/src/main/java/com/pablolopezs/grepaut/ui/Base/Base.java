package com.pablolopezs.grepaut.ui.Base;

import java.util.ArrayList;
import java.util.List;

public class Base {
    // T sera el tipo de objeto que va recivir la interfaz que hereda de Base para la usarlo en alguno de los metoso como (hayDatos)
    public interface View<T>{
        void hayDatos(List<T> list);
        void noDatos();
        void mensaje(String msg);
        void mostrarError(String msg);
    }
    public interface Presenter<T>{
        void cargarDatos();
        void anadir(T objeto);
        void actualizar(int pos, T objeto);
        boolean eliminar(int posicion, T objeto);
    }

}

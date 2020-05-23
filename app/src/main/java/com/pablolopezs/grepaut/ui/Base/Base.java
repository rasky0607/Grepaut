package com.pablolopezs.grepaut.ui.Base;

import java.util.ArrayList;

public class Base {
    // T sera el tipo de objeto que va recivir la interfaz que hereda de Base para la usarlo en alguno de los metoso como (hayDatos)
    public interface View<T>{
        void hayDatos(ArrayList<T> list);
        void noDatos();
        void mensaje(String msg);
        void mostrarError(String msg);
    }
    public interface Presenter<T>{
        void cargarDatos();
        void anadir(T objeto);
        void anadirPorPos(int pos,T objeto);
        void editar(T objeto);
        boolean eliminar(int posicion);
    }

}

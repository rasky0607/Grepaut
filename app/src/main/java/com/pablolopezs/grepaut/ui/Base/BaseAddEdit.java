package com.pablolopezs.grepaut.ui.Base;

public class BaseAddEdit {
    // T sera el tipo de objeto que va recivir la interfaz que hereda de Base para la usarlo en alguno de los metoso como (hayDatos)
    public interface View<T>{
        void Correcto();//Succes
        T getObjeto();
        void mensaje(String msg);
        void mostrarError(String msg);
    }
    public interface Presenter<T>{
        void anadir(T objeto);
        void modificar(int pos,T objeto);
        boolean validar(T objeto);
    }
}

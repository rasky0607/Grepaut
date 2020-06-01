package com.pablolopezs.grepaut.ui.Base;

public class BaseAddEdit {
    // T sera el tipo de objeto que va recivir la interfaz que hereda de Base para la usarlo en alguno de los metoso como (hayDatos)
    public interface View<T>{
        T getObjeto();//Obtiene el objeto creado en la ventana de añadir o editar
        boolean esValido();//Valida el objeto que va recibir (los campos)
        void mensaje(String msg);
        void mostrarError(String msg);
    }
    public interface Presenter<T>{
        void anadir(T objeto);
        void modificar(int pos,T objeto);
        boolean validar();//Indica a la vista si los datos a introducir son validos
    }
}

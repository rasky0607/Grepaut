package com.pablolopezs.grepaut.ui.Base;

public class Base {
    public interface View<T>{
        void mostrarError(String msg);
        void noDatos();
        void mensaje(String msg);

    }
}

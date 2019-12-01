package com.pablolopezs.grepaut.data.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Servicio {


    String nombre;
    double precio;
    String descripcion;
    String empresa;//Empresa a la que pertenece este servicio (es una FK de la tabla Empresa)

    //region Propiedades
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    //endregion

    //region Constructores
    public Servicio(String nombre, double precio, String descripcion, String empresa) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.empresa = empresa;
    }
    public Servicio(){

    }
    //endregion

}
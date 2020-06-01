package com.pablolopezs.grepaut.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Servicio implements Parcelable {
    public static final String TAG="Servicio";
    String nombre;//Clave primaria, es decir el nombre es unico
    double precio;
    //SIN USO ACTUALMENTE
    String descripcion;
    //SIN USO ACTUALMENTE
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

    public Servicio(String nombre, double precio ) {
        this.nombre = nombre;
        this.precio = precio;
    }
    //SIN USO ACTUALMENTE
    public Servicio(String nombre, double precio, String descripcion,String empresa) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.empresa = empresa;
    }
    //SIN USO ACTUALMENTE
    public Servicio(String nombre, double precio, String descripcion ) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }
    public Servicio(){

    }

    //region Interfaz parcelable
    @Override
    public int describeContents() {
        return CONTENTS_FILE_DESCRIPTOR;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeDouble(precio);
        dest.writeString(descripcion);
        //dest.writeString(empresa); //SIN USO ACTUALMENTE
    }
    //endregion

}
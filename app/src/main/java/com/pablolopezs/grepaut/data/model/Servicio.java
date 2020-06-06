package com.pablolopezs.grepaut.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
//Room
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Servicio implements Parcelable {
    @Ignore
    public static final String TAG="Servicio";

    @PrimaryKey
    @NonNull
    @ColumnInfo
    String nombre;//Clave primaria, es decir el nombre es unico
    @NonNull
    double precio;
    String descripcion;

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

    //endregion

    //region Constructores

    @Ignore
    public Servicio(String nombre, double precio ) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public Servicio(String nombre, double precio, String descripcion ) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    @Ignore
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


    protected Servicio(Parcel in) {
        nombre = in.readString();
        precio = in.readDouble();
        descripcion = in.readString();
    }

    public static final Creator<Servicio> CREATOR = new Creator<Servicio>() {
        @Override
        public Servicio createFromParcel(Parcel in) {
            return new Servicio(in);
        }

        @Override
        public Servicio[] newArray(int size) {
            return new Servicio[size];
        }
    };
    //endregion

}
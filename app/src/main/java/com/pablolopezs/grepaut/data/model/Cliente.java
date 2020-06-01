package com.pablolopezs.grepaut.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Cliente implements Parcelable {
    public static final String TAG="Cliente";
    //region campos
    String matriculaCoche;//primary key
    String nombre;//Nombre y apellidos
    String tlf;
    String email;
    //endregion

    //region Propiedades

    public String getMatriculaCoche() {
        return matriculaCoche;
    }

    public void setMatriculaCoche(String matriculaCoche) {
        this.matriculaCoche = matriculaCoche;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //endregion

    //region Constructores
    public Cliente( String matriculaCoche, String nombre, String tlf, String email) {
        this.matriculaCoche = matriculaCoche;
        this.nombre = nombre;
        this.tlf = tlf;
        this.email = email;
    }

    public  Cliente(int id,String matriculaCoche,String nombre,String apellidos){
        this.matriculaCoche = matriculaCoche;
        this.nombre = nombre;
    }

    public  Cliente(){

    }

    //region Interfaz parcelable
    @Override
    public int describeContents() {
        return CONTENTS_FILE_DESCRIPTOR;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(matriculaCoche);
        dest.writeString(nombre);
        dest.writeString(tlf);
        dest.writeString(email);
    }
    //endregion

}
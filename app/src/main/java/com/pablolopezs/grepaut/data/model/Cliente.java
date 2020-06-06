package com.pablolopezs.grepaut.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Cliente implements Parcelable {
    public static final String TAG="Cliente";
    //region campos
    @PrimaryKey
    @NonNull
    @ColumnInfo
    String matriculaCoche;//primary key
    @NonNull
    String nombre;//Nombre y apellidos
    @NonNull
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
    public Cliente( String matriculaCoche, String nombre, String tlf ) {
        this.matriculaCoche = matriculaCoche;
        this.nombre = nombre;
        this.tlf = tlf;
    }

    @Ignore
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

    protected Cliente(Parcel in) {
        matriculaCoche = in.readString();
        nombre = in.readString();
        tlf = in.readString();
        email = in.readString();
    }

    public static final Creator<Cliente> CREATOR = new Creator<Cliente>() {
        @Override
        public Cliente createFromParcel(Parcel in) {
            return new Cliente(in);
        }

        @Override
        public Cliente[] newArray(int size) {
            return new Cliente[size];
        }
    };
    //endregion

}
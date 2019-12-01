package com.pablolopezs.grepaut.data.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Cliente {

    //region campos
    int id;
    String matriculaCoche;
    String nombre;
    String apellidos;
    String tlf;
    String email;
    //endregion

    //region Propiedades
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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
    public Cliente(int id, String matriculaCoche, String nombre, String apellidos, String tlf, String email) {
        this.id = id;
        this.matriculaCoche = matriculaCoche;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.tlf = tlf;
        this.email = email;
    }

    public  Cliente(){

    }
    //endregion

}
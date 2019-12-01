package com.pablolopezs.grepaut.data.model;

import androidx.lifecycle.ViewModel;

import java.util.Date;

public class Reparacion extends ViewModel {


    //region Campos
    int numeroReparacion;
    String fecha;
    int idcliente;
    String matriculaCoche;
    String nombreServicio;
    boolean estadoReparacion;// En funcion de si es true o false se escribira un string determinado u otro (En curso, Finalizada)
    boolean estadoFacturacdo;// En funcion de si es true o false se escribira un string determinado u otro (Facturado, No Facturado)
    String emailUsuario;
    String nombreEmpresa;
    //endregion

    //region Propiedades
    public int getNumeroReparacion() {
        return numeroReparacion;
    }

    public void setNumeroReparacion(int numeroReparacion) {
        this.numeroReparacion = numeroReparacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getMatriculaCoche() {
        return matriculaCoche;
    }

    public void setMatriculaCoche(String matriculaCoche) {
        this.matriculaCoche = matriculaCoche;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public boolean isEstadoReparacion() {
        return estadoReparacion;
    }

    public void setEstadoReparacion(boolean estadoReparacion) {
        this.estadoReparacion = estadoReparacion;
    }

    public boolean isEstadoFacturacdo() {
        return estadoFacturacdo;
    }

    public void setEstadoFacturacdo(boolean estadoFacturacdo) {
        this.estadoFacturacdo = estadoFacturacdo;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
    //endregion

    //region Constructores
    public Reparacion(int numeroReparacion, String fecha, int idcliente, String matriculaCoche, String nombreServicio, boolean estadoReparacion, boolean estadoFacturacdo, String emailUsuario, String nombreEmpresa) {
        this.numeroReparacion = numeroReparacion;
        this.fecha = fecha;
        this.idcliente = idcliente;
        this.matriculaCoche = matriculaCoche;
        this.nombreServicio = nombreServicio;
        this.estadoReparacion = estadoReparacion;
        this.estadoFacturacdo = estadoFacturacdo;
        this.emailUsuario = emailUsuario;
        this.nombreEmpresa = nombreEmpresa;
    }

    public  Reparacion() {

    }
    //endregion


}
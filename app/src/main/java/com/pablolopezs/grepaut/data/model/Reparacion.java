package com.pablolopezs.grepaut.data.model;

import androidx.lifecycle.ViewModel;

public class Reparacion extends ViewModel {


    //region Campos
    int numeroReparacion;
    String fecha;
    int idcliente;
    String matriculaCoche;
    String nombreServicio;
    boolean estadoReparacion;// En funcion de si es true o false se escribira un string determinado u otro (En curso, Finalizada)
    boolean estadoFacturado;// En funcion de si es true o false se escribira un string determinado u otro (Facturado, No Facturado)
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

    public boolean getEstadoReparacion() {
        return estadoReparacion;
    }

    public void setEstadoReparacion(boolean estadoReparacion) {
        this.estadoReparacion = estadoReparacion;
    }

    public boolean getEstadoFacturado() {
        return estadoFacturado;
    }

    public void setEstadoFacturado(boolean estadoFacturado) {
        this.estadoFacturado = estadoFacturado;
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
        this.estadoFacturado = estadoFacturacdo;
        this.emailUsuario = emailUsuario;
        this.nombreEmpresa = nombreEmpresa;
    }

    public  Reparacion() {

    }
    //endregion


}
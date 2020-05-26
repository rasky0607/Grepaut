package com.pablolopezs.grepaut.data.model;

import androidx.lifecycle.ViewModel;

public class Reparacion extends ViewModel {
    //region Campos
    int idReparacion;
    int numeroReparacion;
    String fecha;
    int idCliente;
    String nombreCliente;
    String matriculaCoche;
    String nombreServicio;
    boolean estadoReparacion;// En funcion de si es true o false se escribira un string determinado u otro (En curso, Finalizada)
    boolean estadoFacturado;// En funcion de si es true o false se escribira un string determinado u otro (Facturado, No Facturado)
    String emailUsuario;
    String nombreUsuario;
    //SIN USO ACTUALMENTE
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

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    //SIN USO ACTUALMENTE
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }
    //SIN USO ACTUALMENTE
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }




    //endregion

    //region Constructores
    public Reparacion(int numeroReparacion, String fecha,int idCliente, String nombreCliente, String matriculaCoche, String nombreServicio, boolean estadoReparacion, boolean estadoFacturacdo, String emailUsuario,String nombreUsuario) {
        this.numeroReparacion = numeroReparacion;
        this.fecha = fecha;
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.matriculaCoche = matriculaCoche;
        this.nombreServicio = nombreServicio;
        this.estadoReparacion = estadoReparacion;
        this.estadoFacturado = estadoFacturacdo;
        this.emailUsuario = emailUsuario;
        this.nombreUsuario=nombreUsuario;
    }

    //SIN USO ACTUALMENTE por la propiedad nombreEmpresa
    public Reparacion(int numeroReparacion, String fecha,int idCliente, String nombreCliente, String matriculaCoche, String nombreServicio, boolean estadoReparacion, boolean estadoFacturacdo, String emailUsuario,String nombreUsuario, String nombreEmpresa) {
        this.numeroReparacion = numeroReparacion;
        this.fecha = fecha;
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.matriculaCoche = matriculaCoche;
        this.nombreServicio = nombreServicio;
        this.estadoReparacion = estadoReparacion;
        this.estadoFacturado = estadoFacturacdo;
        this.emailUsuario = emailUsuario;
        this.nombreEmpresa = nombreEmpresa;
        this.nombreUsuario=nombreUsuario;
    }


    public  Reparacion() {

    }
    //endregion


}
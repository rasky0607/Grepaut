package com.pablolopezs.grepaut.data.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"numeroReparacion", "fecha", "matriculaCoche"})
public class Reparacion {
    //region Campos

    @NonNull
    int numeroReparacion;
    @NonNull
    String fecha;
    @NonNull
    String matriculaCoche;
    @NonNull
    String tecnico;
    @NonNull
    String nombreCliente;
    @NonNull
    String nombreServicio;
    @NonNull
    Double precioServicio;
    @NonNull
    Boolean estadoReparacion;// En funcion de si es true o false se escribira un string determinado u otro (En curso, Finalizada)
    @NonNull
    Boolean estadoFacturado;// En funcion de si es true o false se escribira un string determinado u otro (Facturado, No Facturado)

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

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(@NonNull String tecnico) {
        this.tecnico = tecnico;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public Double getPrecioServicio() {
        return precioServicio;
    }

    public void setPrecioServicio(Double precioServicio) {
        this.precioServicio = precioServicio;
    }

    public Boolean getEstadoReparacion() {
        return estadoReparacion;
    }

    public void setEstadoReparacion(Boolean estadoReparacion) {
        this.estadoReparacion = estadoReparacion;
    }

    public Boolean getEstadoFacturado() {
        return estadoFacturado;
    }

    public void setEstadoFacturado(Boolean estadoFacturado) {
        this.estadoFacturado = estadoFacturado;
    }

    //endregion

    //region Constructores


    @Ignore
    public  Reparacion() {

    }

    public Reparacion(int numeroReparacion, String fecha, String matriculaCoche,String tecnico, String nombreCliente,  String nombreServicio,Double precioServicio, Boolean estadoReparacion, Boolean estadoFacturado) {
        this.numeroReparacion = numeroReparacion;
        this.fecha = fecha;
        this.matriculaCoche=matriculaCoche;
        this.tecnico=tecnico;
        this.nombreCliente = nombreCliente;
        this.nombreServicio = nombreServicio;
        this.precioServicio=precioServicio;
        this.estadoReparacion = estadoReparacion;
        this.estadoFacturado = estadoFacturado;
    }


    //endregion


}
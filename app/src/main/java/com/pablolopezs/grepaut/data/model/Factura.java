package com.pablolopezs.grepaut.data.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Date;

public class Factura {

    //region Campos
    int numeroFactura;
    int lineaFactura; //Suele coincidir con el numero numeroReparacion de la clase Reparacion, pero realmente es el numero de reparaciones asignadas a un mismo numero de factura
    String fechaFacturacion;
    boolean estadoFactura;// En funcion de si es true o false se escribira un string determinado u otro (Vigente, Anulada)
    String fechaReparacion;
    String matriculaCocheRepara;
    //TODO PENDIENTES DE APLICAR ESTOS DOS CAMPOS
    String nombreServicio;
    double precioServicio;
    //---------------------------//
    String emailUsuario;
    String numeroFacturaAnulada;//Si se crea una nueva factura a raiz de la anulacion de otra, en este campo se hara referencia a la factura anulada a partir de la que se creo la nueva.
    //endregion

    //region Propiedades
    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public int getLineaFactura() {
        return lineaFactura;
    }

    public void setLineaFactura(int lineaFactura) {
        this.lineaFactura = lineaFactura;
    }

    public String getFechaFacturacion() {
        return fechaFacturacion;
    }

    public void setFechaFacturacion(String fechaFacturacion) {
        this.fechaFacturacion = fechaFacturacion;
    }

    public boolean getEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(boolean estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public String getFechaReparacion() {
        return fechaReparacion;
    }

    public void setFechaReparacion(String fechaReparacion) {
        this.fechaReparacion = fechaReparacion;
    }

    public String getMatriculaCocheRepara() {
        return matriculaCocheRepara;
    }

    public void setMatriculaCocheRepara(String matriculaCocheRepara) {
        this.matriculaCocheRepara = matriculaCocheRepara;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getNumeroFacturaAnulada() {
        return numeroFacturaAnulada;
    }

    public void setNumeroFacturaAnulada(String numeroFacturaAnulada) {
        this.numeroFacturaAnulada = numeroFacturaAnulada;
    }

    //endregion

    //region Constructor
    public Factura(int numeroFactura, int lineaFactura, String fechaFacturacion, boolean estadoFactura, String fechaReparacion, String matriculaCocheRepara, String emailUsuario, String numeroFacturaAnulada) {
        this.numeroFactura = numeroFactura;
        this.lineaFactura = lineaFactura;
        this.fechaFacturacion = fechaFacturacion;
        this.estadoFactura = estadoFactura;
        this.fechaReparacion = fechaReparacion;
        this.matriculaCocheRepara = matriculaCocheRepara;
        this.emailUsuario = emailUsuario;
        this.numeroFacturaAnulada = numeroFacturaAnulada;
    }

    //Costructor sin asginar numero de factura anulada, es decir cuando nuestra factura no hace referencia otra que esta anulada
    public Factura(int numeroFactura, int lineaFactura, String fechaFacturacion, boolean estadoFactura, String fechaReparacion,  String matriculaCocheRepara, String emailUsuario) {
        this.numeroFactura = numeroFactura;
        this.lineaFactura = lineaFactura;
        this.fechaFacturacion = fechaFacturacion;
        this.estadoFactura = estadoFactura;
        this.fechaReparacion = fechaReparacion;
        this.matriculaCocheRepara = matriculaCocheRepara;
        this.emailUsuario = emailUsuario;
    }
    public Factura(){

    }
    //endregion



}
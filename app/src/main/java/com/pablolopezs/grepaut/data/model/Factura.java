package com.pablolopezs.grepaut.data.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Date;

public class Factura {

    //region Campos
    int numeroFactura;
    int lineaFactura;
    Date fechaFacturacion;
    boolean estadoFactura;// En funcion de si es true o false se escribira un string determinado u otro (Vigente, Anulada)
    int numeroReparacion;
    Date fechaReparacion;
    int idClienteRepara;
    String matriculaCocheRepara;
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

    public Date getFechaFacturacion() {
        return fechaFacturacion;
    }

    public void setFechaFacturacion(Date fechaFacturacion) {
        this.fechaFacturacion = fechaFacturacion;
    }

    public boolean isEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(boolean estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public int getNumeroReparacion() {
        return numeroReparacion;
    }

    public void setNumeroReparacion(int numeroReparacion) {
        this.numeroReparacion = numeroReparacion;
    }

    public Date getFechaReparacion() {
        return fechaReparacion;
    }

    public void setFechaReparacion(Date fechaReparacion) {
        this.fechaReparacion = fechaReparacion;
    }

    public int getIdClienteRepara() {
        return idClienteRepara;
    }

    public void setIdClienteRepara(int idClienteRepara) {
        this.idClienteRepara = idClienteRepara;
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
    public Factura(int numeroFactura, int lineaFactura, Date fechaFacturacion, boolean estadoFactura, int numeroReparacion, Date fechaReparacion, int idClienteRepara, String matriculaCocheRepara, String emailUsuario, String numeroFacturaAnulada) {
        this.numeroFactura = numeroFactura;
        this.lineaFactura = lineaFactura;
        this.fechaFacturacion = fechaFacturacion;
        this.estadoFactura = estadoFactura;
        this.numeroReparacion = numeroReparacion;
        this.fechaReparacion = fechaReparacion;
        this.idClienteRepara = idClienteRepara;
        this.matriculaCocheRepara = matriculaCocheRepara;
        this.emailUsuario = emailUsuario;
        this.numeroFacturaAnulada = numeroFacturaAnulada;
    }

    public Factura(){

    }
    //endregion



}
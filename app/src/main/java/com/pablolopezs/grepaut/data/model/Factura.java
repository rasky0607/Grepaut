package com.pablolopezs.grepaut.data.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;
@Entity(primaryKeys = {"numeroFactura", "lineaFactura"})
public class Factura {

    //region Campos
    @NonNull
    @ColumnInfo
    int numeroFactura;
    @NonNull
    int lineaFactura; //Suele coincidir con el numero numeroReparacion de la clase Reparacion, pero realmente es el numero de reparaciones asignadas a un mismo numero de factura
    @NonNull
    String fechaFacturacion;
    @NonNull
    String fechaReparacion;
    @NonNull
    String matriculaCocheRepara;
    @NonNull
    boolean estadoFactura;// En funcion de si es true o false se escribira un string determinado u otro (Vigente, Anulada)

    @NonNull
    String nombreServicio;
    @NonNull
    double precioServicio;

    String numeroFacturaAnulada;//Si se crea una nueva factura a raiz de la anulacion de otra, en este campo se hara referencia a la factura anulada a partir de la que se creo la nueva.
    //---------------------------//
    //String emailUsuario;

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

    @NonNull
    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(@NonNull String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public double getPrecioServicio() {
        return precioServicio;
    }

    public void setPrecioServicio(double precioServicio) {
        this.precioServicio = precioServicio;
    }

    /*public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }*/

    public String getNumeroFacturaAnulada() {
        return numeroFacturaAnulada;
    }

    public void setNumeroFacturaAnulada(String numeroFacturaAnulada) {
        this.numeroFacturaAnulada = numeroFacturaAnulada;
    }

    //endregion

    //region Constructor
    public Factura(int numeroFactura, int lineaFactura, String fechaFacturacion,String fechaReparacion,String matriculaCocheRepara, boolean estadoFactura,String nombreServicio,Double precioServicio ) {
        this.numeroFactura = numeroFactura;
        this.lineaFactura = lineaFactura;
        this.fechaFacturacion = fechaFacturacion;
        this.fechaReparacion=fechaReparacion;
        this.matriculaCocheRepara = matriculaCocheRepara;
        this.estadoFactura = estadoFactura;
        this.nombreServicio=nombreServicio;
        this.precioServicio=precioServicio;
    }

    //Costructor sin asginar numero de factura anulada, es decir cuando nuestra factura no hace referencia otra que esta anulada
    @Ignore
    public Factura(int numeroFactura, int lineaFactura, String fechaFacturacion, boolean estadoFactura, String fechaReparacion,  String matriculaCocheRepara) {
        this.numeroFactura = numeroFactura;
        this.lineaFactura = lineaFactura;
        this.fechaFacturacion = fechaFacturacion;
        this.estadoFactura = estadoFactura;
        this.fechaReparacion = fechaReparacion;
        this.matriculaCocheRepara = matriculaCocheRepara;
        //this.emailUsuario = emailUsuario;
    }
    @Ignore
    public Factura(){

    }
    //endregion



}
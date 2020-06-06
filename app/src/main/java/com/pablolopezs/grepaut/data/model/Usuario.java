package com.pablolopezs.grepaut.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Usuario {

    @PrimaryKey
    @NonNull
    @ColumnInfo
    String email;
    @NonNull
    String password;
    @NonNull
    String nombre;
    @NonNull
    String tipoUsuario;
    @NonNull
    boolean tienePermiso; //Si es de tipo "Administrador", tiene permisos, si es de tipo "Usuario" debe darselo un administrador.(Solo puede haber un administrador en principio)
    String empresa;//Empresa a la que pertenece este servicio (es una FK de la tabla Empresa)//NO usado actualmente

    //region Propiedades
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public boolean getTienePermiso() {
        return tienePermiso;
    }

    public void setTienePermiso(boolean tienePermiso) {
        this.tienePermiso = tienePermiso;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    //endregion


    //region Constructores
    public Usuario(String email, String password, String nombre, String tipoUsuario, boolean tienePermiso) {
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.tipoUsuario = tipoUsuario;
        this.tienePermiso = tienePermiso;
    }

    //NO USADO ACTUALMENTE
    @Ignore
    public Usuario(String email, String password, String nombre, String tipoUsuario, boolean tienePermiso,String empresa) {
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.tipoUsuario = tipoUsuario;
        this.tienePermiso = tienePermiso;
        this.empresa = empresa;
    }

    @Ignore
    public Usuario() {

    }


    //endregion
}

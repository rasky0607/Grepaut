package com.pablolopezs.grepaut.data.model;

public class Usuario {

    String _email;
    String _password;
    String _nombre;
    String _tipoUsuario;
    boolean _tienePermiso; //Si es de tipo "Administrador", tiene permisos, si es de tipo "Usuario" debe darselo un administrador.(Solo puede haber un administrador en principio)

    //region Propiedades
    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String get_tipoUsuario() {
        return _tipoUsuario;
    }

    public void set_tipoUsuario(String _tipoUsuario) {
        this._tipoUsuario = _tipoUsuario;
    }

    public boolean is_tienePermiso() {
        return _tienePermiso;
    }

    public void set_tienePermiso(boolean _tienePermiso) {
        this._tienePermiso = _tienePermiso;
    }
    //endregion


    //region Constructores

    public Usuario(String _email, String _password, String _nombre, String _tipoUsuario, boolean _tienePermiso) {
        this._email = _email;
        this._password = _password;
        this._nombre = _nombre;
        this._tipoUsuario = _tipoUsuario;
        this._tienePermiso = _tienePermiso;
    }

    public Usuario() {

    }


    //endregion
}

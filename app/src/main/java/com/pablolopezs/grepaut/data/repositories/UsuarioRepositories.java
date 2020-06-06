package com.pablolopezs.grepaut.data.repositories;

import com.pablolopezs.grepaut.data.model.Usuario;

import java.util.ArrayList;

public class UsuarioRepositories {

    private ArrayList<Usuario> listusuario;

    //Unica instancia de esta clase
    static UsuarioRepositories INSTANCE;
    /*Este metodo devuelve la instancia del Repository*/
    public  static  UsuarioRepositories getInstance(){
        if(INSTANCE == null)
            INSTANCE = new UsuarioRepositories();

        return INSTANCE;
    }

    public UsuarioRepositories() {
        listusuario = new ArrayList<Usuario>();
        inicializa();
    }

    private void inicializa(){
        this.listusuario.add(new Usuario("email1@gmail.com","1234","pepe","Admin",true));
        this.listusuario.add(new Usuario("email2@gmail.com","1234","Maria","User",true));
    }

    public ArrayList<Usuario> getList(){
        return this.listusuario;
    }

    public void add(Usuario usuario)
    {
        this.listusuario.add(usuario);
    }

    //comprueba si el usuario indicado existe o es correcto y tiene permisos
    public boolean buscarUsuario(String email, String passwrord){
        for(Usuario item: listusuario)
        {
            if(item.getEmail().equals(email)&&item.getPassword().equals(passwrord) && item.getTienePermiso())
                return true;
        }
        return false;
    }
    public void delete(Usuario usuario)
    {
        this.listusuario.remove(usuario);
    }

    public Usuario getUsuario(String email, String password){
        Usuario u = new Usuario();
        for(Usuario item: listusuario){
            if(item.getEmail().equals(email) && item.getPassword().equals(password))
                u=item;
        }
        return  u;
    }
}

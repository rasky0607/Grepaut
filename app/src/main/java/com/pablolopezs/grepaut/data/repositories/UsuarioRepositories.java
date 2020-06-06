package com.pablolopezs.grepaut.data.repositories;

import com.pablolopezs.grepaut.data.dao.DaoContractBase;
import com.pablolopezs.grepaut.data.dao.GrepautDatabase;
import com.pablolopezs.grepaut.data.model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class UsuarioRepositories {
    Usuario usuarioActual;
    //ROOM
    private DaoContractBase.UsarioDaoContract dao;

    private List<Usuario> listusuario;

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
        //inicializa();
        dao= GrepautDatabase.getDatabase().daoUsuario();//Obteine la tabla de la BD
    }

    private void inicializa(){
        this.listusuario.add(new Usuario("email1@gmail.com","1234","pepe","Admin",true));
        this.listusuario.add(new Usuario("email2@gmail.com","1234","Maria","User",true));
    }

    public List<Usuario> getList(){
        //return this.listusuario;
        //ROOM
        try {
            return GrepautDatabase.databaseWriteExecutor.submit(() -> dao.getAll()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public boolean validarLoginUsuario(String email, String password){
        try {
            Usuario u= GrepautDatabase.databaseWriteExecutor.submit(() -> dao.getUnUsuario(email,password)).get();
            if(u!=null) {
                if (u.getEmail().equals(email) && u.getPassword().equals(password)){
                    usuarioActual=u;
                    return true;
                }
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
    //ROOM
    public boolean insert(final Usuario usuario){
        GrepautDatabase.databaseWriteExecutor.execute(() -> dao.insertDao(usuario));
        return true;
    }

    public boolean update(final Usuario usuario){
        GrepautDatabase.databaseWriteExecutor.execute(() -> dao.updateDao(usuario));
        return true;
    }

    public boolean delete(final Usuario usuario){
        GrepautDatabase.databaseWriteExecutor.execute(() -> dao.deleteDao(usuario));
        return true;
    }

    //Devuelve el usuario que inicio sesion
    public Usuario getUsuarioActual(){
        if(usuarioActual!=null)
            return usuarioActual;
        return null;
    }

}

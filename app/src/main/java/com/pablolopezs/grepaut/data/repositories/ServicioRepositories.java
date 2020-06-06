package com.pablolopezs.grepaut.data.repositories;

import com.pablolopezs.grepaut.data.dao.DaoContractBase;
import com.pablolopezs.grepaut.data.dao.GrepautDatabase;
import com.pablolopezs.grepaut.data.model.Servicio;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ServicioRepositories {
    //ROOM
    private DaoContractBase.ServicioDaoContract dao;
    //private List<Servicio> listServicio;
    //Unica instancia de esta clase
    static ServicioRepositories INSTANCE;
    /*Este metodo devuelve la instancia del Repository*/
    public  static  ServicioRepositories getInstance(){
        if(INSTANCE == null)
            INSTANCE = new ServicioRepositories();

        return INSTANCE;
    }

    public ServicioRepositories() {
        //listServicio = new ArrayList<Servicio>();
        //inicializa();
        //Room
        dao= GrepautDatabase.getDatabase().daoServicio();//Obteine la tabla de la BD
    }

    private void inicializa(){
        /*this.listServicio.add(new Servicio("Cambio de aceite",20.50,"desc1"));
        this.listServicio.add(new Servicio("Cambio de luces",60.50,"desc2"));
        this.listServicio.add(new Servicio("Cambio de neumatico",40.50,"desc3"));
        this.listServicio.add(new Servicio("Cambio de correa de distribucion",90,"desc4"));
        this.listServicio.add(new Servicio("Cambio de bujias",70,"desc5"));
        this.listServicio.add(new Servicio("Cambio de carburador",120.50,"desc6"));
        this.listServicio.add(new Servicio("Cambio de reporductor MP3",65.50,"desc7"));
        this.listServicio.add(new Servicio("Purga de deposito",45,"desc8"));
        this.listServicio.add(new Servicio("Instalacion de llantas",90.50,"desc9"));*/

    }

    public List<Servicio> getList(){

        //return this.listServicio;
        //room
        try {
            return GrepautDatabase.databaseWriteExecutor.submit(() -> dao.getAll()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  null;

    }

    //NO ROOM
    /*public void add(Servicio servicio)
    {
        //this.listServicio.add(servicio);
    }

    public void deleteRepositorio(Servicio servicio)
    {
        //this.listServicio.remove(servicio);
    }

    public void edit(int pos,Servicio servicio){
        //listServicio.add(pos,servicio);
    }*/

    //ROOM
    public boolean insert(final Servicio servicio){
        GrepautDatabase.databaseWriteExecutor.execute(() -> dao.insertDao(servicio));
        return true;
    }

    public boolean update(final Servicio servicio){
        GrepautDatabase.databaseWriteExecutor.execute(() -> dao.updateDao(servicio));
        return true;
    }

    public boolean delete(final Servicio servicio){
        GrepautDatabase.databaseWriteExecutor.execute(() -> dao.deleteDao(servicio));
        return true;
    }

    public double precioServicio(String nombre){
        Double precio=null;
        try {
             precio= GrepautDatabase.databaseWriteExecutor.submit(() -> dao.getPrecioServicio(nombre)).get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  precio;
    }

}

package com.pablolopezs.grepaut.data.repositories;

import android.util.Log;

import com.pablolopezs.grepaut.data.dao.DaoContractBase;
import com.pablolopezs.grepaut.data.dao.GrepautDatabase;
import com.pablolopezs.grepaut.data.model.Reparacion;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ReparacionRepositories {

    //ROOM
    private DaoContractBase.ReparacionDaoContract dao;
    private List<Reparacion>listReparaciones;
    //private  static ReparacionRepositories reparacionRepository;
    private List<Reparacion> listReparacionComunes; //Lista de reapracion que pertenecen aun mismo cliente en una misma fecha con un determinado vehiculo

    //Unica instancia de esta clase
    static ReparacionRepositories INSTANCE;

    /*Este metodo devuelve la instancia del Repository*/
    public  static ReparacionRepositories getInstance(){
        if(INSTANCE==null)
            INSTANCE = new ReparacionRepositories();
        return INSTANCE;
    }

    //Constructor
    public ReparacionRepositories(){
        //listReparaciones = new ArrayList<Reparacion>();
        //inicializa();
        //Room
        dao= GrepautDatabase.getDatabase().daoReparacion();
    }

    /*Llena la lista de reparaciones*/
    private  void inicializa(){

        /*this.listReparaciones.add(new Reparacion(1, "10/05/2018","Juan","5794GPL","Cambio de aceite",false,false));
        this.listReparaciones.add(new Reparacion(2, "10/05/2018","Juan","5794GPL","Cambio de luces",false,false));
        this.listReparaciones.add(new Reparacion(3, "10/05/2018","Juan","5794GPL","Cambio de neumaticos",false , false));
        this.listReparaciones.add(new Reparacion(4, "10/05/2018","Juan","5794GPL","Cambio de correa de distribucion",false,false));

        this.listReparaciones.add(new Reparacion(1, "10/07/2018","Juan","9712ROG","Cambio de bujias",true,true));
        this.listReparaciones.add(new Reparacion(2, "10/07/2018","Juan","9712ROG","Cambio de carburador",true,true));
        this.listReparaciones.add(new Reparacion(3, "10/07/2018","Juan","9712ROG","Cambio de reporductor MP3",true,true));
        this.listReparaciones.add(new Reparacion(4, "10/07/2018","Juan","9712ROG","Purga de deposito",true,true));

        this.listReparaciones.add(new Reparacion(1, "11/07/2018","Manolo","9712ROG","Cambio de bujias",false,false));
        this.listReparaciones.add(new Reparacion(2, "11/07/2018","Manolo","9712ROG","Cambio de carburador",false,false));
        this.listReparaciones.add(new Reparacion(3, "11/07/2018","Manolo","9712ROG","Cambio de reporductor MP3",false,false));
        this.listReparaciones.add(new Reparacion(4, "11/07/2018","Manolo","9712ROG","Purga de deposito",false,false));*/

    }

    public List<Reparacion>getList(){
        //return  this.listReparaciones;
        //Room
        try {
            return GrepautDatabase.databaseWriteExecutor.submit(() -> dao.getAll()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  null;
    }

    /**Devuelve una lista de todas las de reparaciones con la misma fecha,
     *  cliente y vehiculo que la que se ha selecionado en el fragment_reparacion_list_view*/
   /* NO ROOM
    public List<Reparacion>getListReparacionesComunes(){
        if(listReparacionComunes!=null)
            return this.listReparacionComunes;
        return null;
    }

    public void setListReparacionesComunes(List<Reparacion> list){
       this.listReparacionComunes = list;
    }


    public void add(Reparacion reparacion) {
        this.listReparaciones.add(reparacion);
    }

     //Elimina una reparacion, siempre que no este facturada
    //(Esto ya esta comprobado en ReparacionListAdapter)
    public void delete(int  pos) {
        this.listReparaciones.remove(pos);
    }
    */

    public List<Reparacion>getListReparacionesComunes(){
        if(listReparacionComunes!=null)
            return this.listReparacionComunes;
        return null;
    }
    public void setListReparacionesComunes(String fecha, String matricula){
        listReparacionComunes = null;
        try {
            listReparacionComunes= GrepautDatabase.databaseWriteExecutor.submit(() -> dao.getReparacionesComunes(fecha,matricula)).get();
            Log.d("lista"," el tamanio "+listReparacionComunes.size());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //ROOM
    public boolean insert(final Reparacion reparacion){
        GrepautDatabase.databaseWriteExecutor.execute(() -> dao.insertDao(reparacion));
        return true;
    }

    public boolean update(final Reparacion reparacion){
        GrepautDatabase.databaseWriteExecutor.execute(() -> dao.updateDao(reparacion));
        return true;
    }

    public boolean delete(final Reparacion reparacion){
        GrepautDatabase.databaseWriteExecutor.execute(() -> dao.deleteDao(reparacion));
        return true;
    }

    /*Busca reparaciones de un mismo cliente en un mismo dia, para determina el ultimo numero de reparacion de este cliente, para las nuevas reparacioens que se añadan sobre ese dia
    * Ya que cada reparacion esta representada por un [numero de reparacion, fecha, matricula de vehiculo]
    * Si no encuentra reparaciones comunes, devuelve -1*/
    public int buscarReparacionesExistentes(String fecha,String matricula){

        int num=0;
        try {
            num=GrepautDatabase.databaseWriteExecutor.submit(() -> dao.getNumeroReparacionesExistente(fecha,matricula)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return num;
    }

}

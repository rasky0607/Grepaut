package com.pablolopezs.grepaut.data.repositories;

import android.util.Log;

import com.pablolopezs.grepaut.data.model.Reparacion;

import java.util.ArrayList;

public class ReparacionRepositories {

    private ArrayList<Reparacion>listReparaciones;
    //private  static ReparacionRepositories reparacionRepository;
    private ArrayList<Reparacion> listReparacionComunes; //Lista de reapracion que pertenecen aun mismo cliente en una misma fecha con un determinado vehiculo

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
        listReparaciones = new ArrayList<Reparacion>();
        inicializa();
    }

    /*Llena la lista de reparaciones*/
    private  void inicializa(){

        this.listReparaciones.add(new Reparacion(1, "10/05/2018","Juan","5794GPL","Cambio de aceite",false,false,"email1@gmail.com","Carlos"));
        this.listReparaciones.add(new Reparacion(2, "10/05/2018","Juan","5794GPL","Cambio de luces",false,false,"email1@gmail.com","Carlos"));
        this.listReparaciones.add(new Reparacion(3, "10/05/2018","Juan","5794GPL","Cambio de neumaticos",false,false,"email1@gmail.com","Carlos"));
        this.listReparaciones.add(new Reparacion(4, "10/05/2018","Juan","5794GPL","Cambio de correa de distribucion",false,false,"email1@gmail.com","Carlos"));

        this.listReparaciones.add(new Reparacion(1, "10/07/2018","Juan","9712ROG","Cambio de bujias",true,true,"email1@gmail.com","Carlos"));
        this.listReparaciones.add(new Reparacion(2, "10/07/2018","Juan","9712ROG","Cambio de carburador",true,true,"email1@gmail.com","Carlos"));
        this.listReparaciones.add(new Reparacion(3, "10/07/2018","Juan","9712ROG","Cambio de reporductor MP3",true,true,"email1@gmail.com","Carlos"));
        this.listReparaciones.add(new Reparacion(4, "10/07/2018","Juan","9712ROG","Purga de deposito",true,true,"email1@gmail.com","Carlos"));

        this.listReparaciones.add(new Reparacion(1, "11/07/2018","Manolo","9712ROG","Cambio de bujias",false,false,"email1@gmail.com","Oscar"));
        this.listReparaciones.add(new Reparacion(2, "11/07/2018","Manolo","9712ROG","Cambio de carburador",false,false,"email1@gmail.com","Oscar"));
        this.listReparaciones.add(new Reparacion(3, "11/07/2018","Manolo","9712ROG","Cambio de reporductor MP3",false,false,"email1@gmail.com","Oscar"));
        this.listReparaciones.add(new Reparacion(4, "11/07/2018","Manolo","9712ROG","Purga de deposito",false,false,"email1@gmail.com","Oscar"));
    }

    public ArrayList<Reparacion>getList(){
        return  this.listReparaciones;
    }

    /**Devuelve una lista de todas las de reparaciones con la misma fecha,
     *  cliente y vehiculo que la que se ha selecionado en el fragment_reparacion_list_view*/
    public ArrayList<Reparacion>getListReparacionesComunes(){
        if(listReparacionComunes!=null)
            return this.listReparacionComunes;
        return null;
    }

    public void setListReparacionesComunes(ArrayList<Reparacion>list){
       this.listReparacionComunes = list;
    }


    public void add(Reparacion reparacion) {
        this.listReparaciones.add(reparacion);
    }

    /*Edita una reparacion*/
    public void edit(int numeroReparacion,String fecha, String matriculaCoche) {
        for(Reparacion item:this.listReparaciones)
        {
            if(item.getNumeroReparacion()==numeroReparacion && item.getMatriculaCoche().equals(matriculaCoche))
            {
                /*IMPORTANTE:En principio una reparacion, no puede ser editada,para ello, se elimina y se crea una nueva,
                a no ser que sta este facturada, en cuyo caso , no podra ser eliminada, para ello primero habra que anular dicha factura emitida*/
            }
        }
    }

    /*Elimina una reparacion, siempre que no este facturada
    (Esto ya esta comprobado en ReparacionListAdapter)*/
    public void delete(int  pos) {
            this.listReparaciones.remove(pos);
    }

    /*Busca reparaciones de un mismo cliente en un mismo dia, para determina el ultimo numero de reparacion de este cliente, para las nuevas reparacioens que se añadan sobre ese dia
    * Ya que cada reparacion esta representada por un [numero de reparacion, fecha, matricula de vehiculo]
    * Si no encuentra reparaciones comunes, devuelve -1*/
    public int buscarReparacionesExistentes(String matricula, String fecha){
        int numeroReparacion=-1;
        for(Reparacion item:listReparaciones){
            if(item.getFecha().equals(fecha) &&item.getMatriculaCoche().equals(matricula)){
                if(item.getNumeroReparacion()>numeroReparacion) {
                    numeroReparacion = item.getNumeroReparacion();
                }
            }
        }
        return numeroReparacion;
    }

}

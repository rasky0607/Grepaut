package com.pablolopezs.grepaut.data.repositories;

import com.pablolopezs.grepaut.data.model.Reparacion;

import java.util.ArrayList;

public class ReparacionRepositories {


    private ArrayList<Reparacion>listReparaciones;
    private  static ReparacionRepositories reparacionRepository;

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
        listReparaciones = new ArrayList<>();
        inicializa();
    }

    /*Llena la lista de reparaciones*/
    private  void inicializa(){

        this.listReparaciones.add(new Reparacion(1, "10/05/2018",1,"5794GPL","Cambio de aceite",false,false,"email1@gmail.com","RepaAuto S.L."));
        this.listReparaciones.add(new Reparacion(2, "10/05/2018",1,"5794GPL","Cambio de luces",true,true,"email1@gmail.com","RepaAuto S.L."));
        this.listReparaciones.add(new Reparacion(3, "10/05/2018",1,"5794GPL","Cambio de neumaticos",false,false,"email1@gmail.com","RepaAuto S.L."));
        this.listReparaciones.add(new Reparacion(4, "10/05/2018",1,"5794GPL","Cambio de correa de distribucion",false,false,"email1@gmail.com","RepaAuto S.L."));

        this.listReparaciones.add(new Reparacion(1, "10/07/2018",1,"9712ROG","Cambio de bujias",true,true,"email1@gmail.com","RepaAuto S.L."));
        this.listReparaciones.add(new Reparacion(2, "10/07/2018",1,"9712ROG","Cambio de carburador",false,false,"email1@gmail.com","RepaAuto S.L."));
        this.listReparaciones.add(new Reparacion(3, "10/07/2018",1,"9712ROG","Cambio de reporductor MP3",false,false,"email1@gmail.com","RepaAuto S.L."));
        this.listReparaciones.add(new Reparacion(4, "10/07/2018",1,"9712ROG","Purga de deposito",true,true,"email1@gmail.com","RepaAuto S.L."));

        this.listReparaciones.add(new Reparacion(1, "11/07/2018",2,"9712ROG","Cambio de bujias",true,true,"email1@gmail.com","RepaAuto S.L."));
        this.listReparaciones.add(new Reparacion(2, "11/07/2018",2,"9712ROG","Cambio de carburador",false,false,"email1@gmail.com","RepaAuto S.L."));
        this.listReparaciones.add(new Reparacion(3, "11/07/2018",2,"9712ROG","Cambio de reporductor MP3",false,false,"email1@gmail.com","RepaAuto S.L."));
        this.listReparaciones.add(new Reparacion(4, "11/07/2018",2,"9712ROG","Purga de deposito",true,true,"email1@gmail.com","RepaAuto S.L."));
    }

    public ArrayList<Reparacion>getList(){
        return  this.listReparaciones;
    }


    public void add(Reparacion reparacion) {
        this.listReparaciones.add(reparacion);
    }

    /*Edita una reparacion*/
    public void edit(int numeroReparacion,String fecha,int idCliente, String matriculaCoche) {
        for(Reparacion item:this.listReparaciones)
        {
            if(item.getFecha().equals(fecha) && item.getIdcliente()==idCliente && item.getNumeroReparacion()==numeroReparacion && item.getMatriculaCoche().equals(matriculaCoche))
            {
                /*IMPORTANTE:En principio una reparacion, no puede ser editada,para ello, se elimina y se crea una nueva,
                a no ser que sta este facturada, en cuyo caso , no podra ser eliminada, para ello primero habra que anular dicha factura emitida*/
            }
        }
    }
    /*Elimina una reparacion, siempre que no este facturada*/
    /*public void delete(Reparacion reparacion) {
        if(!reparacion.getEstadoFacturado())
            this.listReparaciones.remove(reparacion);
    }*/

    public void delete(int  pos) {
            this.listReparaciones.remove(pos);
    }


}

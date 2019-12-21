package com.pablolopezs.grepaut.data.repository;

import android.util.Log;

import com.pablolopezs.grepaut.data.model.Reparacion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ReparacionRepository {

    private List<Reparacion>listReparaciones;
    private  static  ReparacionRepository reparacionRepository;

    //Como inicializar en el ambito static una variable estatica
    /*Se inicializa en el siguiente bloque todas las propiedades estaticas de la clase
     * sin tener que realizarlo en un metodo.
     * Este bloque si inicia antes que el constructor y cualquier otro bloque (y evita comprobar si es null)*/
    static {
        reparacionRepository = new ReparacionRepository();
    }

    //Constructor Privado ya que solo exite una  instancia/objeto de Repository
    private  ReparacionRepository(){
        listReparaciones = new ArrayList<>();
        inicializa();
    }

    /*Este metodo devuelve la instancia del Repository que siempre se inicializa en el bloque static*/
    public static ReparacionRepository getInstance(){
        return  reparacionRepository;
    }

    /*Llena la lista de reparaciones*/
    private  void inicializa(){

        listReparaciones.add(new Reparacion(1, "10/05/2018",1,"5794GPL","Cambio de aceite",false,false,"email1@gmail.com","RepaAuto S.L."));
        listReparaciones.add(new Reparacion(2, "10/05/2018",1,"5794GPL","Cambio de luces",true,true,"email1@gmail.com","RepaAuto S.L."));
        listReparaciones.add(new Reparacion(3, "10/05/2018",1,"5794GPL","Cambio de neumaticos",false,false,"email1@gmail.com","RepaAuto S.L."));
        listReparaciones.add(new Reparacion(4, "10/05/2018",1,"5794GPL","Cambio de correa de distribucion",false,false,"email1@gmail.com","RepaAuto S.L."));

        listReparaciones.add(new Reparacion(1, "10/07/2018",1,"9712ROG","Cambio de bujias",true,true,"email1@gmail.com","RepaAuto S.L."));
        listReparaciones.add(new Reparacion(2, "10/07/2018",1,"9712ROG","Cambio de carburador",false,false,"email1@gmail.com","RepaAuto S.L."));
        listReparaciones.add(new Reparacion(3, "10/07/2018",1,"9712ROG","Cambio de reporductor MP3",false,false,"email1@gmail.com","RepaAuto S.L."));
        listReparaciones.add(new Reparacion(4, "10/07/2018",1,"9712ROG","Purga de deposito",true,true,"email1@gmail.com","RepaAuto S.L."));
    }

    public List<Reparacion>getList(){
        return  listReparaciones;
    }


    public void add(Reparacion reparacion) {

    }

    public void edit(Reparacion reparacion) {

    }

    public void delete(Reparacion reparacion) {

    }


}

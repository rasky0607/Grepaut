package com.pablolopezs.grepaut.data.repositories;

import com.pablolopezs.grepaut.data.model.Servicio;

import java.util.ArrayList;

public class ServicioRepositories {
    private ArrayList<Servicio> listServicio;

    //Unica instancia de esta clase
    static ServicioRepositories INSTANCE;
    /*Este metodo devuelve la instancia del Repository*/
    public  static  ServicioRepositories getInstance(){
        if(INSTANCE == null)
            INSTANCE = new ServicioRepositories();

        return INSTANCE;
    }

    public ServicioRepositories() {
        listServicio = new ArrayList<Servicio>();
        inicializa();
    }

    private void inicializa(){
        this.listServicio.add(new Servicio("Cambio de aceite",20.50,"desc1"));
        this.listServicio.add(new Servicio("Cambio de luces",60.50,"desc2"));
        this.listServicio.add(new Servicio("Cambio de neumatico",40.50,"desc3"));
        this.listServicio.add(new Servicio("Cambio de correa de distribucion",90,"desc4"));
        this.listServicio.add(new Servicio("Cambio de bujias",70,"desc5"));
        this.listServicio.add(new Servicio("Cambio de carburador",120.50,"desc6"));
        this.listServicio.add(new Servicio("Cambio de reporductor MP3",65.50,"desc7"));
        this.listServicio.add(new Servicio("Purga de deposito",45,"desc8"));
        this.listServicio.add(new Servicio("Instalacion de llantas",90.50,"desc9"));

    }

    public ArrayList<Servicio> getList(){
        return this.listServicio;
    }

    public void add(Servicio servicio)
    {
        this.listServicio.add(servicio);
    }

    public void delete(Servicio servicio)
    {
        this.listServicio.remove(servicio);
    }

    public void edit(int pos,Servicio servicio){
        listServicio.add(pos,servicio);
    }
}

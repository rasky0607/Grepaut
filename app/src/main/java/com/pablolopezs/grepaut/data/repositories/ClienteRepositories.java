package com.pablolopezs.grepaut.data.repositories;

import com.pablolopezs.grepaut.data.dao.DaoContractBase;
import com.pablolopezs.grepaut.data.dao.GrepautDatabase;
import com.pablolopezs.grepaut.data.model.Cliente;
import com.pablolopezs.grepaut.data.model.Reparacion;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ClienteRepositories {
    //ROOM
    private DaoContractBase.ClienteDaoContract dao;
    //private List<Cliente> listClientes;

    //Unica instancia de esta clase
    static ClienteRepositories INSTANCE;
    /*Este metodo devuelve la instancia del Repository*/
    public  static  ClienteRepositories getInstance(){
        if(INSTANCE == null)
            INSTANCE = new ClienteRepositories();

        return INSTANCE;
    }

    public ClienteRepositories() {
      //listClientes = new ArrayList<Cliente>();
      //inicializa();
        //ROOM
        dao= GrepautDatabase.getDatabase().daoCliente();//Obteine la tabla de la BD
    }

    private void inicializa(){
        /*this.listClientes.add(new Cliente("5794GPL","Juan Martin Dominguez","632220105"));
        this.listClientes.add(new Cliente("ER8921","Juan Martin Dominguez","637022175"));
        this.listClientes.add(new Cliente("APL4729","Daniel Martin Perez","637426144"));
        this.listClientes.add(new Cliente("3456PG","Daniel Martin Perez","637620185"));
        this.listClientes.add(new Cliente("3466PZ","Marcos Lopez Nuñez","637620132"));
        this.listClientes.add(new Cliente("3456KG","Manuel Merendez Dominguez","637230195"));
        this.listClientes.add(new Cliente("3446LY","Lolo Marin Santana","638224169"));
        this.listClientes.add(new Cliente("1456PG","Maria Martin Santana","632223125"));
        this.listClientes.add(new Cliente("9356OG","Ester Vazquez Baquero","632520102"));
        this.listClientes.add(new Cliente("3254PK","Carlos Montoro Gonzalez","638420121"));
        this.listClientes.add(new Cliente("9452PL","Enrrique Rojas Aguilar","667420203"));
        this.listClientes.add(new Cliente("7476AL","Nicolas Landon Mendez","651223109"));
        this.listClientes.add(new Cliente("9153DJ","Salvador Jimenez Bueno","627325405"));
        this.listClientes.add(new Cliente("9426UT","Jaime Nalda Navarro","637234101"));*/
    }
  /*NO ROOM
    public void add(Cliente cliente)
    {
        this.listClientes.add(cliente);
    }

    public void edit(Cliente cliente){
      for (Cliente item:listClientes)
      {
          if( item.getMatriculaCoche().equals(cliente.getMatriculaCoche()))
          {
              item.setMatriculaCoche(cliente.getMatriculaCoche());
              item.setEmail(cliente.getEmail());
              item.setNombre(cliente.getNombre());
              item.setTlf(cliente.getTlf());
          }
      }
    }

    public void delete(Cliente cliente)
    {
        this.listClientes.remove(cliente);
    }*/


    public List<Cliente> getList(){
      //return this.listClientes;
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
    //ROOM
    public boolean insert(final Cliente cliente){
        GrepautDatabase.databaseWriteExecutor.execute(() -> dao.insertDao(cliente));
        return true;
    }

    public boolean update(final Cliente cliente){
        GrepautDatabase.databaseWriteExecutor.execute(() -> dao.updateDao(cliente));
        return true;
    }

    public boolean delete(final Cliente cliente){
        GrepautDatabase.databaseWriteExecutor.execute(() -> dao.deleteDao(cliente));
        return true;
    }

    //Dada una Matricula devuelve el nombre del cliente, si devuelve "<Sin nombre>" es que no la encontro
    public String buscarNobreCliente(String matricula){
        /*for(Cliente item:listClientes)
        {
            if(item.getMatriculaCoche().equals(matricula))
            {
                return item.getNombre();
            }
        }*/
        //return  "<Sin nombre>";
        //ROOM
        String nombreCliente =null;
        try {
            nombreCliente = GrepautDatabase.databaseWriteExecutor.submit(() -> dao.getNombreCliente(matricula)).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        if(nombreCliente == null)
            nombreCliente="<Sin nombre>";

        return nombreCliente;

    }

}

package com.pablolopezs.grepaut.data.repositories;

import com.pablolopezs.grepaut.data.model.Cliente;
import com.pablolopezs.grepaut.data.model.Reparacion;

import java.util.ArrayList;

public class ClienteRepositories {
    private ArrayList<Cliente> listClientes;

    //Unica instancia de esta clase
    static ClienteRepositories INSTANCE;
    /*Este metodo devuelve la instancia del Repository*/
    public  static  ClienteRepositories getInstance(){
        if(INSTANCE == null)
            INSTANCE = new ClienteRepositories();

        return INSTANCE;
    }

    public ClienteRepositories() {
      listClientes = new ArrayList<Cliente>();
      inicializa();
    }

    private void inicializa(){
        this.listClientes.add(new Cliente("5794GPL","Juan Martin Dominguez","632220105","email1@gmail.com"));
        this.listClientes.add(new Cliente("ER8921","Juan Martin Dominguez","637022175","email1@gmail.com"));
        this.listClientes.add(new Cliente("APL4729","Daniel Martin Perez","637426144","email1@gmail.com"));
        this.listClientes.add(new Cliente("3456PG","Daniel Martin Perez","637620185","email1@gmail.com"));
        this.listClientes.add(new Cliente("3466PZ","Marcos Lopez Nuñez","637620132","email1@gmail.com"));
        this.listClientes.add(new Cliente("3456KG","Manuel Merendez Dominguez","637230195","email1@gmail.com"));
        this.listClientes.add(new Cliente("3446LY","Lolo Marin Santana","638224169","email1@gmail.com"));
        this.listClientes.add(new Cliente("1456PG","Maria Martin Santana","632223125","email1@gmail.com"));
        this.listClientes.add(new Cliente("9356OG","Ester Vazquez Baquero","632520102","email1@gmail.com"));
        this.listClientes.add(new Cliente("3254PK","Carlos Montoro Gonzalez","638420121","email1@gmail.com"));
        this.listClientes.add(new Cliente("9452PL","Enrrique Rojas Aguilar","667420203","email1@gmail.com"));
        this.listClientes.add(new Cliente("7476AL","Nicolas Landon Mendez","651223109","email1@gmail.com"));
        this.listClientes.add(new Cliente("9153DJ","Salvador Jimenez Bueno","627325405","email1@gmail.com"));
        this.listClientes.add(new Cliente("9426UT","Jaime Nalda Navarro","637234101","email1@gmail.com"));
    }

    public ArrayList<Cliente> getList(){
      return this.listClientes;
    }

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
    }

    //Dada una Matricula devuelve el nombre del cliente, si devuelve "<Sin nombre>" es que no la encontro
    public String buscarNobreCliente(String matricula){
        for(Cliente item:listClientes)
        {
            if(item.getMatriculaCoche().equals(matricula))
            {
                return item.getNombre();
            }
        }
        return  "<Sin nombre>";
    }
}

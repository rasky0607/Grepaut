package com.pablolopezs.grepaut.data.repositories;

import com.pablolopezs.grepaut.data.model.Cliente;

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
        this.listClientes.add(new Cliente(1,"5794GPL","Juan","Martin Dominguez","632220105","email1@gmail.com"));
        this.listClientes.add(new Cliente(1,"ER8921","Juan","Martin Dominguez","637022175","email1@gmail.com"));
        this.listClientes.add(new Cliente(2,"APL4729","Daniel","Martin Perez","637426144","email1@gmail.com"));
        this.listClientes.add(new Cliente(2,"3456PG","Daniel","Martin Perez","637620185","email1@gmail.com"));
        this.listClientes.add(new Cliente(3,"3456PG","Marcos","Lopez Nuñez","637620132","email1@gmail.com"));
        this.listClientes.add(new Cliente(4,"3456PG","Manuel","Merendez Dominguez","637230195","email1@gmail.com"));
        this.listClientes.add(new Cliente(5,"3456PG","Lolo","Marin Santana","638224169","email1@gmail.com"));
        this.listClientes.add(new Cliente(6,"3456PG","Maria","Martin Santana","632223125","email1@gmail.com"));
        this.listClientes.add(new Cliente(7,"3456PG","Ester","Vazquez Baquero","632520102","email1@gmail.com"));
        this.listClientes.add(new Cliente(8,"3456PG","Carlos","Montoro Gonzalez","638420121","email1@gmail.com"));
        this.listClientes.add(new Cliente(9,"3456PG","Enrrique","Rojas Aguilar","667420203","email1@gmail.com"));
        this.listClientes.add(new Cliente(10,"3456PG","Nicolas","Landon Mendez","651223109","email1@gmail.com"));
        this.listClientes.add(new Cliente(11,"3456PG","Salvador","Jimenez Bueno","627325405","email1@gmail.com"));
        this.listClientes.add(new Cliente(12,"3456PG","Jaime","Nalda Navarro","637234101","email1@gmail.com"));
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
          if(item.getId() == cliente.getId() && item.getMatriculaCoche().equals(cliente.getMatriculaCoche()))
          {
              item.setMatriculaCoche(cliente.getMatriculaCoche());
              item.setEmail(cliente.getEmail());
              item.setNombre(cliente.getNombre());
              item.setApellidos(cliente.getApellidos());
              item.setTlf(cliente.getTlf());
          }
      }
    }

    public void delete(Cliente cliente)
    {
        this.listClientes.remove(cliente);
    }
}

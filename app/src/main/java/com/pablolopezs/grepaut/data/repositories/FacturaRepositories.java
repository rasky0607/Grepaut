package com.pablolopezs.grepaut.data.repositories;

import com.pablolopezs.grepaut.data.model.Factura;

import java.util.ArrayList;
import java.util.Calendar;
//Nota: Una factura no puede ser Editada, anulada ni creada, solo puede ser generada por una reparacion o una serie de reparaciones desde la vista en detalle "ReparacionDetailListView
public class FacturaRepositories {
    private ArrayList<Factura> listFactura;

    //Unica instancia de esta clase
    static FacturaRepositories INSTANCE;
    /*Este metodo devuelve la instancia del Repository*/
    public  static  FacturaRepositories getInstance(){
        if(INSTANCE == null)
            INSTANCE = new FacturaRepositories();

        return INSTANCE;
    }

    public FacturaRepositories() {
        listFactura = new ArrayList<Factura>();
        inicializa();
    }

    private void inicializa(){
        Calendar cFechaHoy=Calendar.getInstance();
        int day= cFechaHoy.get(Calendar.DAY_OF_MONTH);
        int month = cFechaHoy.get(Calendar.MONTH);
        int year = cFechaHoy.get(Calendar.YEAR);
        String fecha=Integer.toString(day)+"/"+Integer.toString(month)+"/"+Integer.toString(year);
        this.listFactura.add(new Factura(1,1,fecha,false,"10/07/2018","9712ROG","email1@gmail.com"));
        this.listFactura.add(new Factura(1,2,fecha,false,"10/07/2018","9712ROG","email1@gmail.com"));
        this.listFactura.add(new Factura(1,3,fecha,false,"10/07/2018","9712ROG","email1@gmail.com"));
        this.listFactura.add(new Factura(1,4,fecha,false,"10/07/2018","9712ROG","email1@gmail.com"));

    }

    //Nos devuelde el ultimo numeroFactura creada, para que ala hora de generar una nueva, sea ese numero del ultimo elemento +1
    public int ultimoNumeroFact(){
        Factura f=listFactura.get(listFactura.size()-1);//Cogemos el ultimo elemento de la lista de facturas
        return  f.getNumeroFactura();//Devolvemos el numero de factura del ultimo elemento en la lista
    }
    public ArrayList<Factura> getList(){
        return this.listFactura;
    }

    public void add(Factura factura)
    {
        this.listFactura.add(factura);
    }

    public void delete(Factura factura)
    {
        this.listFactura.remove(factura);
    }
}

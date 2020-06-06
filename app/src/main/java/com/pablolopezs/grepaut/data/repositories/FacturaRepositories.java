package com.pablolopezs.grepaut.data.repositories;

import com.pablolopezs.grepaut.data.dao.DaoContractBase;
import com.pablolopezs.grepaut.data.dao.GrepautDatabase;
import com.pablolopezs.grepaut.data.model.Factura;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

//Nota: Una factura no puede ser Editada, anulada ni creada, solo puede ser generada por una reparacion o una serie de reparaciones desde la vista en detalle "ReparacionDetailListView
public class FacturaRepositories {
    //ROOM
    private DaoContractBase.FacturaDaoContract dao;
    //private List<Factura> listFactura;

    //Unica instancia de esta clase
    static FacturaRepositories INSTANCE;
    /*Este metodo devuelve la instancia del Repository*/
    public  static  FacturaRepositories getInstance(){
        if(INSTANCE == null)
            INSTANCE = new FacturaRepositories();

        return INSTANCE;
    }

    public FacturaRepositories() {
        //listFactura = new ArrayList<Factura>();
        //Room
        dao= GrepautDatabase.getDatabase().daoFactura();//Obteine la tabla de la BD
        //inicializa();
    }

    private void inicializa(){
        /*Calendar cFechaHoy=Calendar.getInstance();
        int day= cFechaHoy.get(Calendar.DAY_OF_MONTH);
        int month = cFechaHoy.get(Calendar.MONTH);
        int year = cFechaHoy.get(Calendar.YEAR);
        String fecha=Integer.toString(day)+"/"+Integer.toString(month)+"/"+Integer.toString(year);
        this.listFactura.add(new Factura(1,1,fecha,false,"10/07/2018","9712ROG"));
        this.listFactura.add(new Factura(1,2,fecha,false,"10/07/2018","9712ROG"));
        this.listFactura.add(new Factura(1,3,fecha,false,"10/07/2018","9712ROG"));
        this.listFactura.add(new Factura(1,4,fecha,false,"10/07/2018","9712ROG"));*/

    }



   /* NO ROOM
    public void add(Factura factura)
    {
        this.listFactura.add(factura);
    }

    public void delete(Factura factura)
    {
        this.listFactura.remove(factura);
    }*/


    //Nos devuelde el ultimo numeroFactura creada, para que ala hora de generar una nueva, sea ese numero del ultimo elemento +1
    public int ultimoNumeroFact(){
        //Factura f=listFactura.get(listFactura.size()-1);//Cogemos el ultimo elemento de la lista de facturas
        //return  f.getNumeroFactura();//Devolvemos el numero de factura del ultimo elemento en la lista
        //ROOM
        int ultimoNumeroFactura=0;
        try {
            ultimoNumeroFactura= GrepautDatabase.databaseWriteExecutor.submit(() -> dao.getNumeroUltimaFacturasExistente()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  ultimoNumeroFactura;
    }

    //ROOM
    public List<Factura> getList(){
        //return this.listFactura;
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

    public boolean insert(final Factura factura){
        GrepautDatabase.databaseWriteExecutor.execute(() -> dao.insertDao(factura));
        return true;
    }

    public boolean update(final Factura factura){
        GrepautDatabase.databaseWriteExecutor.execute(() -> dao.updateDao(factura));
        return true;
    }

    public boolean delete(final Factura factura){
        GrepautDatabase.databaseWriteExecutor.execute(() -> dao.deleteDao(factura));
        return true;
    }
}

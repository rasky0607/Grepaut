package com.pablolopezs.grepaut.data.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.pablolopezs.grepaut.data.model.Cliente;
import com.pablolopezs.grepaut.data.model.Factura;
import com.pablolopezs.grepaut.data.model.Reparacion;
import com.pablolopezs.grepaut.data.model.Servicio;
import com.pablolopezs.grepaut.data.model.Usuario;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/*Esta calse es la encargada de crear la Base de datos*/
@Database(entities = {Servicio.class, Reparacion.class, Cliente.class, Factura.class, Usuario.class},version =11,exportSchema = false)
public abstract class GrepautDatabase extends RoomDatabase {

    public abstract DaoContractBase.ServicioDaoContract daoServicio();
    public abstract DaoContractBase.ClienteDaoContract daoCliente();
    public abstract DaoContractBase.ReparacionDaoContract daoReparacion();
    public abstract DaoContractBase.FacturaDaoContract daoFactura();
    public abstract DaoContractBase.UsarioDaoContract daoUsuario();

    private static volatile GrepautDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static void create(final Context context) {
        if (INSTANCE == null) {
            synchronized (GrepautDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            GrepautDatabase.class, "grepaut")//Nombre de la base de datos
                            .fallbackToDestructiveMigration() //para borrar la base de datos en cada nueva ejecucion
                            .build();
                }
            }
        }
    }

    public static GrepautDatabase getDatabase() {
        return INSTANCE;
    }
}

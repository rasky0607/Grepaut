package com.pablolopezs.grepaut.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.pablolopezs.grepaut.data.model.Cliente;
import com.pablolopezs.grepaut.data.model.Factura;
import com.pablolopezs.grepaut.data.model.Reparacion;
import com.pablolopezs.grepaut.data.model.Servicio;
import com.pablolopezs.grepaut.data.model.Usuario;

import java.util.List;

public class DaoContractBase {
    //metodos que tiene estra tabla [inser, delete etc]
    @Dao
    public interface ContractDao<T>{
        @Insert
        long insertDao(T objeto);
        @Delete
        void deleteDao(T objeto);
        @Update
        void updateDao(T Objeto);
    }
    @Dao
    public interface ServicioDaoContract extends ContractDao<Servicio>
    {
        @Query("SELECT * FROM SERVICIO")
        List<Servicio> getAll();
        @Query("SELECT precio FROM SERVICIO WHERE nombre= :nombre")
        Double getPrecioServicio(String nombre);
    }
    @Dao
    public interface ClienteDaoContract extends ContractDao<Cliente>
    {
        @Query("SELECT * FROM CLIENTE")
        List<Cliente> getAll();
        @Query("SELECT nombre FROM CLIENTE WHERE matriculaCoche= :matriculaCoche")
        String getNombreCliente(String matriculaCoche);
    }
    @Dao
    public interface ReparacionDaoContract extends ContractDao<Reparacion>
    {
        @Query("SELECT * FROM REPARACION ORDER BY fecha asc")
        List<Reparacion> getAll();
        @Query("SELECT * FROM REPARACION WHERE fecha= :fecha and matriculaCoche= :matriculaCoche")
        List<Reparacion> getReparacionesComunes(String fecha,String matriculaCoche);
        //@Query("SELECT MAX(numeroReparacion) FROM REPARACION WHERE fecha= :fecha and matriculaCoche= :matriculaCoche")
        //int getNumeroReparacionesExistente(String fecha,String matriculaCoche);

        //EN prueba
        @Query("SELECT numeroReparacion FROM REPARACION WHERE fecha= :fecha and matriculaCoche= :matriculaCoche order by numeroReparacion desc limit 1")
        int getNumeroReparacionesExistente(String fecha,String matriculaCoche);

    }
    @Dao
    public interface FacturaDaoContract extends ContractDao<Factura>
    {
        @Query("SELECT * FROM FACTURA")
        List<Factura> getAll();
        @Query("SELECT MAX(numeroFactura)as numeroFactura FROM FACTURA")
        int getNumeroUltimaFacturasExistente();

    }
    @Dao
    public interface UsarioDaoContract extends ContractDao<Usuario>
    {
        @Query("SELECT * FROM USUARIO")
        List<Usuario> getAll();
        @Query("SELECT * FROM USUARIO WHERE email= :email and password= :password")
        Usuario getUnUsuario(String email, String password);

    }

}

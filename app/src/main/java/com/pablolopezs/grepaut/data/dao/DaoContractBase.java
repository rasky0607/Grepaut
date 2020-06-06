package com.pablolopezs.grepaut.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.pablolopezs.grepaut.data.model.Reparacion;
import com.pablolopezs.grepaut.data.model.Servicio;

import java.util.List;

public class BaseDaoContract {
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
    public interface ContractDaoServicio extends ContractDao<Servicio>
    {
        @Query("SELECT * FROM SERVICIO")
        List<Servicio> getAll();
    }
    public interface ContractDaoReparacion extends ContractDao<Reparacion>
    {
        @Query("SELECT * FROM REPARACION")
        List<Reparacion> getAll();
    }
}

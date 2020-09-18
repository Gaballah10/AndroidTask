package com.example.androidtask.Database.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.androidtask.Database.Model.Data;

import java.util.List;

@Dao
public interface DataDaos {

    @Insert
    void addData(Data data);

    @Delete
    void deleteData(Data data);

    @Update
    void updateData(Data data);


    @Query("select * from data")
    List<Data> getAllData();

    @Query("DELETE FROM Data")
    void deleteTable();

}

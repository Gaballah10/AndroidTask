package com.example.androidtask.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.androidtask.Database.Daos.DataDaos;
import com.example.androidtask.Database.Model.Data;

@Database(entities = {Data.class},version = 1,exportSchema = false)
public abstract class MyDataBase extends RoomDatabase {
    private static MyDataBase myDataBase ;
    public abstract DataDaos sourcesDao ();

    public static MyDataBase getInstance(){
        if(myDataBase==null)
            throw new NullPointerException("database is null try to call init first");
        return myDataBase;
    }
    public static void init(Context context){
        myDataBase = Room.databaseBuilder(context,MyDataBase.class,
                "newsDataBase")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }
}

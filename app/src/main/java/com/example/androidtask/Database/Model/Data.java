package com.example.androidtask.Database.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Data {

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo
    String quarter;
    String  volumeOfConsumption;
    Boolean hadDownquarter;

    public Data() {
    }

    public Data(String quarter, String volumeOfConsumption, Boolean hadDownquarter) {
        this.quarter = quarter;
        this.volumeOfConsumption = volumeOfConsumption;
        this.hadDownquarter = hadDownquarter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getVolumeOfConsumption() {
        return volumeOfConsumption;
    }

    public void setVolumeOfConsumption(String volumeOfConsumption) {
        this.volumeOfConsumption = volumeOfConsumption;
    }

    public Boolean getHadDownquarter() {
        return hadDownquarter;
    }

    public void setHadDownquarter(Boolean hadDownquarter) {
        this.hadDownquarter = hadDownquarter;
    }
}

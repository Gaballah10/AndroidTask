package com.example.androidtask;

import android.app.Application;

import com.example.androidtask.Database.MyDataBase;


public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MyDataBase.init(this);
    }
}
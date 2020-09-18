package com.example.androidtask.api;


import com.example.androidtask.utils.ConfigSettings;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    private static Retrofit retrofit;

    private static Retrofit getInstance(){
        if(retrofit==null)
        {

            retrofit = new Retrofit.Builder()
                    .baseUrl(ConfigSettings.Server_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

        }
        return retrofit;
    }

    public static WebServices getApis(){
        return getInstance().create(WebServices.class);
    }


}

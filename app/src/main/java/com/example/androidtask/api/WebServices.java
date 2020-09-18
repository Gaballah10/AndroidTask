package com.example.androidtask.api;

import com.example.androidtask.model.SourceData;
import com.example.androidtask.utils.ConfigSettings;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface WebServices {

    @GET(ConfigSettings.ConsumptionData)
    Single<SourceData> getConsumptionData();

}

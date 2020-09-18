package com.example.androidtask.ui.home;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidtask.Database.MyDataBase;
import com.example.androidtask.api.ApiManager;
import com.example.androidtask.model.Record;
import com.example.androidtask.model.SourceData;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {

    public static List<Record> consumptionResultYear = new ArrayList<>();
    public static List<String> downYears = new ArrayList<>();
    MutableLiveData<SourceData> consumptionResultAll = new MutableLiveData<>();

    MutableLiveData<List<String>> muDownYear = new MutableLiveData<>();

    MutableLiveData<String> message = new MutableLiveData<>();

    public void getData() {
        ApiManager.getApis()
                .getConsumptionData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<SourceData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(SourceData sourceData) {
                        consumptionResultAll.setValue(sourceData);

                        for (int i = 0; i < sourceData.getResult().getRecords().size() - 1; i++) {
                            if (sourceData.getResult().getRecords().get(i).getQuarter().contains("Q1")) {
                                consumptionResultYear.add(sourceData.getResult().getRecords().get(i));
                            }
                        }

                        // Log.d("ddddsssss",String.valueOf(consumptionResultYear.size()));
                        // muResultYear.setValue(consumptionResultYear);

                        for (int i = 0; i < consumptionResultAll.getValue().getResult().getRecords().size() - 2; i++) {
                            if (Double.parseDouble(consumptionResultAll.getValue().getResult().getRecords().get(i).getVolumeOfMobileData()) > Double.parseDouble(consumptionResultAll.getValue().getResult().getRecords().get(i + 1).getVolumeOfMobileData())) {
                                String dateKey = consumptionResultAll.getValue().getResult().getRecords().get(i).getQuarter().substring(0, 4);
                                downYears.add(dateKey);
                            }
                        }
                        muDownYear.setValue(downYears);

                    }

                    @Override
                    public void onError(Throwable e) {
                        message.setValue(e.getMessage());
                    }
                });
    }


    // Check internet Connection
    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

}

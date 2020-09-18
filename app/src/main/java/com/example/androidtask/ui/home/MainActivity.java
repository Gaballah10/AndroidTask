package com.example.androidtask.ui.home;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.androidtask.Database.Model.Data;
import com.example.androidtask.Database.MyDataBase;
import com.example.androidtask.MyBaseActivity;
import com.example.androidtask.R;
import com.example.androidtask.databinding.ActivityMainBinding;
import com.example.androidtask.model.SourceData;
import com.example.androidtask.utils.DataAdapter;
import com.example.androidtask.utils.RoomDataAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.example.androidtask.ui.home.HomeViewModel.consumptionResultYear;

public class MainActivity extends MyBaseActivity<ActivityMainBinding,
        HomeViewModel> {

    protected RecyclerView recyclerView;
    protected RecyclerView.LayoutManager layoutManager;
    protected RoomDataAdapter roomDataAdapter;
    public static List<String> yearDownward = new ArrayList<>();
    public List<Data> RoomData = new ArrayList<>();
    DataAdapter adapter;

    @Override
    public HomeViewModel getViewModel() {
        return ViewModelProviders.of(this).get(HomeViewModel.class);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding.setVm(viewModel);
        initView();
        initRecyclerView();
        // getRoomDatabase();
        getRoomDatabase();

        if (getViewModel().isNetworkAvailable(this)) {

            getViewModel().getData();
            Log.d("InternetStatus", String.valueOf(getViewModel().isNetworkAvailable(this)));

            getViewModel().muDownYear.observe(this, new Observer<List<String>>() {
                @Override
                public void onChanged(List<String> strings) {
                    Log.d("DataVal", String.valueOf(strings.size()));
                    for (int i = 0; i < strings.size() ; i++) {
                        yearDownward.add(strings.get(i));
                        Log.d("DownyearsSize",String.valueOf(strings.get(i)));
                    }
                }
            });


            getViewModel().consumptionResultAll.observe(this, new Observer<SourceData>() {
                @Override
                public void onChanged(SourceData sourceData) {
                    adapter.changeData(consumptionResultYear);

                   Log.d("DownyearsSize",String.valueOf(yearDownward.size()));
                    MyDataBase.getInstance().sourcesDao().deleteTable();

                    for (int i = 0; i < sourceData.getResult().getRecords().size() - 1; i++) {
                        if (sourceData.getResult().getRecords().get(i).getQuarter().contains("Q1")) {
                            Data data = new Data();
                            data.setQuarter(sourceData.getResult().getRecords().get(i).getQuarter());
                            data.setVolumeOfConsumption(sourceData.getResult().getRecords().get(i).getVolumeOfMobileData());
                            data.setHadDownquarter(false);
                            for (int j = 0; j < yearDownward.size()-1; j++) {
                                if (sourceData.getResult().getRecords().get(i).getQuarter().contains(yearDownward.get(j))) {
                                    data.setHadDownquarter(true);
                                    Toast.makeText(MainActivity.this, "True is Added", Toast.LENGTH_SHORT).show();
                                }
                            }
                            MyDataBase.getInstance().sourcesDao().addData(data);
                        }
                    }
                }
            });




        } else {
            Toast.makeText(this, "Please Check Your Network :(", Toast.LENGTH_SHORT).show();
            //Get Data From Room Database
            Log.d("InternetStatus", String.valueOf(getViewModel().isNetworkAvailable(this)));
            displayRoomData();

        }
    }

    private void displayRoomData() {
        recyclerView.setAdapter(roomDataAdapter);
        roomDataAdapter.changeData(RoomData);
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    private void getRoomDatabase() {

        Log.d("RoomSize", String.valueOf(MyDataBase.getInstance().sourcesDao().getAllData().size()));
        for (int i = 0; i < MyDataBase.getInstance().sourcesDao().getAllData().size(); i++) {
            RoomData.add(MyDataBase.getInstance().sourcesDao().getAllData().get(i));
            Log.d("ValueFromRoom", String.valueOf(RoomData.get(i).getHadDownquarter()));
        }

    }

    private void initRecyclerView() {
        roomDataAdapter = new RoomDataAdapter(null, MainActivity.this);
        adapter = new DataAdapter(null, MainActivity.this);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

}

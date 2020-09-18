package com.example.androidtask.year.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.androidtask.MyBaseActivity;
import com.example.androidtask.R;
import com.example.androidtask.databinding.ActivityDetailsBinding;
import com.example.androidtask.databinding.ActivityMainBinding;
import com.example.androidtask.ui.home.HomeViewModel;

public class DetailsActivity extends MyBaseActivity<ActivityDetailsBinding,
        DetailsViewModel> {

    ImageView imageView;
    String theYear;

    @Override
    public DetailsViewModel getViewModel() {
        return ViewModelProviders.of(this).get(DetailsViewModel.class);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_details;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding.setVm(viewModel);
        initView();
        getIntentExtera();

    }

    private void getIntentExtera() {

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            theYear= null;
        } else {
            theYear= extras.getString("The_Year");
          getViewModel().yearDate.set(theYear);
        }
    }

    private void initView() {
        imageView = findViewById(R.id.imageView);
    }
}

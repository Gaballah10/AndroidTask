package com.example.androidtask;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;

public abstract class MyBaseActivity<T extends ViewDataBinding, V extends ViewModel>
        extends AppCompatActivity {

    protected T viewDataBinding;
    protected V viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewDataBinding();
        viewModel = getViewModel();
    }

    private void initViewDataBinding() {
        viewDataBinding =
                DataBindingUtil.setContentView(this, getLayoutID());

    }

    public abstract V getViewModel();

    public abstract int getLayoutID();

}
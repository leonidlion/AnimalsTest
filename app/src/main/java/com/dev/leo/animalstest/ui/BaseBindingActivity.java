package com.dev.leo.animalstest.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.dev.leo.animaltest.presentation.ViewModelFactory;

public abstract class BaseBindingActivity<B extends ViewDataBinding, VM extends ViewModel> extends AppCompatActivity {
    protected B binding;
    protected VM viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, getLayoutRes());

        viewModel = ViewModelProviders.of(this, getViewModelFactory()).get(getViewModelClass());
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract Class<VM> getViewModelClass();

    @Nullable
    protected abstract ViewModelFactory getViewModelFactory();

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(@StringRes int message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

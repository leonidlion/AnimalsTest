package com.dev.leo.animalstest.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.dev.leo.animaltest.presentation.ViewModelFactory;
import com.dev.leo.animaltest.presentation.viewmodel.MainViewModel;

import dagger.Binds;
import dagger.MapKey;
import dagger.Module;
import dagger.multibindings.IntoMap;

@MapKey
@interface ViewModelKey{
    Class<? extends ViewModel> value();
}

@Module
public abstract class PresentationModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainViewModel(MainViewModel viewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}

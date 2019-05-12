package com.dev.leo.animalstest.di.module;

import com.dev.leo.animalstest.MainActivity;
import com.dev.leo.animalstest.UiThread;
import com.dev.leo.animalstest.ui.main.AnimalFragment;
import com.dev.leo.animaltest.domain.executor.PostExecutionThread;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class UiModule {
    @Binds
    abstract PostExecutionThread bindPostExecutionThread(UiThread uiThread);

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector
    abstract AnimalFragment contributeAnimalFragment();
}

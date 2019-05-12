package com.dev.leo.animalstest.di.module;


import com.dev.leo.animaltest.data.api.AnimalApi;
import com.dev.leo.animaltest.data.api.AnimalApiFactory;
import com.dev.leo.animaltest.data.api.AnimalApiRepositoryImpl;
import com.dev.leo.animaltest.data.repository.AnimalApiRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class RemoteModule {

    @Provides
    static AnimalApi provideAnimalService(){
        return new AnimalApiFactory().makeAnimalService(false);
    }

    @Binds
    abstract AnimalApiRepository bindRemoteRepository(AnimalApiRepositoryImpl remoteImpl);
}

package com.dev.leo.animalstest.di.module;

import com.dev.leo.animaltest.data.AnimalDataRepository;
import com.dev.leo.animaltest.data.executor.JobExecutor;
import com.dev.leo.animaltest.domain.executor.ThreadExecutor;
import com.dev.leo.animaltest.domain.repository.AnimalRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class DataModule {
    @Binds
    abstract AnimalRepository bindAnimalRepository(AnimalDataRepository dataRepository);

    @Binds
    abstract ThreadExecutor bindThreadExecutor(JobExecutor jobExecutor);
}

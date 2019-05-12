package com.dev.leo.animaltest.domain.usecase.animal;


import com.dev.leo.animaltest.domain.entity.Animal;
import com.dev.leo.animaltest.domain.executor.PostExecutionThread;
import com.dev.leo.animaltest.domain.executor.ThreadExecutor;
import com.dev.leo.animaltest.domain.repository.AnimalRepository;
import com.dev.leo.animaltest.domain.usecase.FlowableUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.annotations.Nullable;

public class AnimalUseCase extends FlowableUseCase<List<Animal>, String> {
    private AnimalRepository repository;

    @Inject
    public AnimalUseCase(AnimalRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Flowable<List<Animal>> buildUserCase(@Nullable String params) {
        return repository.getAnimals(params);
    }
}

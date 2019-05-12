package com.dev.leo.animaltest.data.api;

import com.dev.leo.animaltest.data.mapper.AnimalApiMapper;
import com.dev.leo.animaltest.data.api.model.AnimalDto;
import com.dev.leo.animaltest.data.api.model.ResponseWrapper;
import com.dev.leo.animaltest.data.entity.AnimalEntity;
import com.dev.leo.animaltest.data.repository.AnimalApiRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class AnimalApiRepositoryImpl implements AnimalApiRepository {
    private AnimalApi animalApi;
    private AnimalApiMapper mapper;

    @Inject
    public AnimalApiRepositoryImpl(AnimalApi animalApi, AnimalApiMapper mapper) {
        this.animalApi = animalApi;
        this.mapper = mapper;
    }

    @Override
    public Flowable<List<AnimalEntity>> getAnimals(String query) {
        return animalApi.getAnimals(query)
                .map(ResponseWrapper::getData)
                .map(animalDtos -> {
                   List<AnimalEntity> animalEntities = new ArrayList<>();
                   for (AnimalDto x : animalDtos)
                       animalEntities.add(mapper.mapToEntity(x));
                   return animalEntities;
                });
    }
}

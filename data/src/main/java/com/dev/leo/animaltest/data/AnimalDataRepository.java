package com.dev.leo.animaltest.data;

import com.dev.leo.animaltest.data.entity.AnimalEntity;
import com.dev.leo.animaltest.data.mapper.AnimalDomainMapper;
import com.dev.leo.animaltest.data.repository.AnimalApiRepository;
import com.dev.leo.animaltest.domain.entity.Animal;
import com.dev.leo.animaltest.domain.repository.AnimalRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class AnimalDataRepository implements AnimalRepository {
    private AnimalApiRepository animalApiRepository;
    private AnimalDomainMapper mapper;

    @Inject
    public AnimalDataRepository(AnimalApiRepository animalApiRepository, AnimalDomainMapper mapper) {
        this.animalApiRepository = animalApiRepository;
        this.mapper = mapper;
    }

    @Override
    public Flowable<List<Animal>> getAnimals(String query) {
        return animalApiRepository.getAnimals(query)
                .flatMap(animalEntities -> {
                    List<Animal> animalList = new ArrayList<>();
                    for (AnimalEntity x : animalEntities) animalList.add(mapper.mapFromEntity(x));
                    return Flowable.just(animalList);
                });

    }
}

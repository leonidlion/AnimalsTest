package com.dev.leo.animaltest.data.mapper;

import com.dev.leo.animaltest.data.entity.AnimalEntity;
import com.dev.leo.animaltest.domain.entity.Animal;

import javax.inject.Inject;

public class AnimalDomainMapper implements Mapper<AnimalEntity, Animal> {
    @Inject
    public AnimalDomainMapper(){ }

    @Override
    public Animal mapFromEntity(AnimalEntity data) {
        return new Animal(data.getUrl(), data.getTitle());
    }

    @Override
    public AnimalEntity mapToEntity(Animal data) {
        return new AnimalEntity(data.getUrl(), data.getTitle());
    }
}

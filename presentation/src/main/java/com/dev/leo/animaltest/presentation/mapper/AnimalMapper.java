package com.dev.leo.animaltest.presentation.mapper;

import com.dev.leo.animaltest.domain.entity.Animal;
import com.dev.leo.animaltest.presentation.model.AnimalPresentation;

import javax.inject.Inject;

public class AnimalMapper implements Mapper<AnimalPresentation, Animal> {
    @Inject
    public AnimalMapper(){}

    @Override
    public AnimalPresentation mapToView(Animal data) {
        return new AnimalPresentation(data.getUrl(), data.getTitle());
    }
}

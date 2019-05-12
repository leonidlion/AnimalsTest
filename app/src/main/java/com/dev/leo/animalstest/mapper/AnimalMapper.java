package com.dev.leo.animalstest.mapper;

import com.dev.leo.animalstest.model.AnimalViewModel;
import com.dev.leo.animaltest.presentation.model.AnimalPresentation;

import javax.inject.Inject;

public class AnimalMapper implements Mapper<AnimalViewModel, AnimalPresentation> {
    @Inject
    public AnimalMapper(){

    }

    @Override
    public AnimalViewModel mapToViewModel(AnimalPresentation model) {
        return new AnimalViewModel(model.getUrl(), model.getTitle());
    }
}

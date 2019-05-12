package com.dev.leo.animaltest.data.mapper;

import com.dev.leo.animaltest.data.api.model.AnimalDto;
import com.dev.leo.animaltest.data.entity.AnimalEntity;

import javax.inject.Inject;

public class AnimalApiMapper implements Mapper<AnimalEntity, AnimalDto> {
    @Inject
    public AnimalApiMapper(){ }

    @Override
    public AnimalDto mapFromEntity(AnimalEntity data) {
        return new AnimalDto(data.getUrl(), data.getTitle());
    }

    @Override
    public AnimalEntity mapToEntity(AnimalDto data) {
        return new AnimalEntity(data.getUrl(), data.getTitle());
    }
}

package com.dev.leo.animaltest.data.repository;

import com.dev.leo.animaltest.data.entity.AnimalEntity;

import java.util.List;

import io.reactivex.Flowable;

public interface AnimalApiRepository {
    Flowable<List<AnimalEntity>> getAnimals(String query);
}

package com.dev.leo.animaltest.domain.repository;

import com.dev.leo.animaltest.domain.entity.Animal;

import java.util.List;

import io.reactivex.Flowable;

public interface AnimalRepository {
    Flowable<List<Animal>> getAnimals(String query);
}

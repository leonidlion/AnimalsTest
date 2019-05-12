package com.dev.leo.animaltest.data.mapper;

public interface Mapper<E, D> {
    D mapFromEntity(E data);
    E mapToEntity(D data);
}

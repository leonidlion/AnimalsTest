package com.dev.leo.animaltest.presentation.mapper;

interface Mapper<V, D> {
    V mapToView(D data);
}

package com.dev.leo.animalstest.mapper;

public interface Mapper<V, D> {
    V mapToViewModel(D model);
}

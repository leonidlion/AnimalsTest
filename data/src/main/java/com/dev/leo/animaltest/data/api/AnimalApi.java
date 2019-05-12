package com.dev.leo.animaltest.data.api;

import com.dev.leo.animaltest.data.api.model.AnimalDto;
import com.dev.leo.animaltest.data.api.model.ResponseWrapper;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AnimalApi {
    @GET("api.php")
    Flowable<ResponseWrapper<AnimalDto>> getAnimals(@Query("query") String query);
}

package com.dev.leo.animaltest.data.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnimalApiFactory {
    private static final int CONNECTION_TIME_OUT = 120;
    private static final int READ_TIME_OUT = 120;
    private static final String BASE_URL = "http://kot3.com/xim/";

    public AnimalApi makeAnimalService(boolean isDebug){
        return makeAnimalService(
                makeOkHttpClient(makeLoggingInterceptor(isDebug)),
                makeGson());
    }

    private AnimalApi makeAnimalService(OkHttpClient client, Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(AnimalApi.class);
    }

    private OkHttpClient makeOkHttpClient(HttpLoggingInterceptor interceptor){
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    private Gson makeGson(){
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    private HttpLoggingInterceptor makeLoggingInterceptor(boolean isDebug){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(isDebug ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return loggingInterceptor;
    }
}

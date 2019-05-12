package com.dev.leo.animaltest.presentation.data;

public class Resource<T> {
    private ResourceState status;
    private T data;
    private String message;

    public Resource(ResourceState status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public ResourceState getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public Resource<T> success(T data){
        return new Resource<>(ResourceState.SUCCESS, data, null);
    }

    public Resource<T> error(String message, T data){
        return new Resource<>(ResourceState.ERROR, null, message);
    }

    public Resource<T> loading(){
        return new Resource<>(ResourceState.LOADING, null, null);
    }
}

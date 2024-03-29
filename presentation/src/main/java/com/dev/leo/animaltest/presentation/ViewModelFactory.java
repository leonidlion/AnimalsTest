package com.dev.leo.animaltest.presentation;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
public final class ViewModelFactory implements ViewModelProvider.Factory {
    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> creators;

    @Inject
    public ViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> creators) {
        this.creators = creators;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        Provider creator = creators.get(modelClass);
        if (creator == null){
            for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> x : creators.entrySet()) {
                if (modelClass.isAssignableFrom(x.getKey())) {
                    creator = x.getValue();
                    break;
                }
            }
        }

        if (creator == null){
            throw new IllegalArgumentException("unknown model class " + modelClass);
        }
        try{
            return (T) creator.get();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}

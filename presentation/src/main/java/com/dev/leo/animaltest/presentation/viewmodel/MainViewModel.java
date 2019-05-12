package com.dev.leo.animaltest.presentation.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.dev.leo.animaltest.domain.entity.Animal;
import com.dev.leo.animaltest.domain.usecase.animal.AnimalUseCase;
import com.dev.leo.animaltest.presentation.data.Resource;
import com.dev.leo.animaltest.presentation.data.ResourceState;
import com.dev.leo.animaltest.presentation.mapper.AnimalMapper;
import com.dev.leo.animaltest.presentation.model.AnimalPresentation;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.subscribers.DisposableSubscriber;

public class MainViewModel extends ViewModel {
    private static final String CAT = "cat";
    private static final String DOG = "dog";

    private AnimalUseCase animalUseCase;
    private AnimalMapper mapper;
    private MutableLiveData<Resource<List<AnimalPresentation>>> catLiveData = new MutableLiveData<>();
    private MutableLiveData<Resource<List<AnimalPresentation>>> dogLiveData = new MutableLiveData<>();

    @Inject
    public MainViewModel(AnimalUseCase animalUseCase, AnimalMapper mapper) {
        this.animalUseCase = animalUseCase;
        this.mapper = mapper;
    }

    public MutableLiveData<Resource<List<AnimalPresentation>>> getCatLiveData() {
        return catLiveData;
    }

    public MutableLiveData<Resource<List<AnimalPresentation>>> getDogLiveData() {
        return dogLiveData;
    }

    public void loadCats(){
        catLiveData.postValue(new Resource<List<AnimalPresentation>>(ResourceState.LOADING, null, null));
        animalUseCase.execute(new AnimalSubscriber(catLiveData), CAT);
    }

    public void loadDogs(){
        dogLiveData.postValue(new Resource<List<AnimalPresentation>>(ResourceState.LOADING, null, null));
        animalUseCase.execute(new AnimalSubscriber(dogLiveData), DOG);
    }

    @Override
    protected void onCleared() {
        animalUseCase.dispose();
        super.onCleared();
    }

    class AnimalSubscriber extends DisposableSubscriber<List<Animal>>{
        private MutableLiveData<Resource<List<AnimalPresentation>>> liveData;

        AnimalSubscriber(MutableLiveData<Resource<List<AnimalPresentation>>> liveData){
            this.liveData = liveData;
        }

        @Override
        public void onNext(List<Animal> data) {
            List<AnimalPresentation> presentations = new ArrayList<>();
            for (Animal x : data) presentations.add(mapper.mapToView(x));
            liveData.postValue(new Resource<>(ResourceState.SUCCESS, presentations, null));
        }

        @Override
        public void onError(Throwable t) {
            liveData.postValue(new Resource<List<AnimalPresentation>>(ResourceState.ERROR, null, t.getMessage()));
        }

        @Override
        public void onComplete() {

        }
    }
}

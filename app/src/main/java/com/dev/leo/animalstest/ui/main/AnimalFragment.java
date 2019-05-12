package com.dev.leo.animalstest.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.dev.leo.animalstest.Constants;
import com.dev.leo.animalstest.R;
import com.dev.leo.animalstest.databinding.FragmentAnimalBinding;
import com.dev.leo.animalstest.mapper.AnimalMapper;
import com.dev.leo.animalstest.model.AnimalViewModel;
import com.dev.leo.animalstest.ui.BaseBindingFragment;
import com.dev.leo.animalstest.utils.IntentUtils;
import com.dev.leo.animaltest.presentation.data.Resource;
import com.dev.leo.animaltest.presentation.model.AnimalPresentation;
import com.dev.leo.animaltest.presentation.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;


public class AnimalFragment extends BaseBindingFragment<FragmentAnimalBinding> {
    private static final String ARG_ANIMAL_TYPE = "ARG_ANIMAL_TYPE";
    private static final String STATE_POSITION = "STATE_POSITION";

    private AnimalAdapter adapter;

    private MainViewModel viewModel;

    private int animalType;

    @Inject
    AnimalMapper mapper;

    public static AnimalFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt(ARG_ANIMAL_TYPE, type);
        AnimalFragment fragment = new AnimalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new AnimalAdapter();
        adapter.setClickListener(animalViewModel -> startActivity(IntentUtils.startAnimalDetailActivity(getActivity(), animalViewModel)));

        binding.setAdapter(adapter);

        animalType = getArguments().getInt(ARG_ANIMAL_TYPE, Constants.CAT_LIST);

        if (savedInstanceState != null)
            binding.animalRecycler.getLayoutManager().onRestoreInstanceState(savedInstanceState.getParcelable(STATE_POSITION));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

        loadData(animalType);
    }


    private void loadData(int code){
        switch (code){
            case Constants.CAT_LIST:
                loadCats();
                break;
            case Constants.DOG_LIST:
                loadDogs();
                break;
        }
    }

    private void loadCats(){
        viewModel.loadCats();
        viewModel.getCatLiveData().observe(this, getAnimalObserver());
    }

    private void loadDogs(){
        viewModel.loadDogs();
        viewModel.getDogLiveData().observe(this, getAnimalObserver());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(STATE_POSITION, binding.animalRecycler.getLayoutManager().onSaveInstanceState());
        super.onSaveInstanceState(outState);
    }

    private Observer<Resource<List<AnimalPresentation>>> getAnimalObserver(){
        return listResource -> {
            if (listResource == null) return;
            switch (listResource.getStatus()){
                case ERROR:
                    Toast.makeText(getContext(), listResource.getMessage(), Toast.LENGTH_SHORT).show();
                    break;
                case LOADING:

                    break;
                case SUCCESS:
                    List<AnimalViewModel> data = new ArrayList<>();
                    for (AnimalPresentation x : listResource.getData())
                        data.add(mapper.mapToViewModel(x));
                    adapter.addItem(data);
                    break;
            }
        };
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_animal;
    }
}

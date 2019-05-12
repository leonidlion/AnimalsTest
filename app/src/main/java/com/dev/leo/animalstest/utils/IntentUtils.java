package com.dev.leo.animalstest.utils;

import android.content.Context;
import android.content.Intent;

import com.dev.leo.animalstest.BundleKeys;
import com.dev.leo.animalstest.model.AnimalViewModel;
import com.dev.leo.animalstest.ui.detail.DetailActivity;

public class IntentUtils {
    public static Intent startAnimalDetailActivity(Context context, AnimalViewModel animalViewModel){
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(BundleKeys.ANIMAL_DATA.name(), animalViewModel);
        return intent;
    }
}

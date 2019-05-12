package com.dev.leo.animalstest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.dev.leo.animalstest.databinding.ActivityMainBinding;
import com.dev.leo.animalstest.ui.BaseBindingActivity;
import com.dev.leo.animalstest.ui.main.AnimalFragment;
import com.dev.leo.animaltest.presentation.ViewModelFactory;
import com.dev.leo.animaltest.presentation.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends BaseBindingActivity<ActivityMainBinding, MainViewModel> {
    private static final String TAG = "MainActivity";
    private static final String STATE_LAST_POSITION = "STATE_LAST_POSITION";

    @Inject ViewModelFactory viewModelFactory;

    private List<Fragment> fragments = new ArrayList<>();
    private int lastPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) lastPosition = savedInstanceState.getInt(STATE_LAST_POSITION);

        Log.d(TAG, "onCreate: " + lastPosition + " SIZE: " + getSupportFragmentManager().getFragments().size());

        initFragments();

//        if (getSupportFragmentManager().getFragments().size() == 0)
//            getSupportFragmentManager().beginTransaction().add(R.id.mainFrameLayout, fragments.get(Constants.CAT_LIST), String.valueOf(Constants.CAT_LIST)).commit();
        displayFragment(lastPosition);

        binding.tabLayout.getTabAt(lastPosition).select();

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                displayFragment(tab.getPosition());

                lastPosition = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void initFragments(){
        if (getSupportFragmentManager().findFragmentByTag(String.valueOf(Constants.CAT_LIST)) != null)
            fragments.add(getSupportFragmentManager().findFragmentByTag(String.valueOf(Constants.CAT_LIST)));
        else fragments.add(AnimalFragment.newInstance(Constants.CAT_LIST));

        if (getSupportFragmentManager().findFragmentByTag(String.valueOf(Constants.DOG_LIST)) != null)
            fragments.add(getSupportFragmentManager().findFragmentByTag(String.valueOf(Constants.DOG_LIST)));
        else fragments.add(AnimalFragment.newInstance(Constants.DOG_LIST));
    }

    protected void displayFragment(int currentPosition) {
        Log.d(TAG, "displayFragment: " + currentPosition);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        Fragment fragment = fragments.get(currentPosition);
        Fragment currentVisibleFragment = fragments.get(lastPosition);

        if (fragment.isAdded()) {
            Log.d(TAG, "SHOW: " + currentPosition);
            ft.show(fragment);
        }
        else {
            Log.d(TAG, "ADD: " + currentPosition);
            ft.add(R.id.mainFrameLayout, fragment, String.valueOf(currentPosition));
        }
        if ((currentPosition != lastPosition) && currentVisibleFragment.isAdded()) {
            Log.d(TAG, "HIDE: " + lastPosition);
            ft.hide(currentVisibleFragment);
        }

        ft.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_LAST_POSITION, lastPosition);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected Class<MainViewModel> getViewModelClass() {
        return MainViewModel.class;
    }

    @Nullable
    @Override
    protected ViewModelFactory getViewModelFactory() {
        return viewModelFactory;
    }
}

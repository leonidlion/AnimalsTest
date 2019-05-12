package com.dev.leo.animalstest.ui.detail;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dev.leo.animalstest.BundleKeys;
import com.dev.leo.animalstest.R;
import com.dev.leo.animalstest.databinding.ActivityDetailBinding;
import com.dev.leo.animalstest.model.AnimalViewModel;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent() == null) finish();

        ActivityDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        binding.setAnimal((AnimalViewModel) getIntent().getSerializableExtra(BundleKeys.ANIMAL_DATA.name()));
    }

    @BindingAdapter("app:imageUrl")
    public static void setupImage(ImageView imageView, String url){
        Glide.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }
}

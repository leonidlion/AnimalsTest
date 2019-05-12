package com.dev.leo.animalstest.ui.main;

import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dev.leo.animalstest.R;
import com.dev.leo.animalstest.databinding.ItemAnimalBinding;
import com.dev.leo.animalstest.model.AnimalDiffUtil;
import com.dev.leo.animalstest.model.AnimalViewModel;
import com.dev.leo.animalstest.ui.BaseRecyclerAdapter;
import com.dev.leo.animalstest.ui.BaseViewHolder;

import java.util.List;

public class AnimalAdapter extends BaseRecyclerAdapter<AnimalAdapter.AnimalHolder, AnimalViewModel> {
    private ClickListener clickListener;

    public interface ClickListener{
        void onItemClick(AnimalViewModel animalViewModel);
    }

    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }

    @Override
    public void addItem(List<AnimalViewModel> itemList) {
        diffUtilCallback = new AnimalDiffUtil(itemList, adapterData);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(diffUtilCallback);

        adapterData.clear();
        adapterData.addAll(itemList);
        result.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public AnimalHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new AnimalHolder(
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_animal, viewGroup, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalHolder animalHolder, int i) {
        animalHolder.onBind(getItem(animalHolder));
    }

    @BindingAdapter("app:imageUrl")
    public static void bindImage(ImageView imageView, String url){
        Glide.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }

    class AnimalHolder extends BaseViewHolder<ItemAnimalBinding, AnimalViewModel>{

        AnimalHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(AnimalViewModel data) {
            if (clickListener != null) binding.setClickListener(clickListener);
            binding.setPosition(String.valueOf(getAdapterPosition()));
            binding.setAnimal(data);
            binding.executePendingBindings();
        }
    }
}

package com.dev.leo.animalstest.model;

import java.util.List;

public class AnimalDiffUtil extends BaseDiffUtilCallback<AnimalViewModel> {

    public AnimalDiffUtil(List<AnimalViewModel> newList, List<AnimalViewModel> oldList) {
        super(newList, oldList);
    }

    @Override
    public boolean areItemsTheSame(int i, int i1) {
        return oldList.get(i).getTitle().equalsIgnoreCase(newList.get(i1).getTitle());
    }

    @Override
    public boolean areContentsTheSame(int i, int i1) {
        return oldList.get(i).equals(newList.get(i1));
    }
}

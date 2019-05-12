package com.dev.leo.animalstest.model;

import android.support.v7.util.DiffUtil;

import java.util.List;

public abstract class BaseDiffUtilCallback<T> extends DiffUtil.Callback {
    protected List<T> newList;
    protected List<T> oldList;

    public BaseDiffUtilCallback(List<T> newList, List<T> oldList) {
        this.newList = newList;
        this.oldList = oldList;
    }

    @Override
    public int getOldListSize() {
        return oldList == null ? 0 : oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList == null ? 0 : newList.size();
    }
}

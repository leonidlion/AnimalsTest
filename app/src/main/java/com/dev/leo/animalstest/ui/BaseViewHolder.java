package com.dev.leo.animalstest.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseViewHolder<B extends ViewDataBinding, T> extends RecyclerView.ViewHolder {
    protected B binding;

    public BaseViewHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    protected abstract void onBind(T data);
}

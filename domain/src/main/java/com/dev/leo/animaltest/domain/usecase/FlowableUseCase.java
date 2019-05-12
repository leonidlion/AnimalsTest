package com.dev.leo.animaltest.domain.usecase;


import com.dev.leo.animaltest.domain.executor.PostExecutionThread;
import com.dev.leo.animaltest.domain.executor.ThreadExecutor;

import io.reactivex.Flowable;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

abstract public class FlowableUseCase<T, P> {
    private ThreadExecutor threadExecutor;
    private PostExecutionThread postExecutionThread;

    private final CompositeDisposable disposable = new CompositeDisposable();

    public FlowableUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    protected abstract Flowable<T> buildUserCase(@Nullable P params);

    public void execute(DisposableSubscriber<T> observer, @Nullable P params){
        Flowable<T> observable = this.buildUserCase(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
        addDisposable(observable.subscribeWith(observer));
    }

    public void dispose(){
        if (!disposable.isDisposed()) disposable.dispose();
    }

    private void addDisposable(Disposable disposable){
        this.disposable.add(disposable);
    }
}

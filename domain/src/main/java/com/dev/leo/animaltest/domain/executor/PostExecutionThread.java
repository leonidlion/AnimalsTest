package com.dev.leo.animaltest.domain.executor;

import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;

public interface PostExecutionThread {
    @NonNull
    Scheduler getScheduler();
}

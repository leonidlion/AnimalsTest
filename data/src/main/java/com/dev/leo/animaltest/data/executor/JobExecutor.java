package com.dev.leo.animaltest.data.executor;

import com.dev.leo.animaltest.domain.executor.ThreadExecutor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

public class JobExecutor implements ThreadExecutor {
    private final int INITIAL_POOL_SIZE = 3;
    private final int MAX_POOL_SIZE = 5;
    private final long KEEP_ALIVE_TIME = 10;
    private final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;

    private LinkedBlockingQueue<Runnable> workQueue;
    private ThreadPoolExecutor threadPoolExecutor;
    private ThreadFactory threadFactory;

    @Inject
    public JobExecutor(){
        this.workQueue = new LinkedBlockingQueue<>();
        this.threadFactory = new JobThreadFactory();
        this.threadPoolExecutor = new ThreadPoolExecutor(INITIAL_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, workQueue, threadFactory);

    }

    @Override
    public void execute(Runnable command) {
        if (command == null) throw new IllegalArgumentException("Runnable cannot be null");
        this.threadPoolExecutor.execute(command);
    }

    private class JobThreadFactory implements ThreadFactory{
        private static final String THREAD_NAME = "animal_thread_";
        private int counter = 0;

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, THREAD_NAME + counter++);
        }
    }
}

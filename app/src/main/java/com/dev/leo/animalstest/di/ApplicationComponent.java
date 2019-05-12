package com.dev.leo.animalstest.di;

import android.app.Application;

import com.dev.leo.animalstest.MainApplication;
import com.dev.leo.animalstest.di.module.ApplicationModule;
import com.dev.leo.animalstest.di.module.DataModule;
import com.dev.leo.animalstest.di.module.PresentationModule;
import com.dev.leo.animalstest.di.module.RemoteModule;
import com.dev.leo.animalstest.di.module.UiModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        AndroidSupportInjectionModule.class,
        DataModule.class,
        PresentationModule.class,
        RemoteModule.class,
        UiModule.class
})
public interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }

    void inject(MainApplication application);
}

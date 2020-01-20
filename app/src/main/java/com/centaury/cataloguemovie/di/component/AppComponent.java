package com.centaury.cataloguemovie.di.component;

import android.app.Application;

import com.centaury.cataloguemovie.MovieCatalogueApp;
import com.centaury.cataloguemovie.di.builder.ActivityModule;
import com.centaury.cataloguemovie.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Centaury on 1/17/2020.
 */
@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                AppModule.class,
                ActivityModule.class
        }
)
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(MovieCatalogueApp application);
}

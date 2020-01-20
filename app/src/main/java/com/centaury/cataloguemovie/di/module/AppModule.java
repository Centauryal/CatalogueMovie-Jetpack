package com.centaury.cataloguemovie.di.module;

import android.app.Application;

import androidx.room.Room;

import com.centaury.cataloguemovie.data.local.LocalRepository;
import com.centaury.cataloguemovie.data.local.db.CatalogueDao;
import com.centaury.cataloguemovie.data.local.db.CatalogueDatabase;
import com.centaury.cataloguemovie.data.remote.RemoteRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Centaury on 1/17/2020.
 */
@Module(includes = ViewModelModule.class)
public class AppModule {

    @Singleton
    @Provides
    CatalogueDatabase catalogueDatabase(Application application) {
        return Room.databaseBuilder(application, CatalogueDatabase.class, "Catalogue.db").build();
    }

    @Singleton
    @Provides
    CatalogueDao provideCatalogueDao(CatalogueDatabase database) {
        return database.catalogueDao();
    }

    @Singleton
    @Provides
    LocalRepository provideLocal(CatalogueDao dao) {
        return new LocalRepository(dao);
    }

    @Singleton
    @Provides
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Singleton
    @Provides
    RemoteRepository provideRemote(Application application) {
        return new RemoteRepository(application);
    }
}

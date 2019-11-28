package com.centaury.cataloguemovie.data.local.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.centaury.cataloguemovie.data.local.entity.MovieEntity;
import com.centaury.cataloguemovie.data.local.entity.TVShowEntity;

/**
 * Created by Centaury on 11/20/2019.
 */
@Database(entities = {MovieEntity.class, TVShowEntity.class}, version = 1, exportSchema = false)
public abstract class CatalogueDatabase extends RoomDatabase {

    private static CatalogueDatabase INSTANCE;

    public abstract CatalogueDao catalogueDao();

    private static final Object sLock = new Object();

    public static CatalogueDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        CatalogueDatabase.class, "Catalogue.db")
                        .build();
            }
            return INSTANCE;
        }
    }
}

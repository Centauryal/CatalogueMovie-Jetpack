package com.centaury.cataloguemovie.di;

import android.app.Application;

import com.centaury.cataloguemovie.data.CatalogueRepository;
import com.centaury.cataloguemovie.data.local.LocalRepository;
import com.centaury.cataloguemovie.data.local.db.CatalogueDatabase;
import com.centaury.cataloguemovie.data.remote.RemoteRepository;
import com.centaury.cataloguemovie.utils.AppExecutors;

/**
 * Created by Centaury on 10/25/2019.
 */
public class Injection {

    public static CatalogueRepository catalogueRepository(Application application) {

        CatalogueDatabase catalogueDatabase = CatalogueDatabase.getInstance(application);

        LocalRepository localRepository = LocalRepository.getInstance(catalogueDatabase.catalogueDao());
        RemoteRepository remoteRepository = RemoteRepository.getInstance(application);
        AppExecutors appExecutors = new AppExecutors();

        return CatalogueRepository.getInstance(localRepository, remoteRepository, appExecutors);
    }
}

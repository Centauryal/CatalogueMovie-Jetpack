package com.centaury.cataloguemovie.utils;

import com.centaury.cataloguemovie.data.local.entity.MovieEntity;
import com.centaury.cataloguemovie.data.local.entity.TVShowEntity;
import com.centaury.cataloguemovie.data.remote.detail.movie.DetailMovieResponse;
import com.centaury.cataloguemovie.data.remote.detail.tvshow.DetailTVShowResponse;
import com.centaury.cataloguemovie.data.remote.genre.GenresItem;
import com.centaury.cataloguemovie.data.remote.movie.MovieResultsItem;
import com.centaury.cataloguemovie.data.remote.tvshow.TVShowResultsItem;

import java.util.ArrayList;

/**
 * Created by Centaury on 10/6/2019.
 */
public class FakeDataDummy {

    public static ArrayList<MovieEntity> generateDummyMovies() {

        ArrayList<MovieEntity> movieEntities = new ArrayList<>();

        movieEntities.add(new MovieEntity(475557,
                "Joker",
                "Joker",
                "https://image.tmdb.org/t/p/w500/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg",
                "https://image.tmdb.org/t/p/w500/n6bUvigpRFqSwmPp1m2YADdbRBc.jpg",
                "80, 53, 18",
                "2019-10-04",
                "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.",
                8.8));
        movieEntities.add(new MovieEntity(429617,
                "Spider-Man: Far from Home",
                "Spider-Man: Far from Home",
                "https://image.tmdb.org/t/p/w500/lcq8dVxeeOqHvvgcte707K0KVx5.jpg",
                "https://image.tmdb.org/t/p/w500/5myQbDzw3l8K9yofUXRJ4UTVgam.jpg",
                "28, 12, 878",
                "2019-07-02",
                "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
                7.7));

        return movieEntities;
    }

    public static ArrayList<MovieResultsItem> generateDummyResponseMovies() {

        ArrayList<MovieResultsItem> movieEntities = new ArrayList<>();

        movieEntities.add(new MovieResultsItem(475557,
                "Joker",
                "https://image.tmdb.org/t/p/w500/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg",
                "2019-10-04",
                "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.",
                8.8));
        movieEntities.add(new MovieResultsItem(429617,
                "Spider-Man: Far from Home",
                "https://image.tmdb.org/t/p/w500/lcq8dVxeeOqHvvgcte707K0KVx5.jpg",
                "2019-07-02",
                "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
                7.7));

        return movieEntities;
    }

    public static ArrayList<TVShowEntity> generateDummyTVShows() {

        ArrayList<TVShowEntity> tvShowEntities = new ArrayList<>();

        tvShowEntities.add(new TVShowEntity(1412,
                "Arrow",
                "Arrow",
                "https://image.tmdb.org/t/p/w500/mo0FP1GxOFZT4UDde7RFDz5APXF.jpg",
                "https://image.tmdb.org/t/p/w500/dXTyVDTIgeByvUOUEiHjbi8xX9A.jpg",
                "80, 18, 9648, 10759",
                "2012-10-10",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                5.8));
        tvShowEntities.add(new TVShowEntity(60735,
                "The Flash",
                "The Flash",
                "https://image.tmdb.org/t/p/w500/wHa6KOJAoNTFLFtp7wguUJKSnju.jpg",
                "https://image.tmdb.org/t/p/w500/6ZdQTBy20HzWudZthAV7NkZWfIb.jpg",
                "18, 10765",
                "2014-10-07",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                6.7));

        return tvShowEntities;
    }

    public static ArrayList<TVShowResultsItem> generateDummyResponseTVShows() {

        ArrayList<TVShowResultsItem> tvShowEntities = new ArrayList<>();

        tvShowEntities.add(new TVShowResultsItem(1412,
                "Arrow",
                "https://image.tmdb.org/t/p/w500/mo0FP1GxOFZT4UDde7RFDz5APXF.jpg",
                "2012-10-10",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                5.8));
        tvShowEntities.add(new TVShowResultsItem(60735,
                "The Flash",
                "https://image.tmdb.org/t/p/w500/wHa6KOJAoNTFLFtp7wguUJKSnju.jpg",
                "2014-10-07",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                6.7));

        return tvShowEntities;
    }

    public static ArrayList<DetailMovieResponse> generateDummyResponseDetailMovies() {

        ArrayList<DetailMovieResponse> detailMovieResponses = new ArrayList<>();

        detailMovieResponses.add(new DetailMovieResponse(475557,
                "Joker",
                "https://image.tmdb.org/t/p/w500/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg",
                "2019-10-04",
                "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.",
                8.8));
        detailMovieResponses.add(new DetailMovieResponse(429617,
                "Spider-Man: Far from Home",
                "https://image.tmdb.org/t/p/w500/lcq8dVxeeOqHvvgcte707K0KVx5.jpg",
                "2019-07-02",
                "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
                7.7));

        return detailMovieResponses;
    }

    public static ArrayList<DetailTVShowResponse> generateDummyResponseDetailTVShows() {

        ArrayList<DetailTVShowResponse> detailTVShowResponses = new ArrayList<>();

        detailTVShowResponses.add(new DetailTVShowResponse(1412,
                "Arrow",
                "https://image.tmdb.org/t/p/w500/mo0FP1GxOFZT4UDde7RFDz5APXF.jpg",
                "2012-10-10",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                5.8));
        detailTVShowResponses.add(new DetailTVShowResponse(60735,
                "The Flash",
                "https://image.tmdb.org/t/p/w500/wHa6KOJAoNTFLFtp7wguUJKSnju.jpg",
                "2014-10-07",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                6.7));

        return detailTVShowResponses;
    }

    public static ArrayList<GenresItem> generateDummyResponseGenreMovie() {
        ArrayList<GenresItem> genresItems = new ArrayList<>();

        genresItems.add(new GenresItem("Action", 28));
        genresItems.add(new GenresItem("Adventure", 12));

        return genresItems;
    }

    public static ArrayList<GenresItem> generateDummyResponseGenreTVShow() {
        ArrayList<GenresItem> genresItems = new ArrayList<>();

        genresItems.add(new GenresItem("Animation", 16));
        genresItems.add(new GenresItem("Comedy", 35));

        return genresItems;
    }
}

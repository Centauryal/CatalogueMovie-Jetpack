package com.centaury.cataloguemovie.ui.detail;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.centaury.cataloguemovie.BuildConfig;
import com.centaury.cataloguemovie.R;
import com.centaury.cataloguemovie.data.local.entity.MovieEntity;
import com.centaury.cataloguemovie.data.local.entity.TVShowEntity;
import com.centaury.cataloguemovie.utils.AppConstants;
import com.centaury.cataloguemovie.utils.GlideApp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;

public class DetailMovieActivity extends AppCompatActivity {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    ViewModelProvider.Factory factory;

    private DateFormat inputDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private DateFormat outputDate = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());

    private DetailMovieResponse detailMovieResponse;
    private DetailTVShowResponse detailTVShowResponse;
    private DetailMovieViewModel detailMovieViewModel;
    private String imageBaseUrl = BuildConfig.IMAGE_URL;
    private String sizeImage = AppConstants.SIZE_IMAGE;
    private int movieId;
    private int tvshowId;
    private Boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(view.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }

        detailMovieViewModel = obtainViewModel(this);
        String language = Locale.getDefault().toLanguageTag();

        mClData.setVisibility(View.GONE);
        mShimmerViewContainer.setVisibility(View.VISIBLE);
        mShimmerViewContainer.startShimmer();

        movieId = getIntent().getIntExtra(AppConstants.DETAIL_EXTRA_MOVIE, 0);
        if (movieId != 0) {
            detailMovieViewModel.setMovieId(String.valueOf(movieId));
            detailMovieViewModel.getDetailMovie(language).observe(this, detailMovieResponse -> {
                mShimmerViewContainer.stopShimmer();
                mShimmerViewContainer.setVisibility(View.GONE);
                mClData.setVisibility(View.VISIBLE);
                itemMovie(detailMovieResponse);
                this.detailMovieResponse = detailMovieResponse;
                stateFavoriteMovie(movieId);
            });

        } else {
            tvshowId = getIntent().getIntExtra(AppConstants.DETAIL_EXTRA_TV_SHOW, 0);

            detailMovieViewModel.setTvshowId(String.valueOf(tvshowId));
            detailMovieViewModel.getDetailTVShow(language).observe(this, detailTVShowResponse -> {
                mShimmerViewContainer.stopShimmer();
                mShimmerViewContainer.setVisibility(View.GONE);
                mClData.setVisibility(View.VISIBLE);
                itemTVShow(detailTVShowResponse);
                this.detailTVShowResponse = detailTVShowResponse;
                stateFavoriteTVShow(tvshowId);
            });
        }

        setFavorite();
        mBtnFavorite.setScale(2.481f);
    }

    private void itemMovie(DetailMovieResponse movie) {
        mTxtTitledetail.setText(movie.getTitle());
        mTxtRatemovie.setText(String.valueOf(movie.getVoteAverage()));
        float movieRating = (float) (movie.getVoteAverage() / 2);
        mRbRatingdetail.setRating(movieRating);

        if (movie.getGenres().size() == 0) {
            mTxtGenredetail.setText(getResources().getString(R.string.txt_no_genre));
        } else {
            getGenresString(movie.getGenres());
        }

        if (movie.getOverview() == null || movie.getOverview().equals("")) {
            mTxtDescdetail.setText(getResources().getString(R.string.txt_no_desc));
        } else {
            mTxtDescdetail.setText(movie.getOverview());
        }

        GlideApp.with(this)
                .load(imageBaseUrl + sizeImage + movie.getPosterPath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(mIvImgdetail);
        if (movie.getBackdropPath() != null) {
            GlideApp.with(this)
                    .load(imageBaseUrl + sizeImage + movie.getBackdropPath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(mIvCoverdetail);
        } else {
            GlideApp.with(this)
                    .load(imageBaseUrl + sizeImage + movie.getPosterPath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(mIvCoverdetail);
        }

        try {
            Date date = inputDate.parse(movie.getReleaseDate());
            String releaseDate = null;
            if (date != null) {
                releaseDate = outputDate.format(date);
            }
            mTxtDatedetail.setText(releaseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void itemTVShow(DetailTVShowResponse tvshow) {
        mTxtTitledetail.setText(tvshow.getName());
        mTxtRatemovie.setText(String.valueOf(tvshow.getVoteAverage()));
        float movieRating = (float) (tvshow.getVoteAverage() / 2);
        mRbRatingdetail.setRating(movieRating);

        if (tvshow.getGenres().size() == 0) {
            mTxtGenredetail.setText(getResources().getString(R.string.txt_no_genre));
        } else {
            getGenresString(tvshow.getGenres());
        }

        if (tvshow.getOverview() == null || tvshow.getOverview().equals("")) {
            mTxtDescdetail.setText(getResources().getString(R.string.txt_no_desc));
        } else {
            mTxtDescdetail.setText(tvshow.getOverview());
        }

        GlideApp.with(this)
                .load(imageBaseUrl + sizeImage + tvshow.getPosterPath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(mIvImgdetail);
        if (tvshow.getBackdropPath() != null) {
            GlideApp.with(this)
                    .load(imageBaseUrl + sizeImage + tvshow.getBackdropPath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(mIvCoverdetail);
        } else {
            GlideApp.with(this)
                    .load(imageBaseUrl + sizeImage + tvshow.getPosterPath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(mIvCoverdetail);
        }

        try {
            Date date = inputDate.parse(tvshow.getFirstAirDate());
            String releaseDate = null;
            if (date != null) {
                releaseDate = outputDate.format(date);
            }
            mTxtDatedetail.setText(releaseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmer();
    }

    @Override
    public void onPause() {
        mShimmerViewContainer.stopShimmer();
        super.onPause();
    }

    private void getGenresString(List<GenresItem> itemList) {
        List<String> genreMovies = new ArrayList<>();
        try {
            if (itemList.size() == 1) {
                for (GenresItem genresItem : itemList) {
                    genreMovies.add(genresItem.getName());
                }
                mTxtGenredetail.setText(TextUtils.join(", ", genreMovies));
            } else {
                List<GenresItem> genreMore = itemList.subList(0, 2);
                for (GenresItem genresItem : genreMore) {
                    genreMovies.add(genresItem.getName());
                }
                mTxtGenredetail.setText(TextUtils.join(", ", genreMovies));
            }
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            System.out.println("Exception thrown : " + e);
        }
    }

    private void addFavorite() {
        if (movieId != 0) {
            MovieEntity movieEntity = new MovieEntity(detailMovieResponse.getId(), mTxtTitledetail.getText().toString(), detailMovieResponse.getOriginalTitle(),
                    detailMovieResponse.getPosterPath(), detailMovieResponse.getBackdropPath(), mTxtGenredetail.getText().toString(),
                    detailMovieResponse.getReleaseDate(), mTxtDescdetail.getText().toString(), detailMovieResponse.getVoteAverage());
            detailMovieViewModel.insertFavoriteMovie(movieEntity);
        } else {
            TVShowEntity tvShowEntity = new TVShowEntity(detailTVShowResponse.getId(), mTxtTitledetail.getText().toString(), detailTVShowResponse.getOriginalName(),
                    detailTVShowResponse.getPosterPath(), detailTVShowResponse.getBackdropPath(), mTxtGenredetail.getText().toString(),
                    detailTVShowResponse.getFirstAirDate(), mTxtDescdetail.getText().toString(), detailTVShowResponse.getVoteAverage());
            detailMovieViewModel.insertFavoriteTVShow(tvShowEntity);
        }

        Toast.makeText(this, getString(R.string.txt_movie_add), Toast.LENGTH_SHORT).show();
    }

    private void removeFavorite(int id) {
        if (movieId != 0) {
            final MovieEntity movieEntity;
            try {
                movieEntity = detailMovieViewModel.getDetailFavMovie(id);
                detailMovieViewModel.deleteFavoriteMovie(movieEntity);

            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            final TVShowEntity tvShowEntity;
            try {
                tvShowEntity = detailMovieViewModel.getDetailFavTVShow(id);
                detailMovieViewModel.deleteFavoriteTVShow(tvShowEntity);

            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        Toast.makeText(this, getString(R.string.txt_movie_remove), Toast.LENGTH_SHORT).show();
    }

    private void stateFavoriteMovie(int id) {
        final MovieEntity movieEntity;
        try {
            movieEntity = detailMovieViewModel.getDetailFavMovie(id);
            if (movieEntity != null) {
                isFavorite = true;
            }
            setFavorite();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void stateFavoriteTVShow(int id) {
        final TVShowEntity tvShowEntity;
        try {
            tvShowEntity = detailMovieViewModel.getDetailFavTVShow(id);
            if (tvShowEntity != null) {
                isFavorite = true;
            }
            setFavorite();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void setFavorite() {
        if (isFavorite) {
            mBtnFavorite.setSpeed(1f);
            mBtnFavorite.setProgress(1f);
        } else {
            mBtnFavorite.setProgress(0f);
            mBtnFavorite.setSpeed(-2f);
        }
    }

    @OnClick(R.id.btn_favorite)
    public void onClick(View v) {
        if (v.getId() == R.id.btn_favorite) {
            if (isFavorite) {
                if (movieId != 0) {
                    removeFavorite(movieId);
                } else {
                    removeFavorite(tvshowId);
                }
            } else {
                addFavorite();
            }

            isFavorite = !isFavorite;

            setFavorite();
            mBtnFavorite.playAnimation();
        }
    }
}

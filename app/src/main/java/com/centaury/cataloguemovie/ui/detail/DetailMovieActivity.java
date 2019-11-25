package com.centaury.cataloguemovie.ui.detail;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.request.RequestOptions;
import com.centaury.cataloguemovie.BuildConfig;
import com.centaury.cataloguemovie.R;
import com.centaury.cataloguemovie.ViewModelProviderFactory;
import com.centaury.cataloguemovie.data.local.entity.GenreMovieEntity;
import com.centaury.cataloguemovie.data.local.entity.GenreTVShowEntity;
import com.centaury.cataloguemovie.data.local.entity.MovieEntity;
import com.centaury.cataloguemovie.data.local.entity.TVShowEntity;
import com.centaury.cataloguemovie.utils.AppConstants;
import com.centaury.cataloguemovie.utils.GlideApp;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailMovieActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_coverdetail)
    ImageView mIvCoverdetail;
    @BindView(R.id.iv_imgdetail)
    ImageView mIvImgdetail;
    @BindView(R.id.txt_titledetail)
    TextView mTxtTitledetail;
    @BindView(R.id.txt_genredetail)
    TextView mTxtGenredetail;
    @BindView(R.id.rb_ratingdetail)
    RatingBar mRbRatingdetail;
    @BindView(R.id.txt_ratemovie)
    TextView mTxtRatemovie;
    @BindView(R.id.txt_datedetail)
    TextView mTxtDatedetail;
    @BindView(R.id.txt_descdetail)
    TextView mTxtDescdetail;
    @BindView(R.id.shimmer_view_container)
    ShimmerFrameLayout mShimmerViewContainer;
    @BindView(R.id.cl_data)
    ConstraintLayout mClData;
    @BindView(R.id.btn_favorite)
    LottieAnimationView mBtnFavorite;

    private DateFormat inputDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private DateFormat outputDate = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());

    private List<GenreMovieEntity> genreMovieEntities = new ArrayList<>();
    private List<GenreTVShowEntity> genreTVShowEntities = new ArrayList<>();
    private MovieEntity movieEntity;
    private TVShowEntity tvShowEntity;
    private DetailMovieViewModel detailMovieViewModel;
    private String imageBaseUrl = BuildConfig.IMAGE_URL;
    private String sizeImage = AppConstants.SIZE_IMAGE;
    private int movieId;
    private Boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        ButterKnife.bind(this);
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
        detailMovieViewModel.setLanguage(Locale.getDefault().toLanguageTag());

        movieId = getIntent().getIntExtra(AppConstants.DETAIL_EXTRA_MOVIE, 0);
        if (movieId != 0) {
            mClData.setVisibility(View.GONE);
            mShimmerViewContainer.setVisibility(View.VISIBLE);
            mShimmerViewContainer.startShimmer();
            detailMovieViewModel.setMovieId(String.valueOf(movieId));

            detailMovieViewModel.getDetailMovie().observe(this, detailMovie -> {
                if (detailMovie != null) {
                    switch (detailMovie.status) {
                        case LOADING:
                            mShimmerViewContainer.startShimmer();
                            break;
                        case SUCCESS:
                            mShimmerViewContainer.stopShimmer();
                            mShimmerViewContainer.setVisibility(View.GONE);
                            mClData.setVisibility(View.VISIBLE);
                            movieEntity = detailMovie.data;
                            itemMovie(movieEntity);
                            isFavorite = movieEntity.isMovieFavorited();
                            setFavorite();
                            break;
                        case ERROR:
                            mShimmerViewContainer.stopShimmer();
                            mShimmerViewContainer.setVisibility(View.GONE);
                            Toast.makeText(this, getString(R.string.txt_error), Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
            detailMovieViewModel.getGenreMovie().observe(this, genreItem -> {
                if (genreItem != null) {
                    switch (genreItem.status) {
                        case LOADING:
                            break;
                        case SUCCESS:
                            if (genreItem.data != null) {
                                genreMovieEntities.addAll(genreItem.data);
                            }
                            break;
                        case ERROR:
                            Toast.makeText(this, getString(R.string.txt_error), Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });

        } else {
            int tvshowId = getIntent().getIntExtra(AppConstants.DETAIL_EXTRA_TVSHOW, 0);
            mClData.setVisibility(View.GONE);
            mShimmerViewContainer.setVisibility(View.VISIBLE);
            mShimmerViewContainer.startShimmer();
            detailMovieViewModel.setTvshowId(String.valueOf(tvshowId));

            detailMovieViewModel.getDetailTVShow().observe(this, detailTVShow -> {
                if (detailTVShow != null) {
                    switch (detailTVShow.status) {
                        case LOADING:
                            mShimmerViewContainer.startShimmer();
                            break;
                        case SUCCESS:
                            mShimmerViewContainer.stopShimmer();
                            mShimmerViewContainer.setVisibility(View.GONE);
                            mClData.setVisibility(View.VISIBLE);
                            tvShowEntity = detailTVShow.data;
                            itemTVShow(tvShowEntity);
                            isFavorite = tvShowEntity.isTvshowFavorited();
                            setFavorite();
                            break;
                        case ERROR:
                            mShimmerViewContainer.stopShimmer();
                            mShimmerViewContainer.setVisibility(View.GONE);
                            Toast.makeText(this, getString(R.string.txt_error), Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });

            detailMovieViewModel.getGenreTVShow().observe(this, genreItemList -> {
                if (genreItemList != null) {
                    switch (genreItemList.status) {
                        case LOADING:
                            break;
                        case SUCCESS:
                            if (genreItemList.data != null) {
                                genreTVShowEntities.addAll(genreItemList.data);
                            }
                            break;
                        case ERROR:
                            Toast.makeText(this, getString(R.string.txt_error), Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
        }

        mBtnFavorite.setScale(2.481f);
    }


    @NonNull
    private static DetailMovieViewModel obtainViewModel(AppCompatActivity activity) {
        ViewModelProviderFactory factory = ViewModelProviderFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(DetailMovieViewModel.class);
    }

    private void itemMovie(MovieEntity movie) {
        mTxtTitledetail.setText(movie.getName());
        mTxtRatemovie.setText(String.valueOf(movie.getVoteAverage()));
        float movieRating = (float) (movie.getVoteAverage() / 2);
        mRbRatingdetail.setRating(movieRating);

        if (movie.getGenres() == null | movie.getGenres().equals("")) {
            mTxtGenredetail.setText(getResources().getString(R.string.txt_no_genre));
        } else {
            getGenresMovieString(movie.getGenres());
        }

        if (movie.getOverview() == null || movie.getOverview().equals("")) {
            mTxtDescdetail.setText(getResources().getString(R.string.txt_nodesc));
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

    private void itemTVShow(TVShowEntity tvshow) {
        mTxtTitledetail.setText(tvshow.getName());
        mTxtRatemovie.setText(String.valueOf(tvshow.getVoteAverage()));
        float movieRating = (float) (tvshow.getVoteAverage() / 2);
        mRbRatingdetail.setRating(movieRating);

        if (tvshow.getGenres() == null | tvshow.getGenres().equals("")) {
            mTxtGenredetail.setText(getResources().getString(R.string.txt_no_genre));
        } else {
            getGenresTVShowString(tvshow.getGenres());
        }

        if (tvshow.getOverview() == null || tvshow.getOverview().equals("")) {
            mTxtDescdetail.setText(getResources().getString(R.string.txt_nodesc));
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
            Date date = inputDate.parse(tvshow.getReleaseDate());
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

    private void getGenresMovieString(String itemList) {
        List<String> genre = new ArrayList<>(Arrays.asList(itemList.split(", ")));
        List<String> genreMovies = new ArrayList<>();
        try {
            if (genre.size() == 1) {
                for (String genreId : genre) {
                    for (GenreMovieEntity genresItem : genreMovieEntities) {
                        if (genresItem.getGenreId() == Integer.parseInt(genreId)) {
                            genreMovies.add(genresItem.getName());
                        }
                    }
                    mTxtGenredetail.setText(TextUtils.join(", ", genreMovies));
                }

            } else {
                List<String> genreMore = genre.subList(0, 2);
                for (String genreId : genreMore) {
                    for (GenreMovieEntity genresItem : genreMovieEntities) {
                        if (genresItem.getGenreId() == Integer.parseInt(genreId)) {
                            genreMovies.add(genresItem.getName());
                        }
                    }
                    mTxtGenredetail.setText(TextUtils.join(", ", genreMovies));
                }
            }
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            System.out.println("Exception thrown : " + e);
        }
    }

    private void getGenresTVShowString(String itemList) {
        List<String> genre = new ArrayList<>(Arrays.asList(itemList.split(", ")));
        List<String> genreMovies = new ArrayList<>();
        try {
            if (genre.size() == 1) {
                for (String genreId : genre) {
                    for (GenreTVShowEntity genresItem : genreTVShowEntities) {
                        if (genresItem.getGenreId() == Integer.parseInt(genreId)) {
                            genreMovies.add(genresItem.getName());
                        }
                    }
                }
                mTxtGenredetail.setText(TextUtils.join(", ", genreMovies));
            } else {
                List<String> genreMore = genre.subList(0, 2);
                for (String genreId : genreMore) {
                    for (GenreTVShowEntity genresItem : genreTVShowEntities) {
                        if (genresItem.getGenreId() == Integer.parseInt(genreId)) {
                            genreMovies.add(genresItem.getName());
                        }
                    }
                }
                mTxtGenredetail.setText(TextUtils.join(", ", genreMovies));
            }
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            System.out.println("Exception thrown : " + e);
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
                    detailMovieViewModel.setFavoriteMovie(movieEntity, false);
                } else {
                    detailMovieViewModel.setFavoriteTVShow(tvShowEntity, false);
                }
            } else {
                if (movieId != 0) {
                    detailMovieViewModel.setFavoriteMovie(movieEntity, true);
                } else {
                    detailMovieViewModel.setFavoriteTVShow(tvShowEntity, true);
                }
            }

            isFavorite = !isFavorite;

            setFavorite();
            mBtnFavorite.playAnimation();
        }
    }

}

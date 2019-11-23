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
import com.centaury.cataloguemovie.data.remote.detail.movie.DetailMovieResponse;
import com.centaury.cataloguemovie.data.remote.detail.tvshow.DetailTVShowResponse;
import com.centaury.cataloguemovie.data.remote.genre.GenresItem;
import com.centaury.cataloguemovie.utils.AppConstants;
import com.centaury.cataloguemovie.utils.GlideApp;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMovieActivity extends AppCompatActivity {

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

    private String imageBaseUrl = BuildConfig.IMAGE_URL;
    private String sizeImage = AppConstants.SIZE_IMAGE;
    private Boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
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

        DetailMovieViewModel detailMovieViewModel = obtainViewModel(this);
        String language = Locale.getDefault().toLanguageTag();

        int movieId = getIntent().getIntExtra(AppConstants.DETAIL_EXTRA_MOVIE, 0);
        if (movieId != 0) {
            mClData.setVisibility(View.GONE);
            mShimmerViewContainer.setVisibility(View.VISIBLE);
            mShimmerViewContainer.startShimmer();
            detailMovieViewModel.setMovieId(String.valueOf(movieId));

            /*detailMovieViewModel.getDetailMovie(language).observe(this, detailMovieResponse -> {
                mShimmerViewContainer.stopShimmer();
                mShimmerViewContainer.setVisibility(View.GONE);
                mClData.setVisibility(View.VISIBLE);
                itemMovie(detailMovieResponse);
            });*/
        } else {
            int tvshowId = getIntent().getIntExtra(AppConstants.DETAIL_EXTRA_TVSHOW, 0);
            mClData.setVisibility(View.GONE);
            mShimmerViewContainer.setVisibility(View.VISIBLE);
            mShimmerViewContainer.startShimmer();
            detailMovieViewModel.setTvshowId(String.valueOf(tvshowId));

            /*detailMovieViewModel.getDetailTVShow(language).observe(this, detailTVShowResponse -> {
                mShimmerViewContainer.stopShimmer();
                mShimmerViewContainer.setVisibility(View.GONE);
                mClData.setVisibility(View.VISIBLE);
                itemTVShow(detailTVShowResponse);
            });*/
        }

        mBtnFavorite.setScale(2.481f);
    }

    @NonNull
    private static DetailMovieViewModel obtainViewModel(AppCompatActivity activity) {
        ViewModelProviderFactory factory = ViewModelProviderFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(DetailMovieViewModel.class);
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

    private void setFavorite() {
        if (isFavorite) {
            mBtnFavorite.setSpeed(1f);
            mBtnFavorite.setProgress(1f);
        } else {
            mBtnFavorite.setProgress(0f);
            mBtnFavorite.setSpeed(-2f);
        }
    }

}

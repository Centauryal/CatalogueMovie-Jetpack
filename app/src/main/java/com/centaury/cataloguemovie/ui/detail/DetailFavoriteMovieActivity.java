package com.centaury.cataloguemovie.ui.detail;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
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
import com.centaury.cataloguemovie.data.local.entity.MovieEntity;
import com.centaury.cataloguemovie.data.local.entity.TVShowEntity;
import com.centaury.cataloguemovie.utils.AppConstants;
import com.centaury.cataloguemovie.utils.GlideApp;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailFavoriteMovieActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_coverfavdetail)
    ImageView mIvCoverFavdetail;
    @BindView(R.id.iv_imgfavdetail)
    ImageView mIvImgFavdetail;
    @BindView(R.id.txt_titlefavdetail)
    TextView mTxtTitleFavdetail;
    @BindView(R.id.txt_genrefavdetail)
    TextView mTxtGenreFavdetail;
    @BindView(R.id.rb_ratingfavdetail)
    RatingBar mRbRatingFavdetail;
    @BindView(R.id.txt_ratefavmovie)
    TextView mTxtRateFavmovie;
    @BindView(R.id.txt_datefavdetail)
    TextView mTxtDateFavdetail;
    @BindView(R.id.txt_descfavdetail)
    TextView mTxtDescFavdetail;
    @BindView(R.id.shimmer_view_container)
    ShimmerFrameLayout mShimmerViewContainer;
    @BindView(R.id.cl_favdata)
    ConstraintLayout mClFavData;
    @BindView(R.id.btn_favorite)
    LottieAnimationView mBtnFavorite;

    private DateFormat inputDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private DateFormat outputDate = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());

    private MovieEntity movieEntity;
    private TVShowEntity tvShowEntity;
    private DetailFavoriteMovieViewModel detailFavoriteMovieViewModel;
    private String imageBaseUrl = BuildConfig.IMAGE_URL;
    private String sizeImage = AppConstants.SIZE_IMAGE;
    private int movieId;
    private int tvshowId;
    private Boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_favorite_movie);
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

        detailFavoriteMovieViewModel = obtainViewModel(this);
        movieId = getIntent().getIntExtra(AppConstants.DETAIL_EXTRA_FAVMOVIE, 0);

        mClFavData.setVisibility(View.GONE);
        mShimmerViewContainer.setVisibility(View.VISIBLE);
        mShimmerViewContainer.startShimmer();

        if (movieId != 0) {
            mShimmerViewContainer.stopShimmer();
            mShimmerViewContainer.setVisibility(View.GONE);
            mClFavData.setVisibility(View.VISIBLE);
            try {
                movieEntity = detailFavoriteMovieViewModel.getDetailFavMovie(movieId);
                itemMovie(movieEntity);
                stateFavoriteMovie(movieId);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            tvshowId = getIntent().getIntExtra(AppConstants.DETAIL_EXTRA_FAVTVSHOW, 0);
            mShimmerViewContainer.stopShimmer();
            mShimmerViewContainer.setVisibility(View.GONE);
            mClFavData.setVisibility(View.VISIBLE);
            try {
                tvShowEntity = detailFavoriteMovieViewModel.getDetailFavTVShow(tvshowId);
                itemTVShow(tvShowEntity);
                stateFavoriteTVShow(tvshowId);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        setFavorite();
        mBtnFavorite.setScale(2.481f);
    }

    private void itemMovie(MovieEntity movie) {
        mTxtTitleFavdetail.setText(movie.getName());
        mTxtRateFavmovie.setText(String.valueOf(movie.getVoteAverage()));
        float movieRating = (float) (movie.getVoteAverage() / 2);
        mRbRatingFavdetail.setRating(movieRating);

        if (movie.getGenres() == null || movie.getGenres().equals("")) {
            mTxtGenreFavdetail.setText(getResources().getString(R.string.txt_no_genre));
        } else {
            mTxtGenreFavdetail.setText(movie.getGenres());
        }

        if (movie.getOverview() == null || movie.getOverview().equals("")) {
            mTxtDescFavdetail.setText(getResources().getString(R.string.txt_nodesc));
        } else {
            mTxtDescFavdetail.setText(movie.getOverview());
        }

        GlideApp.with(this)
                .load(imageBaseUrl + sizeImage + movie.getPosterPath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(mIvImgFavdetail);
        if (movie.getBackdropPath() != null) {
            GlideApp.with(this)
                    .load(imageBaseUrl + sizeImage + movie.getBackdropPath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(mIvCoverFavdetail);
        } else {
            GlideApp.with(this)
                    .load(imageBaseUrl + sizeImage + movie.getPosterPath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(mIvCoverFavdetail);
        }

        try {
            Date date = inputDate.parse(movie.getReleaseDate());
            String releaseDate = null;
            if (date != null) {
                releaseDate = outputDate.format(date);
            }
            mTxtDateFavdetail.setText(releaseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void itemTVShow(TVShowEntity tvshow) {
        mTxtTitleFavdetail.setText(tvshow.getName());
        mTxtRateFavmovie.setText(String.valueOf(tvshow.getVoteAverage()));
        float movieRating = (float) (tvshow.getVoteAverage() / 2);
        mRbRatingFavdetail.setRating(movieRating);

        if (tvshow.getGenres() == null || tvshow.getGenres().equals("")) {
            mTxtGenreFavdetail.setText(getResources().getString(R.string.txt_no_genre));
        } else {
            mTxtGenreFavdetail.setText(tvshow.getGenres());
        }

        if (tvshow.getOverview() == null || tvshow.getOverview().equals("")) {
            mTxtDescFavdetail.setText(getResources().getString(R.string.txt_nodesc));
        } else {
            mTxtDescFavdetail.setText(tvshow.getOverview());
        }

        GlideApp.with(this)
                .load(imageBaseUrl + sizeImage + tvshow.getPosterPath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(mIvImgFavdetail);
        if (tvshow.getBackdropPath() != null) {
            GlideApp.with(this)
                    .load(imageBaseUrl + sizeImage + tvshow.getBackdropPath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(mIvCoverFavdetail);
        } else {
            GlideApp.with(this)
                    .load(imageBaseUrl + sizeImage + tvshow.getPosterPath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(mIvCoverFavdetail);
        }

        try {
            Date date = inputDate.parse(tvshow.getReleaseDate());
            String releaseDate = null;
            if (date != null) {
                releaseDate = outputDate.format(date);
            }
            mTxtDateFavdetail.setText(releaseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    private static DetailFavoriteMovieViewModel obtainViewModel(AppCompatActivity activity) {
        ViewModelProviderFactory factory = ViewModelProviderFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(DetailFavoriteMovieViewModel.class);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
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

    private void addFavorite() {
        if (movieId != 0) {
            MovieEntity entity = new MovieEntity(movieEntity.getMovieId(), mTxtTitleFavdetail.getText().toString(), movieEntity.getOriginalName(),
                    movieEntity.getPosterPath(), movieEntity.getBackdropPath(), mTxtGenreFavdetail.getText().toString(),
                    movieEntity.getReleaseDate(), mTxtDescFavdetail.getText().toString(), movieEntity.getVoteAverage());
            detailFavoriteMovieViewModel.insertFavoriteMovie(entity);
        } else {
            TVShowEntity entity = new TVShowEntity(tvShowEntity.getTvshowId(), mTxtTitleFavdetail.getText().toString(), tvShowEntity.getOriginalName(),
                    tvShowEntity.getPosterPath(), tvShowEntity.getBackdropPath(), mTxtGenreFavdetail.getText().toString(),
                    tvShowEntity.getReleaseDate(), mTxtDescFavdetail.getText().toString(), tvShowEntity.getVoteAverage());
            detailFavoriteMovieViewModel.insertFavoriteTVShow(entity);
        }

        Toast.makeText(this, getString(R.string.txt_add_movie), Toast.LENGTH_SHORT).show();
    }

    private void removeFavorite(int id) {
        if (movieId != 0) {
            final MovieEntity movieEntity;
            try {
                movieEntity = detailFavoriteMovieViewModel.getDetailFavMovie(id);
                detailFavoriteMovieViewModel.deleteFavoriteMovie(movieEntity);

            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            final TVShowEntity tvShowEntity;
            try {
                tvShowEntity = detailFavoriteMovieViewModel.getDetailFavTVShow(id);
                detailFavoriteMovieViewModel.deleteFavoriteTVShow(tvShowEntity);

            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        Toast.makeText(this, getString(R.string.txt_remove_movie), Toast.LENGTH_SHORT).show();
    }

    private void stateFavoriteMovie(int id) {
        final MovieEntity movieEntity;
        try {
            movieEntity = detailFavoriteMovieViewModel.getDetailFavMovie(id);
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
            tvShowEntity = detailFavoriteMovieViewModel.getDetailFavTVShow(id);
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

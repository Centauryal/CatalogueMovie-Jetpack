package com.centaury.cataloguemovie.ui.detail;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.request.RequestOptions;
import com.centaury.cataloguemovie.R;
import com.centaury.cataloguemovie.data.MovieEntity;
import com.centaury.cataloguemovie.data.TVShowEntity;
import com.centaury.cataloguemovie.utils.GlideApp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailMovieActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String EXTRA_TVSHOW = "extra_tvshow";

    private ImageView mIvCoverdetail;
    private ImageView mIvImgdetail;
    private TextView mTxtTitledetail;
    private RatingBar mRbRatingdetail;
    private TextView mTxtRatemovie;
    private TextView mTxtDatedetail;
    private TextView mTxtDescdetail;

    private DateFormat inputDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private DateFormat outputDate = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());

    private DetailMovieViewModel detailMovieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
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

        detailMovieViewModel = ViewModelProviders.of(this).get(DetailMovieViewModel.class);

        mIvCoverdetail = findViewById(R.id.iv_coverdetail);
        mIvImgdetail = findViewById(R.id.iv_imgdetail);
        mTxtTitledetail = findViewById(R.id.txt_titledetail);
        mRbRatingdetail = findViewById(R.id.rb_ratingdetail);
        mTxtRatemovie = findViewById(R.id.txt_ratemovie);
        mTxtDatedetail = findViewById(R.id.txt_datedetail);
        mTxtDescdetail = findViewById(R.id.txt_descdetail);

        int movieId = getIntent().getIntExtra(EXTRA_MOVIE, 0);
        if (movieId != 0) {
            detailMovieViewModel.setMovieId(movieId);
            if (detailMovieViewModel.getMovie() != null) {
                itemMovie(detailMovieViewModel.getMovie());
            }
        } else {
            int tvshowId = getIntent().getIntExtra(EXTRA_TVSHOW, 0);
            detailMovieViewModel.setTvshowId(tvshowId);
            if (detailMovieViewModel.getTvShow() != null) {
                itemTVShow(detailMovieViewModel.getTvShow());
            }
        }
    }

    private void itemMovie(MovieEntity entity) {
        mTxtTitledetail.setText(entity.getName());
        mTxtRatemovie.setText(entity.getRating());
        int rate = (int) Double.parseDouble(entity.getRating());
        float movieRating = (float) (rate / 2);
        mRbRatingdetail.setRating(movieRating);
        GlideApp.with(this)
                .load(entity.getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(mIvImgdetail);
        GlideApp.with(this)
                .load(entity.getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(mIvCoverdetail);
        mTxtDescdetail.setText(entity.getDesc());

        try {
            Date date = inputDate.parse(entity.getDate());
            String releaseDate = null;
            if (date != null) {
                releaseDate = outputDate.format(date);
            }
            mTxtDatedetail.setText(releaseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void itemTVShow(TVShowEntity entity) {
        mTxtTitledetail.setText(entity.getName());
        mTxtRatemovie.setText(entity.getRating());
        int rate = (int) Double.parseDouble(entity.getRating());
        float movieRating = (float) (rate / 2);
        mRbRatingdetail.setRating(movieRating);
        GlideApp.with(this)
                .load(entity.getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(mIvImgdetail);
        GlideApp.with(this)
                .load(entity.getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(mIvCoverdetail);
        mTxtDescdetail.setText(entity.getDesc());

        try {
            Date date = inputDate.parse(entity.getDate());
            String releaseDate = null;
            if (date != null) {
                releaseDate = outputDate.format(date);
            }
            mTxtDatedetail.setText(releaseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}

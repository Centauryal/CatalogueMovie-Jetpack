package com.centaury.cataloguemovie.ui.movie;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.centaury.cataloguemovie.BuildConfig;
import com.centaury.cataloguemovie.R;
import com.centaury.cataloguemovie.data.local.entity.GenreMovieEntity;
import com.centaury.cataloguemovie.data.local.entity.MovieEntity;
import com.centaury.cataloguemovie.ui.detail.DetailMovieActivity;
import com.centaury.cataloguemovie.utils.AppConstants;
import com.centaury.cataloguemovie.utils.GlideApp;

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

/**
 * Created by Centaury on 10/7/2019.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final Activity activity;
    private List<MovieEntity> movieResultsList = new ArrayList<>();
    private List<GenreMovieEntity> genresItemList = new ArrayList<>();

    public MovieAdapter(Activity activity) {
        this.activity = activity;
    }

    private List<MovieEntity> getListMovies() {
        return movieResultsList;
    }

    void setListMovies(List<MovieEntity> listMovies) {
        if (listMovies == null) return;
        this.movieResultsList.clear();
        this.movieResultsList.addAll(listMovies);
    }

    void setListGenreMovie(List<GenreMovieEntity> genreMovie) {
        if (genreMovie == null) return;
        this.genresItemList.clear();
        this.genresItemList.addAll(genreMovie);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movielist, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieEntity movie = movieResultsList.get(position);
        holder.bind(movie);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(activity, DetailMovieActivity.class);
            intent.putExtra(AppConstants.DETAIL_EXTRA_MOVIE, getListMovies().get(position).getMovieId());
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return getListMovies().size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_titlebackground)
        TextView mTxtTitlebackground;
        @BindView(R.id.iv_movielist)
        ImageView mIvMovielist;
        @BindView(R.id.txt_genremovielist)
        TextView mTxtGenremovielist;
        @BindView(R.id.txt_titlemovielist)
        TextView mTxtTitlemovielist;
        @BindView(R.id.txt_descmovielist)
        TextView mTxtDescmovielist;
        @BindView(R.id.txt_datemovielist)
        TextView mTxtDatemovielist;

        MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(MovieEntity movie) {
            mTxtTitlemovielist.setText(movie.getName());
            mTxtTitlebackground.setText(movie.getOriginalName());

            if (movie.getOverview() == null || movie.getOverview().equals("")) {
                mTxtDescmovielist.setText(activity.getString(R.string.txt_nodesc));
            } else {
                mTxtDescmovielist.setText(movie.getOverview());
            }

            if (movie.getGenres() == null | movie.getGenres().equals("")) {
                mTxtGenremovielist.setText(activity.getString(R.string.txt_no_genre));
            } else {
                mTxtGenremovielist.setText(getGenres(movie.getGenres()));
            }

            DateFormat inputDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            DateFormat outputDate = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
            try {
                Date date = inputDate.parse(movie.getReleaseDate());
                String releaseDate = null;
                if (date != null) {
                    releaseDate = outputDate.format(date);
                }
                mTxtDatemovielist.setText(releaseDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            GlideApp.with(itemView.getContext())
                    .load(BuildConfig.IMAGE_URL + AppConstants.SIZE_IMAGE + movie.getPosterPath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(mIvMovielist);
        }

        private String getGenres(String genreList) {
            List<String> genre = new ArrayList<>(Arrays.asList(genreList.split(", ")));
            List<String> genreMovies = new ArrayList<>();
            try {
                if (genre.size() == 1) {
                    for (String genreId : genre) {
                        for (GenreMovieEntity genresItem : genresItemList) {
                            if (genresItem.getGenreId() == Integer.parseInt(genreId)) {
                                genreMovies.add(genresItem.getName());
                            }
                        }
                    }
                } else {
                    List<String> integers = genre.subList(0, 2);
                    for (String genreId : integers) {
                        for (GenreMovieEntity genresItem : genresItemList) {
                            if (genresItem.getGenreId() == Integer.parseInt(genreId)) {
                                genreMovies.add(genresItem.getName());
                            }
                        }
                    }
                }
            } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
                System.out.println("Exception thrown : " + e);
            }
            return TextUtils.join(", ", genreMovies);
        }
    }
}

package com.centaury.cataloguemovie.ui.favorite.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
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
import com.centaury.cataloguemovie.data.local.entity.GenreTVShowEntity;
import com.centaury.cataloguemovie.data.local.entity.TVShowEntity;
import com.centaury.cataloguemovie.ui.detail.DetailMovieActivity;
import com.centaury.cataloguemovie.utils.AppConstants;
import com.centaury.cataloguemovie.utils.GlideApp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Centaury on 11/23/2019.
 */
public class FavoriteTVShowAdapter extends RecyclerView.Adapter<FavoriteTVShowAdapter.FavoriteTVShowViewHolder> {

    private final Activity activity;
    private List<TVShowEntity> favoriteTVShowList = new ArrayList<>();
    private List<GenreTVShowEntity> genresItemList = new ArrayList<>();

    public FavoriteTVShowAdapter(Activity activity) {
        this.activity = activity;
    }

    private List<TVShowEntity> getFavoriteTVShowList() {
        return favoriteTVShowList;
    }

    public void setFavoriteTVShowList(List<TVShowEntity> listTVShows) {
        if (listTVShows == null) return;
        this.favoriteTVShowList.clear();
        this.favoriteTVShowList.addAll(listTVShows);
    }

    public void setListGenreTVShow(List<GenreTVShowEntity> genreTVShow) {
        if (genreTVShow == null) return;
        this.genresItemList.clear();
        this.genresItemList.addAll(genreTVShow);
        Log.e("setListGenreTVShow: ", genreTVShow + "");
    }

    @NonNull
    @Override
    public FavoriteTVShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movielist, parent, false);
        return new FavoriteTVShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteTVShowViewHolder holder, int position) {
        TVShowEntity tvshow = favoriteTVShowList.get(position);
        holder.bind(tvshow);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(activity, DetailMovieActivity.class);
            intent.putExtra(AppConstants.DETAIL_EXTRA_TVSHOW, getFavoriteTVShowList().get(position).getTvshowId());
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return getFavoriteTVShowList().size();
    }

    class FavoriteTVShowViewHolder extends RecyclerView.ViewHolder {
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

        FavoriteTVShowViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(TVShowEntity tvshow) {
            mTxtTitlemovielist.setText(tvshow.getName());
            mTxtTitlebackground.setText(tvshow.getOriginalName());

            if (tvshow.getOverview() == null || tvshow.getOverview().equals("")) {
                mTxtDescmovielist.setText(activity.getString(R.string.txt_nodesc));
            } else {
                mTxtDescmovielist.setText(tvshow.getOverview());
            }

            Log.e("bind: ", tvshow.getGenres());
            /*if (movie.getGenreIds().size() == 0) {
                mTxtGenremovielist.setText(activity.getString(R.string.txt_no_genre));
            } else {
                mTxtGenremovielist.setText(getGenres(movie.getGenreIds()));
            }*/

            DateFormat inputDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            DateFormat outputDate = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
            try {
                Date date = inputDate.parse(tvshow.getReleaseDate());
                String releaseDate = null;
                if (date != null) {
                    releaseDate = outputDate.format(date);
                }
                mTxtDatemovielist.setText(releaseDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            GlideApp.with(itemView.getContext())
                    .load(BuildConfig.IMAGE_URL + AppConstants.SIZE_IMAGE + tvshow.getPosterPath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(mIvMovielist);
        }

        /*private String getGenres(List<Integer> genreList) {
            List<String> genreMovies = new ArrayList<>();
            try {
                if (genreList.size() == 1) {
                    for (Integer genreId : genreList) {
                        for (GenresItem genresItem : genresItemList) {
                            if (genresItem.getId() == genreId) {
                                genreMovies.add(genresItem.getName());
                            }
                        }
                    }
                } else {
                    List<Integer> integers = genreList.subList(0, 2);
                    for (Integer genreId : integers) {
                        for (GenresItem genresItem : genresItemList) {
                            if (genresItem.getId() == genreId) {
                                genreMovies.add(genresItem.getName());
                            }
                        }
                    }
                }
            } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
                System.out.println("Exception thrown : " + e);
            }
            return TextUtils.join(", ", genreMovies);
        }*/
    }
}

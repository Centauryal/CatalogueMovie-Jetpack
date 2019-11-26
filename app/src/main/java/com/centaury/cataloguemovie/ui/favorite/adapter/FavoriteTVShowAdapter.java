package com.centaury.cataloguemovie.ui.favorite.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Centaury on 11/23/2019.
 */
public class FavoriteTVShowAdapter extends PagedListAdapter<TVShowEntity, FavoriteTVShowAdapter.FavoriteTVShowViewHolder> {

    private final Activity activity;
    private List<TVShowEntity> favoriteTVShowList = new ArrayList<>();
    private List<GenreTVShowEntity> genresItemList = new ArrayList<>();

    public FavoriteTVShowAdapter(Activity activity) {
        super(DIFF_CALLBACK);
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

    private static DiffUtil.ItemCallback<TVShowEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<TVShowEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull TVShowEntity oldItem, @NonNull TVShowEntity newItem) {
                    return oldItem.getTvshowId() == newItem.getTvshowId();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull TVShowEntity oldItem, @NonNull TVShowEntity newItem) {
                    return oldItem.equals(newItem);
                }
            };

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

            if (tvshow.getGenres() == null | tvshow.getGenres().equals("")) {
                mTxtGenremovielist.setText(activity.getString(R.string.txt_no_genre));
            } else {
                mTxtGenremovielist.setText(getGenres(tvshow.getGenres()));
            }

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

        private String getGenres(String genreList) {
            List<String> genre = new ArrayList<>(Arrays.asList(genreList.split(", ")));
            List<String> genreMovies = new ArrayList<>();
            try {
                if (genre.size() == 1) {
                    for (String genreId : genre) {
                        for (GenreTVShowEntity genresItem : genresItemList) {
                            if (genresItem.getGenreId() == Integer.parseInt(genreId)) {
                                genreMovies.add(genresItem.getName());
                            }
                        }
                    }
                } else {
                    List<String> integers = genre.subList(0, 2);
                    for (String genreId : integers) {
                        for (GenreTVShowEntity genresItem : genresItemList) {
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

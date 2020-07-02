package com.centaury.cataloguemovie.ui.tvshow;

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
 * Created by Centaury on 10/7/2019.
 */
public class TVShowAdapter extends PagedListAdapter<TVShowResultsItem, TVShowAdapter.TVShowViewHolder> {

    private static DiffUtil.ItemCallback<TVShowResultsItem> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<TVShowResultsItem>() {
                @Override
                public boolean areItemsTheSame(@NonNull TVShowResultsItem oldItem, @NonNull TVShowResultsItem newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull TVShowResultsItem oldItem, @NonNull TVShowResultsItem newItem) {
                    return oldItem.equals(newItem);
                }
            };
    private final Activity activity;
    private List<GenresItem> genresItemList = new ArrayList<>();

    public TVShowAdapter(Activity activity) {
        super(DIFF_CALLBACK);
        this.activity = activity;
    }

    void setListGenreTVShow(List<GenresItem> genreTVShow) {
        if (genreTVShow == null) return;
        this.genresItemList.clear();
        this.genresItemList.addAll(genreTVShow);
    }

    @NonNull
    @Override
    public TVShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_list, parent, false);
        return new TVShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TVShowViewHolder holder, int position) {
        TVShowResultsItem tvshow = getItem(position);
        if (tvshow != null) {
            holder.bind(tvshow);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(activity, DetailMovieActivity.class);
            intent.putExtra(AppConstants.DETAIL_EXTRA_TV_SHOW, tvshow.getId());
            activity.startActivity(intent);
        });
    }

    class TVShowViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_title_background)
        TextView mTxtTitlebackground;
        @BindView(R.id.iv_movie_list)
        ImageView mIvMovielist;
        @BindView(R.id.txt_genre_movie_list)
        TextView mTxtGenremovielist;
        @BindView(R.id.txt_title_movie_list)
        TextView mTxtTitlemovielist;
        @BindView(R.id.txt_desc_movie_list)
        TextView mTxtDescmovielist;
        @BindView(R.id.txt_date_movie_list)
        TextView mTxtDatemovielist;

        TVShowViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(TVShowResultsItem tvshow) {
            mTxtTitlemovielist.setText(tvshow.getName());
            mTxtTitlebackground.setText(tvshow.getOriginalName());

            if (tvshow.getOverview() == null || tvshow.getOverview().equals("")) {
                mTxtDescmovielist.setText(activity.getString(R.string.txt_no_desc));
            } else {
                mTxtDescmovielist.setText(tvshow.getOverview());
            }

            if (tvshow.getGenreIds().size() == 0) {
                mTxtGenremovielist.setText(activity.getString(R.string.txt_no_genre));
            } else {
                mTxtGenremovielist.setText(getGenres(tvshow.getGenreIds()));
            }

            DateFormat inputDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            DateFormat outputDate = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
            try {
                Date date = inputDate.parse(tvshow.getFirstAirDate());
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

        private String getGenres(List<Integer> genreList) {
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
        }
    }
}

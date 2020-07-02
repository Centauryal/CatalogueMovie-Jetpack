package com.centaury.cataloguemovie.ui.favorite.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
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
import com.centaury.cataloguemovie.data.local.entity.TVShowEntity;
import com.centaury.cataloguemovie.ui.detail.DetailFavoriteMovieActivity;
import com.centaury.cataloguemovie.utils.AppConstants;
import com.centaury.cataloguemovie.utils.GlideApp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Centaury on 11/23/2019.
 */
public class FavoriteTVShowAdapter extends PagedListAdapter<TVShowEntity, FavoriteTVShowAdapter.FavoriteTVShowViewHolder> {

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
    private final Activity activity;
    private final FavoriteFragmentCallback callback;

    public FavoriteTVShowAdapter(Activity activity, FavoriteFragmentCallback callback) {
        super(DIFF_CALLBACK);
        this.activity = activity;
        this.callback = callback;
    }

    @NonNull
    @Override
    public FavoriteTVShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite_movielist, parent, false);
        return new FavoriteTVShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteTVShowViewHolder holder, int position) {
        TVShowEntity tvshow = getItem(position);
        if (tvshow != null) {
            holder.bind(tvshow);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(activity, DetailFavoriteMovieActivity.class);
            intent.putExtra(AppConstants.DETAIL_EXTRA_FAV_TV_SHOW, tvshow.getTvshowId());
            activity.startActivity(intent);
        });

        holder.mBtnDelete.setOnClickListener(v -> {
            callback.onDeleteItemClick(tvshow.getTvshowId());
        });
    }

    class FavoriteTVShowViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_title_fav_background)
        TextView mTxtTitlebackground;
        @BindView(R.id.iv_movie_fav_list)
        ImageView mIvMovielist;
        @BindView(R.id.txt_genre_movie_fav_list)
        TextView mTxtGenremovielist;
        @BindView(R.id.txt_title_movie_fav_list)
        TextView mTxtTitlemovielist;
        @BindView(R.id.txt_desc_movie_fav_list)
        TextView mTxtDescmovielist;
        @BindView(R.id.txt_date_movie_fav_list)
        TextView mTxtDatemovielist;
        @BindView(R.id.btn_delete)
        ImageView mBtnDelete;

        FavoriteTVShowViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(TVShowEntity tvshow) {
            mTxtTitlemovielist.setText(tvshow.getName());
            mTxtTitlebackground.setText(tvshow.getOriginalName());
            if (tvshow.getGenres() == null || tvshow.getGenres().equals("")) {
                mTxtGenremovielist.setText(activity.getResources().getString(R.string.txt_no_genre));
            } else {
                mTxtGenremovielist.setText(tvshow.getGenres());
            }

            GlideApp.with(itemView.getContext())
                    .load(BuildConfig.IMAGE_URL + AppConstants.SIZE_IMAGE + tvshow.getPosterPath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(mIvMovielist);

            if (tvshow.getOverview() == null || tvshow.getOverview().equals("")) {
                mTxtDescmovielist.setText(activity.getString(R.string.txt_no_desc));
            } else {
                mTxtDescmovielist.setText(tvshow.getOverview());
            }

            DateFormat inputDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            DateFormat outputDate = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());

            try {
                Date date = inputDate.parse(tvshow.getReleaseDate());
                String releaseDate = outputDate.format(date);
                mTxtDatemovielist.setText(releaseDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}

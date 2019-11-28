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
import com.centaury.cataloguemovie.data.local.entity.MovieEntity;
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
public class FavoriteMovieAdapter extends PagedListAdapter<MovieEntity, FavoriteMovieAdapter.FavoriteMovieViewHolder> {

    private final Activity activity;
    private final FavoriteFragmentCallback callback;

    public FavoriteMovieAdapter(Activity activity, FavoriteFragmentCallback callback) {
        super(DIFF_CALLBACK);
        this.activity = activity;
        this.callback = callback;
    }

    @NonNull
    @Override
    public FavoriteMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite_movielist, parent, false);
        return new FavoriteMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteMovieViewHolder holder, int position) {
        MovieEntity movie = getItem(position);
        if (movie != null) {
            holder.bind(movie);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(activity, DetailFavoriteMovieActivity.class);
            intent.putExtra(AppConstants.DETAIL_EXTRA_FAVMOVIE, movie.getMovieId());
            activity.startActivity(intent);
        });

        holder.mBtnDelete.setOnClickListener(v -> {
            callback.onDeleteItemClick(movie.getMovieId());
        });
    }

    private static DiffUtil.ItemCallback<MovieEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<MovieEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
                    return oldItem.getMovieId() == newItem.getMovieId();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
                    return oldItem.equals(newItem);
                }
            };

    class FavoriteMovieViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_titlefavbackground)
        TextView mTxtTitlebackground;
        @BindView(R.id.iv_moviefavlist)
        ImageView mIvMovielist;
        @BindView(R.id.txt_genremoviefavlist)
        TextView mTxtGenremovielist;
        @BindView(R.id.txt_titlemoviefavlist)
        TextView mTxtTitlemovielist;
        @BindView(R.id.txt_descmoviefavlist)
        TextView mTxtDescmovielist;
        @BindView(R.id.txt_datemoviefavlist)
        TextView mTxtDatemovielist;
        @BindView(R.id.btn_delete)
        ImageView mBtnDelete;

        FavoriteMovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(MovieEntity movie) {
            mTxtTitlemovielist.setText(movie.getName());
            mTxtTitlebackground.setText(movie.getOriginalName());
            if (movie.getGenres() == null || movie.getGenres().equals("")) {
                mTxtGenremovielist.setText(activity.getResources().getString(R.string.txt_no_genre));
            } else {
                mTxtGenremovielist.setText(movie.getGenres());
            }

            GlideApp.with(itemView.getContext())
                    .load(BuildConfig.IMAGE_URL + AppConstants.SIZE_IMAGE + movie.getPosterPath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(mIvMovielist);

            if (movie.getOverview() == null || movie.getOverview().equals("")) {
                mTxtDescmovielist.setText(activity.getString(R.string.txt_nodesc));
            } else {
                mTxtDescmovielist.setText(movie.getOverview());
            }

            DateFormat inputDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            DateFormat outputDate = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());

            try {
                Date date = inputDate.parse(movie.getReleaseDate());
                String releaseDate = outputDate.format(date);
                mTxtDatemovielist.setText(releaseDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}

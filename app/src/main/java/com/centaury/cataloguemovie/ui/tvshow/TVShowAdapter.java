package com.centaury.cataloguemovie.ui.tvshow;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.centaury.cataloguemovie.R;
import com.centaury.cataloguemovie.data.TVShowEntity;
import com.centaury.cataloguemovie.ui.detail.DetailMovieActivity;
import com.centaury.cataloguemovie.utils.GlideApp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Centaury on 10/7/2019.
 */
public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder> {

    private final Activity activity;
    private List<TVShowEntity> tvShowEntities = new ArrayList<>();

    public TVShowAdapter(Activity activity) {
        this.activity = activity;
    }

    private List<TVShowEntity> getListTVShows() {
        return tvShowEntities;
    }

    void setListTVShows(List<TVShowEntity> listTVShows) {
        if (listTVShows == null) return;
        this.tvShowEntities.clear();
        this.tvShowEntities.addAll(listTVShows);
    }

    @NonNull
    @Override
    public TVShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movielist, parent, false);
        return new TVShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TVShowViewHolder holder, int position) {
        TVShowEntity entity = tvShowEntities.get(position);
        holder.bind(entity);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(activity, DetailMovieActivity.class);
            intent.putExtra(DetailMovieActivity.EXTRA_TVSHOW, getListTVShows().get(position).getTvshowId());
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return getListTVShows().size();
    }

    class TVShowViewHolder extends RecyclerView.ViewHolder {
        final TextView mTxtTitlebackground;
        final ImageView mIvMovielist;
        final TextView mTxtTitlemovielist;
        final TextView mTxtDescmovielist;
        final TextView mTxtDatemovielist;

        TVShowViewHolder(View itemView) {
            super(itemView);
            mTxtTitlebackground = itemView.findViewById(R.id.txt_titlebackground);
            mIvMovielist = itemView.findViewById(R.id.iv_movielist);
            mTxtTitlemovielist = itemView.findViewById(R.id.txt_titlemovielist);
            mTxtDescmovielist = itemView.findViewById(R.id.txt_descmovielist);
            mTxtDatemovielist = itemView.findViewById(R.id.txt_datemovielist);
        }

        void bind(TVShowEntity entity) {
            mTxtTitlemovielist.setText(entity.getName());
            mTxtTitlebackground.setText(entity.getName());
            mTxtDescmovielist.setText(entity.getDesc());

            DateFormat inputDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            DateFormat outputDate = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
            try {
                Date date = inputDate.parse(entity.getDate());
                String releaseDate = null;
                if (date != null) {
                    releaseDate = outputDate.format(date);
                }
                mTxtDatemovielist.setText(releaseDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            GlideApp.with(itemView.getContext())
                    .load(entity.getImagePath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(mIvMovielist);
        }
    }
}

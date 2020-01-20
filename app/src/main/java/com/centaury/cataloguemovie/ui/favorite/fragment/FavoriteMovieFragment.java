package com.centaury.cataloguemovie.ui.favorite.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.centaury.cataloguemovie.R;
import com.centaury.cataloguemovie.data.local.entity.MovieEntity;
import com.centaury.cataloguemovie.di.Injectable;
import com.centaury.cataloguemovie.ui.favorite.adapter.FavoriteFragmentCallback;
import com.centaury.cataloguemovie.ui.favorite.adapter.FavoriteMovieAdapter;
import com.centaury.cataloguemovie.ui.favorite.viewmodel.FavoriteMovieViewModel;
import com.centaury.cataloguemovie.utils.Helper;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteMovieFragment extends Fragment implements FavoriteFragmentCallback, Injectable {

    @BindView(R.id.rv_favmovie)
    RecyclerView mRvFavMovie;
    @BindView(R.id.shimmer_view_container)
    ShimmerFrameLayout mShimmerViewContainer;
    @BindView(R.id.empty_state)
    LinearLayout mEmptyState;
    private Unbinder unbinder;

    @Inject
    ViewModelProvider.Factory factory;

    private FavoriteMovieViewModel favoriteMovieViewModel;

    public FavoriteMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite_movie, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            favoriteMovieViewModel = obtainViewModel(getActivity());

            FavoriteMovieAdapter favoriteMovieAdapter = new FavoriteMovieAdapter(getActivity(), this);
            favoriteMovieViewModel.getFavoriteMovie().observe(getActivity(), favoriteMovie -> {
                if (favoriteMovie != null) {
                    mShimmerViewContainer.stopShimmer();
                    mShimmerViewContainer.setVisibility(View.GONE);
                    toggleEmptyMovies(favoriteMovie.size());
                    favoriteMovieAdapter.submitList(favoriteMovie);
                    favoriteMovieAdapter.notifyDataSetChanged();
                }
            });

            mRvFavMovie.setLayoutManager(new LinearLayoutManager(getContext()));
            mRvFavMovie.setHasFixedSize(true);
            mRvFavMovie.setAdapter(favoriteMovieAdapter);
            mRvFavMovie.addItemDecoration(new Helper.TopItemDecoration(55));
        }
    }

    private void showDialogDeleteFavorite(int movieId) {
        MovieEntity movieEntity;
        try {
            movieEntity = favoriteMovieViewModel.getDetailFavMovie(movieId);

            LayoutInflater inflater = LayoutInflater.from(getContext());
            View view = inflater.inflate(R.layout.item_alert_dialog, null);

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setView(view);

            TextView title = view.findViewById(R.id.alerttitle);
            title.setText(getString(R.string.txt_title_delete_dialog));

            builder.setCancelable(false)
                    .setPositiveButton(getString(R.string.btn_delete), (dialog, which) -> {
                        favoriteMovieViewModel.deleteFavoriteMovie(movieEntity);
                        dialog.dismiss();
                        Toast.makeText(getContext(), getString(R.string.txt_remove_movie), Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton(getString(R.string.btn_cancel), (dialog, which) -> {
                        dialog.dismiss();
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    private FavoriteMovieViewModel obtainViewModel(FragmentActivity activity) {
        return ViewModelProviders.of(activity, factory).get(FavoriteMovieViewModel.class);
    }

    private void toggleEmptyMovies(int size) {
        if (size > 0) {
            mEmptyState.setVisibility(View.GONE);
            mRvFavMovie.setVisibility(View.VISIBLE);
        } else {
            mRvFavMovie.setVisibility(View.GONE);
            mEmptyState.setVisibility(View.VISIBLE);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDeleteItemClick(int movieId) {
        if (getActivity() != null) {
            showDialogDeleteFavorite(movieId);
        }
    }
}

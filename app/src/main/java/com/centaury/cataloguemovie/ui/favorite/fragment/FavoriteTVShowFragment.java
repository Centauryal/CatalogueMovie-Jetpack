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
import com.centaury.cataloguemovie.data.local.entity.TVShowEntity;
import com.centaury.cataloguemovie.di.Injectable;
import com.centaury.cataloguemovie.ui.favorite.adapter.FavoriteFragmentCallback;
import com.centaury.cataloguemovie.ui.favorite.adapter.FavoriteTVShowAdapter;
import com.centaury.cataloguemovie.ui.favorite.viewmodel.FavoriteTVShowViewModel;
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
public class FavoriteTVShowFragment extends Fragment implements FavoriteFragmentCallback, Injectable {

    @BindView(R.id.rv_favtvshow)
    RecyclerView mRvFavTvshow;
    @BindView(R.id.shimmer_view_container)
    ShimmerFrameLayout mShimmerViewContainer;
    @BindView(R.id.empty_state)
    LinearLayout mEmptyState;
    private Unbinder unbinder;

    @Inject
    ViewModelProvider.Factory factory;

    private FavoriteTVShowViewModel favoriteTVShowViewModel;

    public FavoriteTVShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite_tvshow, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            favoriteTVShowViewModel = obtainViewModel(getActivity());

            FavoriteTVShowAdapter favoriteTVShowAdapter = new FavoriteTVShowAdapter(getActivity(), this);
            favoriteTVShowViewModel.getFavoriteTVShow().observe(getActivity(), favoriteTVShow -> {
                if (favoriteTVShow != null) {
                    mShimmerViewContainer.stopShimmer();
                    mShimmerViewContainer.setVisibility(View.GONE);
                    toggleEmptyMovies(favoriteTVShow.size());
                    favoriteTVShowAdapter.submitList(favoriteTVShow);
                    favoriteTVShowAdapter.notifyDataSetChanged();
                }
            });

            mRvFavTvshow.setLayoutManager(new LinearLayoutManager(getContext()));
            mRvFavTvshow.setHasFixedSize(true);
            mRvFavTvshow.setAdapter(favoriteTVShowAdapter);
            mRvFavTvshow.addItemDecoration(new Helper.TopItemDecoration(55));
        }
    }

    private void showDialogDeleteFavorite(int movieId) {
        TVShowEntity tvShowEntity;
        try {
            tvShowEntity = favoriteTVShowViewModel.getDetailFavTVShow(movieId);

            LayoutInflater inflater = LayoutInflater.from(getContext());
            View view = inflater.inflate(R.layout.item_alert_dialog, null);

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setView(view);

            TextView title = view.findViewById(R.id.alerttitle);
            title.setText(getString(R.string.txt_title_delete_dialog));

            builder.setCancelable(false)
                    .setPositiveButton(getString(R.string.btn_delete), (dialog, which) -> {
                        favoriteTVShowViewModel.deleteFavoriteTVShow(tvShowEntity);
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
    private FavoriteTVShowViewModel obtainViewModel(FragmentActivity activity) {
        return ViewModelProviders.of(activity, factory).get(FavoriteTVShowViewModel.class);
    }

    private void toggleEmptyMovies(int size) {
        if (size > 0) {
            mEmptyState.setVisibility(View.GONE);
            mRvFavTvshow.setVisibility(View.VISIBLE);
        } else {
            mRvFavTvshow.setVisibility(View.GONE);
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

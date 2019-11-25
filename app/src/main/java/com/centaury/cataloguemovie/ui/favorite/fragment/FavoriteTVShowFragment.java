package com.centaury.cataloguemovie.ui.favorite.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.centaury.cataloguemovie.R;
import com.centaury.cataloguemovie.ViewModelProviderFactory;
import com.centaury.cataloguemovie.ui.favorite.adapter.FavoriteTVShowAdapter;
import com.centaury.cataloguemovie.ui.favorite.viewmodel.FavoriteTVShowViewModel;
import com.centaury.cataloguemovie.utils.Helper;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteTVShowFragment extends Fragment {

    @BindView(R.id.rv_favtvshow)
    RecyclerView mRvFavTvshow;
    @BindView(R.id.shimmer_view_container)
    ShimmerFrameLayout mShimmerViewContainer;
    @BindView(R.id.empty_state)
    LinearLayout mEmptyState;
    private Unbinder unbinder;

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
            FavoriteTVShowViewModel favoriteTVShowViewModel = obtainViewModel(getActivity());
            String language = Locale.getDefault().toLanguageTag();

            FavoriteTVShowAdapter favoriteTVShowAdapter = new FavoriteTVShowAdapter(getActivity());
            favoriteTVShowViewModel.getFavoriteTVShow().observe(getActivity(), favoriteTVShow -> {
                if (favoriteTVShow != null) {
                    switch (favoriteTVShow.status) {
                        case LOADING:
                            mShimmerViewContainer.startShimmer();
                            break;
                        case SUCCESS:
                            mShimmerViewContainer.stopShimmer();
                            mShimmerViewContainer.setVisibility(View.GONE);
                            toggleEmptyMovies(favoriteTVShow.data.size());
                            favoriteTVShowAdapter.setFavoriteTVShowList(favoriteTVShow.data);
                            favoriteTVShowAdapter.notifyDataSetChanged();
                            break;
                        case ERROR:
                            mShimmerViewContainer.stopShimmer();
                            mShimmerViewContainer.setVisibility(View.GONE);
                            Toast.makeText(getContext(), getString(R.string.txt_error), Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
            favoriteTVShowViewModel.getGenreTVShow(language).observe(getActivity(), genresItemList -> {
                if (genresItemList != null) {
                    switch (genresItemList.status) {
                        case LOADING:
                            break;
                        case SUCCESS:
                            favoriteTVShowAdapter.setListGenreTVShow(genresItemList.data);
                            favoriteTVShowAdapter.notifyDataSetChanged();
                            break;
                        case ERROR:
                            Toast.makeText(getContext(), getString(R.string.txt_error), Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });

            mRvFavTvshow.setLayoutManager(new LinearLayoutManager(getContext()));
            mRvFavTvshow.setHasFixedSize(true);
            mRvFavTvshow.setAdapter(favoriteTVShowAdapter);
            mRvFavTvshow.addItemDecoration(new Helper.TopItemDecoration(55));
        }
    }

    @NonNull
    private static FavoriteTVShowViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelProviderFactory factory = ViewModelProviderFactory.getInstance(activity.getApplication());
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
}

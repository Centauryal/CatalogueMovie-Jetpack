package com.centaury.cataloguemovie.ui.movie;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.centaury.cataloguemovie.R;
import com.centaury.cataloguemovie.di.Injectable;
import com.centaury.cataloguemovie.utils.CommonUtils;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements Injectable {

    @BindView(R.id.rv_movie)
    RecyclerView mRvMovie;
    @BindView(R.id.shimmer_view_container)
    ShimmerFrameLayout mShimmerViewContainer;
    @BindView(R.id.txt_load_more)
    TextView mTxtLoadMore;
    @Inject
    ViewModelProvider.Factory factory;
    private Unbinder unbinder;

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            MovieViewModel movieViewModel = obtainViewModel(getActivity());
            String language = Locale.getDefault().toLanguageTag();

            MovieAdapter movieAdapter = new MovieAdapter(getActivity());
            movieViewModel.getMovies().observe(this, movieResultsItems -> {
                movieAdapter.submitList(movieResultsItems);
                movieAdapter.notifyDataSetChanged();
            });

            movieViewModel.getGenreMovie(language).observe(this, genresItemList -> {
                movieAdapter.setListGenreMovie(genresItemList);
                movieAdapter.notifyDataSetChanged();
            });

            movieViewModel.getLoadingState().observe(this, loadingState -> {
                if (loadingState) {
                    mShimmerViewContainer.startShimmer();
                    mShimmerViewContainer.setVisibility(View.VISIBLE);
                } else {
                    mShimmerViewContainer.stopShimmer();
                    mShimmerViewContainer.setVisibility(View.GONE);
                }
            });

            movieViewModel.getLoadMoreLoadingState().observe(this, loadMore -> {
                if (loadMore) {
                    mTxtLoadMore.setVisibility(View.VISIBLE);
                } else {
                    mTxtLoadMore.setVisibility(View.GONE);
                }
            });

            mRvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
            mRvMovie.setHasFixedSize(true);
            mRvMovie.setAdapter(movieAdapter);
            mRvMovie.addItemDecoration(new CommonUtils.TopItemDecoration(55));
        }
    }

    @NonNull
    private MovieViewModel obtainViewModel(FragmentActivity activity) {
        return ViewModelProviders.of(activity, factory).get(MovieViewModel.class);
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

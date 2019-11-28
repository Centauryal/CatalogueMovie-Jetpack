package com.centaury.cataloguemovie.ui.tvshow;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.centaury.cataloguemovie.R;
import com.centaury.cataloguemovie.ViewModelProviderFactory;
import com.centaury.cataloguemovie.utils.Helper;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class TVShowFragment extends Fragment {

    @BindView(R.id.rv_tvshow)
    RecyclerView mRvTvshow;
    @BindView(R.id.shimmer_view_container)
    ShimmerFrameLayout mShimmerViewContainer;
    @BindView(R.id.txt_loadmore)
    TextView mTxtLoadMore;
    private Unbinder unbinder;

    public TVShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tvshow, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            TVShowViewModel tvShowViewModel = obtainViewModel(getActivity());
            String language = Locale.getDefault().toLanguageTag();

            TVShowAdapter tvShowAdapter = new TVShowAdapter(getActivity());
            tvShowViewModel.getTVShows().observe(this, tvShowResultsItems -> {
                tvShowAdapter.submitList(tvShowResultsItems);
                tvShowAdapter.notifyDataSetChanged();
            });
            tvShowViewModel.getGenreTVShow(language).observe(this, genresItemList -> {
                tvShowAdapter.setListGenreTVShow(genresItemList);
                tvShowAdapter.notifyDataSetChanged();
            });

            tvShowViewModel.getLoadingState().observe(this, loadingState -> {
                if (loadingState) {
                    mShimmerViewContainer.startShimmer();
                    mShimmerViewContainer.setVisibility(View.VISIBLE);
                } else {
                    mShimmerViewContainer.stopShimmer();
                    mShimmerViewContainer.setVisibility(View.GONE);
                }
            });

            tvShowViewModel.getLoadMoreLoadingState().observe(this, loadMore -> {
                if (loadMore) {
                    mTxtLoadMore.setVisibility(View.VISIBLE);
                } else {
                    mTxtLoadMore.setVisibility(View.GONE);
                }
            });

            mRvTvshow.setLayoutManager(new LinearLayoutManager(getContext()));
            mRvTvshow.setHasFixedSize(true);
            mRvTvshow.setAdapter(tvShowAdapter);
            mRvTvshow.addItemDecoration(new Helper.TopItemDecoration(55));
        }
    }

    @NonNull
    private static TVShowViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelProviderFactory factory = ViewModelProviderFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(TVShowViewModel.class);
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

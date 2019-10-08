package com.centaury.cataloguemovie.ui.tvshow;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.centaury.cataloguemovie.R;
import com.centaury.cataloguemovie.data.TVShowEntity;
import com.centaury.cataloguemovie.utils.Helper;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TVShowFragment extends Fragment {

    private RecyclerView rvTVShow;
    private ShimmerFrameLayout shimmerFrameLayout;

    public TVShowFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new TVShowFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTVShow = view.findViewById(R.id.rv_tvshow);
        shimmerFrameLayout = view.findViewById(R.id.shimmer_view_container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            TVShowViewModel tvShowViewModel = ViewModelProviders.of(this).get(TVShowViewModel.class);
            List<TVShowEntity> tvShowEntities = tvShowViewModel.getTVShows();

            TVShowAdapter tvShowAdapter = new TVShowAdapter(getActivity());
            shimmerFrameLayout.stopShimmer();
            shimmerFrameLayout.setVisibility(View.GONE);
            tvShowAdapter.setListTVShows(tvShowEntities);

            rvTVShow.setLayoutManager(new LinearLayoutManager(getContext()));
            rvTVShow.setHasFixedSize(true);
            rvTVShow.setAdapter(tvShowAdapter);
            rvTVShow.addItemDecoration(new Helper.TopItemDecoration(55));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        shimmerFrameLayout.startShimmer();
    }

    @Override
    public void onPause() {
        shimmerFrameLayout.stopShimmer();
        super.onPause();
    }
}

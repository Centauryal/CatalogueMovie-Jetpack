package com.centaury.cataloguemovie.ui.main;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.centaury.cataloguemovie.R;
import com.centaury.cataloguemovie.ui.movie.MovieFragment;
import com.centaury.cataloguemovie.ui.tvshow.TVShowFragment;
import com.centaury.cataloguemovie.utils.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = getWindow();
            window.setStatusBarColor(getColor(R.color.colorPrimaryDark));
        }

        TabLayout tabLayout = findViewById(R.id.tabs);
        ViewPager viewPager = findViewById(R.id.view_pager);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(
                getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerAdapter.addFragment(new MovieFragment(), getString(R.string.title_movie));
        viewPagerAdapter.addFragment(new TVShowFragment(), getString(R.string.title_tv_show));
        viewPager.setAdapter(viewPagerAdapter);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
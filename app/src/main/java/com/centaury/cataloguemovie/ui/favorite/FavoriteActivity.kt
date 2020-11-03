package com.centaury.cataloguemovie.ui.favorite

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.viewpager2.widget.ViewPager2
import com.centaury.cataloguemovie.R
import com.centaury.cataloguemovie.databinding.ActivityFavoriteBinding
import com.centaury.cataloguemovie.ui.favorite.fragment.FavoriteMovieFragment
import com.centaury.cataloguemovie.ui.favorite.fragment.FavoriteTVShowFragment
import com.centaury.cataloguemovie.utils.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class FavoriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = setContentView<ActivityFavoriteBinding>(this, R.layout.activity_favorite)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
            setDisplayShowTitleEnabled(false)
        }

        setupViewPager(binding.favViewPager)
        TabLayoutMediator(binding.favTabs, binding.favViewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.title_movie)
                1 -> tab.text = getString(R.string.title_tv_show)
            }
        }.attach()
    }

    private fun setupViewPager(viewPager: ViewPager2) {
        val viewPagerAdapter = ViewPagerAdapter(this)
        viewPager.adapter = viewPagerAdapter

        viewPagerAdapter.addFragment(FavoriteMovieFragment())
        viewPagerAdapter.addFragment(FavoriteTVShowFragment())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
package com.centaury.cataloguemovie.ui.favorite

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.centaury.cataloguemovie.R
import com.centaury.cataloguemovie.ui.favorite.fragment.FavoriteMovieFragment
import com.centaury.cataloguemovie.ui.favorite.fragment.FavoriteTVShowFragment
import com.centaury.cataloguemovie.utils.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        window.statusBarColor = getColor(R.color.colorPrimaryVariant)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        setupViewPager(fav_view_pager)
        fav_tabs.setupWithViewPager(fav_view_pager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val viewPagerAdapter = ViewPagerAdapter(
            supportFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        viewPagerAdapter.addFragment(FavoriteMovieFragment(), getString(R.string.title_movie))
        viewPagerAdapter.addFragment(FavoriteTVShowFragment(), getString(R.string.title_tv_show))
        viewPager.adapter = viewPagerAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
package com.centaury.cataloguemovie.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.centaury.cataloguemovie.R
import com.centaury.cataloguemovie.ui.favorite.FavoriteActivity
import com.centaury.cataloguemovie.ui.movie.MovieFragment
import com.centaury.cataloguemovie.ui.tvshow.TVShowFragment
import com.centaury.cataloguemovie.utils.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = getColor(R.color.colorPrimaryVariant)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        setupViewPager(view_pager)
        TabLayoutMediator(tabs, view_pager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.title_movie)
                1 -> tab.text = getString(R.string.title_tv_show)
            }
        }.attach()
    }

    private fun setupViewPager(viewPager: ViewPager2) {
        val viewPagerAdapter = ViewPagerAdapter(this)
        viewPager.adapter = viewPagerAdapter

        viewPagerAdapter.addFragment(MovieFragment())
        viewPagerAdapter.addFragment(TVShowFragment())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            startActivity(Intent(this, FavoriteActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        finish()
    }
}
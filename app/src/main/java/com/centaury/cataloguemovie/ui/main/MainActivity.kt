package com.centaury.cataloguemovie.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.viewpager2.widget.ViewPager2
import com.centaury.cataloguemovie.R
import com.centaury.cataloguemovie.databinding.ActivityMainBinding
import com.centaury.cataloguemovie.ui.movie.MovieFragment
import com.centaury.cataloguemovie.ui.tvshow.TVShowFragment
import com.centaury.cataloguemovie.utils.ViewPagerAdapter
import com.centaury.cataloguemovie.utils.checkConnection
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView(this, R.layout.activity_main)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        checkConnection(this)

        setupViewPager(binding.viewPager)
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
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
        when (item.itemId) {
            R.id.action_search -> {
                when (binding.tabs.selectedTabPosition) {
                    0 -> startActivity(
                        Intent(
                            this,
                            Class.forName("com.centaury.cataloguemovie.search.SearchActivity")
                        ).apply {
                            putExtra(SEARCH_MOVIE, 1)
                        }
                    )
                    1 -> startActivity(
                        Intent(
                            this,
                            Class.forName("com.centaury.cataloguemovie.search.SearchActivity")
                        ).apply {
                            putExtra(SEARCH_MOVIE, 2)
                        }
                    )
                }
            }
            R.id.action_favorite -> startActivity(
                Intent(
                    this,
                    Class.forName("com.centaury.cataloguemovie.favorite.FavoriteActivity")
                )
            )
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        checkConnection(this)
    }

    override fun onBackPressed() {
        finish()
    }

    companion object {
        const val SEARCH_MOVIE = "search_movie"
    }
}
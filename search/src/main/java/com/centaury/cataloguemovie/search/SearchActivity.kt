package com.centaury.cataloguemovie.search

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.centaury.cataloguemovie.MovieCatalogueApp
import com.centaury.cataloguemovie.R
import com.centaury.cataloguemovie.search.R.*
import com.centaury.cataloguemovie.search.adapter.SearchAdapter
import com.centaury.cataloguemovie.search.adapter.SearchCallback
import com.centaury.cataloguemovie.search.databinding.ActivitySearchBinding
import com.centaury.cataloguemovie.search.di.DaggerSearchComponent
import com.centaury.cataloguemovie.ui.detail.DetailMovieActivity
import com.centaury.cataloguemovie.ui.main.MainActivity
import com.centaury.cataloguemovie.utils.*
import com.centaury.domain.model.Genre
import com.centaury.domain.model.Search
import java.util.*
import javax.inject.Inject

class SearchActivity : AppCompatActivity(), SearchCallback {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val searchViewModel: SearchViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: ActivitySearchBinding

    private var searchData = arrayListOf<Search>()
    private var genreData = arrayListOf<Genre>()
    private val searchAdapter: SearchAdapter by lazy {
        SearchAdapter(searchData, genreData, this)
    }
    private var searchId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        initInjector()
        super.onCreate(savedInstanceState)
        binding = setContentView(this, layout.activity_search)

        checkConnection(this)
        initView(binding)
    }

    private fun initView(binding: ActivitySearchBinding) {
        setSupportActionBar(binding.materialToolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow)
            setDisplayShowTitleEnabled(false)
        }

        searchId = intent.getIntExtra(MainActivity.SEARCH_MOVIE, 0)

        with(binding.rvSearch) {
            hasFixedSize()
            layoutManager = LinearLayoutManager(this@SearchActivity)
            addItemDecoration(CommonUtils.TopItemDecoration(55))
            adapter = searchAdapter
        }

        initObserver(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initObserver(binding: ActivitySearchBinding) {
        searchViewModel.state.observe(this, { state ->
            when (state) {
                is LoaderState.ShowLoading -> {
                    binding.shimmerViewContainer.startShimmer()
                    binding.hasSearch = true
                }
                is LoaderState.HideLoading -> {
                    binding.shimmerViewContainer.stopShimmer()
                    binding.hasSearch = false
                }
            }
        })
        searchViewModel.resultSearchMovies.observe(this, { resultSearchMovie ->
            searchData.clear()
            searchData.addAll(resultSearchMovie)
            CommonUtils.toggleEmptyState(
                resultSearchMovie.size,
                binding.emptyState,
                binding.rvSearch
            )
            searchAdapter.notifyDataSetChanged()
        })
        searchViewModel.errorSearchMovies.observe(this, { errorSearchMovie ->
            timberE(errorSearchMovie)
        })
        searchViewModel.resultSearchTVShows.observe(this, { resultSearchTVShow ->
            searchData.clear()
            searchData.addAll(resultSearchTVShow)
            CommonUtils.toggleEmptyState(
                resultSearchTVShow.size,
                binding.emptyState,
                binding.rvSearch
            )
            searchAdapter.notifyDataSetChanged()
        })
        searchViewModel.errorSearchTVShows.observe(this, { errorSearchTVShow ->
            timberE(errorSearchTVShow)
        })
        searchViewModel.resultGenreMovie.observe(this, { resultGenreMovie ->
            genreData.clear()
            genreData.addAll(resultGenreMovie)
            searchAdapter.notifyDataSetChanged()
        })
        searchViewModel.errorGenreMovie.observe(this, { errorGenreMovie ->
            timberE(errorGenreMovie)
        })
        searchViewModel.resultGenreTVShow.observe(this, { resultGenreTVShow ->
            genreData.clear()
            genreData.addAll(resultGenreTVShow)
            searchAdapter.notifyDataSetChanged()
        })
        searchViewModel.errorGenreTVShow.observe(this, { errorGenreTVShow ->
            timberE(errorGenreTVShow)
        })
    }

    private fun initInjector() {
        DaggerSearchComponent.builder()
            .appComponent((application as MovieCatalogueApp).appComponent)
            .build()
            .inject(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.centaury.cataloguemovie.search.R.menu.menu_search, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchManager.let {
            val searchView = menu?.findItem(id.action_searchView)?.actionView as SearchView
            searchView.apply {
                setSearchableInfo(it.getSearchableInfo(componentName))
                maxWidth = Integer.MAX_VALUE
                queryHint = getString(R.string.txt_movie_hint)
                isIconified = false

                val editSearch =
                    this.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
                editSearch.apply {
                    setTextColor(Color.WHITE)
                    setHintTextColor(ContextCompat.getColor(this@SearchActivity, R.color.grey_20))
                }

                val imageSearch =
                    this.findViewById<ImageView>(androidx.appcompat.R.id.search_button)
                imageSearch.apply {
                    setImageResource(R.drawable.ic_search)
                }

                val imageCloseSearch =
                    this.findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn)
                imageCloseSearch.apply {
                    setImageResource(R.drawable.ic_close)
                }

                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        val textSearch = query?.lowercase(Locale.getDefault())
                        when (searchId) {
                            1 -> {
                                searchViewModel.getAllSearchMoviesContract(textSearch.toString())
                                searchViewModel.getGenreMovieContract()
                            }
                            2 -> {
                                searchViewModel.getAllSearchTVShowsContract(textSearch.toString())
                                searchViewModel.getGenreTVShowContract()
                            }
                        }
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        val textSearch = newText?.lowercase(Locale.getDefault())
                        when (searchId) {
                            1 -> {
                                searchViewModel.getAllSearchMoviesContract(textSearch.toString())
                                searchViewModel.getGenreMovieContract()
                            }
                            2 -> {
                                searchViewModel.getAllSearchTVShowsContract(textSearch.toString())
                                searchViewModel.getGenreTVShowContract()
                            }
                        }
                        return false
                    }
                })

                setOnCloseListener {
                    onBackPressed()
                    false
                }
            }
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        checkConnection(this)
        binding.shimmerViewContainer.startShimmer()
    }

    override fun onPause() {
        binding.shimmerViewContainer.stopShimmer()
        super.onPause()
    }

    private fun showTransitionImage(id: Int, image: ImageView, title: String) {
        when (searchId) {
            1 -> startActivity(Intent(this, DetailMovieActivity::class.java).apply {
                putExtra(DetailMovieActivity.DETAIL_EXTRA_MOVIE, id)
            }, CommonUtils.pairOptionsTransition(this, image, title).toBundle())
            2 -> startActivity(Intent(this, DetailMovieActivity::class.java).apply {
                putExtra(DetailMovieActivity.DETAIL_EXTRA_TV_SHOW, id)
            }, CommonUtils.pairOptionsTransition(this, image, title).toBundle())
        }

    }

    override fun onItemClick(searchId: Int, image: ImageView, title: String) {
        showTransitionImage(searchId, image, title)
    }
}
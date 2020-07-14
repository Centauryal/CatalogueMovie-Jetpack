package com.centaury.cataloguemovie.ui.favorite.adapter

/**
 * Created by Centaury on 11/23/2019.
 */
/*
class FavoriteMovieAdapter(
    private val activity: Activity,
    private val callback: FavoriteFragmentCallback
) :
    PagedListAdapter<MovieEntity?, FavoriteMovieViewHolder?>(DIFF_CALLBACK) {
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite_movielist, parent, false)
        return FavoriteMovieViewHolder(view)
    }

    fun onBindViewHolder(holder: FavoriteMovieViewHolder, position: Int) {
        val movie: MovieEntity = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
        holder.itemView.setOnClickListener { v: View? ->
            val intent = Intent(activity, DetailFavoriteMovieActivity::class.java)
            intent.putExtra(AppConstants.DETAIL_EXTRA_FAV_MOVIE, movie.movieId)
            activity.startActivity(intent)
        }
        holder.mBtnDelete!!.setOnClickListener { v: View? ->
            callback.onDeleteItemClick(
                movie.movieId
            )
        }
    }

    inner class FavoriteMovieViewHolder(itemView: View?) :
        RecyclerView.ViewHolder(itemView!!) {

        fun bind(movie: MovieEntity) {
            mTxtTitlemovielist!!.text = movie.name
            mTxtTitlebackground!!.text = movie.originalName
            if (movie.genres == null || movie.genres == "") {
                mTxtGenremovielist!!.text = activity.resources.getString(R.string.txt_no_genre)
            } else {
                mTxtGenremovielist!!.text = movie.genres
            }
            GlideApp.with(itemView.context)
                .load(BuildConfig.IMAGE_URL + AppConstants.SIZE_IMAGE + movie.posterPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
                )
                .into(mIvMovielist)
            if (movie.overview == null || movie.overview == "") {
                mTxtDescmovielist!!.text = activity.getString(R.string.txt_no_desc)
            } else {
                mTxtDescmovielist!!.text = movie.overview
            }
            val inputDate: DateFormat =
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputDate: DateFormat =
                SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            try {
                val date = inputDate.parse(movie.releaseDate)
                val releaseDate = outputDate.format(date)
                mTxtDatemovielist!!.text = releaseDate
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }

        init {
            ButterKnife.bind(this, itemView)
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<MovieEntity> =
            object : DiffUtil.ItemCallback<MovieEntity>() {
                override fun areItemsTheSame(
                    oldItem: MovieEntity,
                    newItem: MovieEntity
                ): Boolean {
                    return oldItem.movieId == newItem.movieId
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldItem: MovieEntity,
                    newItem: MovieEntity
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }

}*/

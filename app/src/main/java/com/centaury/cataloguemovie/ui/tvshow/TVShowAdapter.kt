package com.centaury.cataloguemovie.ui.tvshow

/**
 * Created by Centaury on 10/7/2019.
 */
/*
class TVShowAdapter(private val activity: Activity) :
    PagedListAdapter<TVShowResultsItem?, TVShowViewHolder?>(DIFF_CALLBACK) {
    private val genresItemList: MutableList<GenresItem> =
        ArrayList<GenresItem>()

    fun setListGenreTVShow(genreTVShow: List<GenresItem?>?) {
        if (genreTVShow == null) return
        genresItemList.clear()
        genresItemList.addAll(genreTVShow)
    }

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_list, parent, false)
        return TVShowViewHolder(view)
    }

    fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        val tvshow: TVShowResultsItem = getItem(position)
        if (tvshow != null) {
            holder.bind(tvshow)
        }
        holder.itemView.setOnClickListener { v: View? ->
            val intent = Intent(activity, DetailMovieActivity::class.java)
            intent.putExtra(AppConstants.DETAIL_EXTRA_TV_SHOW, tvshow.getId())
            activity.startActivity(intent)
        }
    }

    inner class TVShowViewHolder(itemView: View?) :
        RecyclerView.ViewHolder(itemView!!) {
        @JvmField
        @BindView(R.id.txt_title_background)
        var mTxtTitlebackground: TextView? = null

        @JvmField
        @BindView(R.id.iv_movie_list)
        var mIvMovielist: ImageView? = null

        @JvmField
        @BindView(R.id.txt_genre_movie_list)
        var mTxtGenremovielist: TextView? = null

        @JvmField
        @BindView(R.id.txt_title_movie_list)
        var mTxtTitlemovielist: TextView? = null

        @JvmField
        @BindView(R.id.txt_desc_movie_list)
        var mTxtDescmovielist: TextView? = null

        @JvmField
        @BindView(R.id.txt_date_movie_list)
        var mTxtDatemovielist: TextView? = null
        fun bind(tvshow: TVShowResultsItem) {
            mTxtTitlemovielist.setText(tvshow.getName())
            mTxtTitlebackground.setText(tvshow.getOriginalName())
            if (tvshow.getOverview() == null || tvshow.getOverview().equals("")) {
                mTxtDescmovielist!!.text = activity.getString(R.string.txt_no_desc)
            } else {
                mTxtDescmovielist.setText(tvshow.getOverview())
            }
            if (tvshow.getGenreIds().size() === 0) {
                mTxtGenremovielist!!.text = activity.getString(R.string.txt_no_genre)
            } else {
                mTxtGenremovielist!!.text = getGenres(tvshow.getGenreIds())
            }
            val inputDate: DateFormat =
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputDate: DateFormat =
                SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            try {
                val date = inputDate.parse(tvshow.getFirstAirDate())
                var releaseDate: String? = null
                if (date != null) {
                    releaseDate = outputDate.format(date)
                }
                mTxtDatemovielist!!.text = releaseDate
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            GlideApp.with(itemView.context)
                .load(BuildConfig.IMAGE_URL + AppConstants.SIZE_IMAGE + tvshow.getPosterPath())
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
                )
                .into(mIvMovielist)
        }

        private fun getGenres(genreList: List<Int>): String {
            val genreMovies: MutableList<String?> =
                ArrayList()
            try {
                if (genreList.size == 1) {
                    for (genreId in genreList) {
                        for (genresItem in genresItemList) {
                            if (genresItem.getId() === genreId) {
                                genreMovies.add(genresItem.getName())
                            }
                        }
                    }
                } else {
                    val integers = genreList.subList(0, 2)
                    for (genreId in integers) {
                        for (genresItem in genresItemList) {
                            if (genresItem.getId() === genreId) {
                                genreMovies.add(genresItem.getName())
                            }
                        }
                    }
                }
            } catch (e: IndexOutOfBoundsException) {
                println("Exception thrown : $e")
            } catch (e: IllegalArgumentException) {
                println("Exception thrown : $e")
            }
            return TextUtils.join(", ", genreMovies)
        }

        init {
            ButterKnife.bind(this, itemView)
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<TVShowResultsItem> =
            object : DiffUtil.ItemCallback<TVShowResultsItem>() {
                override fun areItemsTheSame(
                    oldItem: TVShowResultsItem,
                    newItem: TVShowResultsItem
                ): Boolean {
                    return oldItem.getId() === newItem.getId()
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldItem: TVShowResultsItem,
                    newItem: TVShowResultsItem
                ): Boolean {
                    return oldItem.equals(newItem)
                }
            }
    }

}*/

package com.centaury.cataloguemovie.ui.favorite.adapter

/**
 * Created by Centaury on 11/23/2019.
 */
/*
class FavoriteTVShowAdapter(
    private val activity: Activity,
    private val callback: FavoriteFragmentCallback
) :
    PagedListAdapter<TVShowEntity?, FavoriteTVShowViewHolder?>(DIFF_CALLBACK) {
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTVShowViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite_movielist, parent, false)
        return FavoriteTVShowViewHolder(view)
    }

    fun onBindViewHolder(holder: FavoriteTVShowViewHolder, position: Int) {
        val tvshow: TVShowEntity = getItem(position)
        if (tvshow != null) {
            holder.bind(tvshow)
        }
        holder.itemView.setOnClickListener { v: View? ->
            val intent = Intent(activity, DetailFavoriteMovieActivity::class.java)
            intent.putExtra(AppConstants.DETAIL_EXTRA_FAV_TV_SHOW, tvshow.tvshowId)
            activity.startActivity(intent)
        }
        holder.mBtnDelete!!.setOnClickListener { v: View? ->
            callback.onDeleteItemClick(
                tvshow.tvshowId
            )
        }
    }

    inner class FavoriteTVShowViewHolder(itemView: View?) :
        RecyclerView.ViewHolder(itemView!!) {

        fun bind(tvshow: TVShowEntity) {
            mTxtTitlemovielist!!.text = tvshow.name
            mTxtTitlebackground!!.text = tvshow.originalName
            if (tvshow.genres == null || tvshow.genres == "") {
                mTxtGenremovielist!!.text = activity.resources.getString(R.string.txt_no_genre)
            } else {
                mTxtGenremovielist!!.text = tvshow.genres
            }
            GlideApp.with(itemView.context)
                .load(BuildConfig.IMAGE_URL + AppConstants.SIZE_IMAGE + tvshow.posterPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
                )
                .into(mIvMovielist)
            if (tvshow.overview == null || tvshow.overview == "") {
                mTxtDescmovielist!!.text = activity.getString(R.string.txt_no_desc)
            } else {
                mTxtDescmovielist!!.text = tvshow.overview
            }
            val inputDate: DateFormat =
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputDate: DateFormat =
                SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            try {
                val date = inputDate.parse(tvshow.releaseDate)
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
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<TVShowEntity> =
            object : DiffUtil.ItemCallback<TVShowEntity>() {
                override fun areItemsTheSame(
                    oldItem: TVShowEntity,
                    newItem: TVShowEntity
                ): Boolean {
                    return oldItem.tvshowId == newItem.tvshowId
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldItem: TVShowEntity,
                    newItem: TVShowEntity
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }

}*/

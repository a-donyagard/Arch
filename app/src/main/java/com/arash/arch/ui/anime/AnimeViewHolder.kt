package com.arash.arch.ui.anime

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arash.arch.R
import com.arash.arch.databinding.ItemAnimeBinding
import com.arash.arch.util.extension.inflate

abstract class AnimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: AnimeDataItem)

    class AnimeViewHolderItem(
        parent: ViewGroup,
        private val listener: (item: AnimeDataItem.AnimeItem) -> Unit
    ) :
        AnimeViewHolder(parent.inflate(R.layout.item_anime)) {
        private val binding = ItemAnimeBinding.bind(itemView)
        private var item: AnimeDataItem.AnimeItem? = null

        init {
            binding.root.setOnClickListener {
                item?.let { listener.invoke(it) }
            }
        }

        override fun bind(item: AnimeDataItem) {
            this.item = item as AnimeDataItem.AnimeItem
            binding.animeItem = item
        }
    }

    class LoadingItem(parent: ViewGroup) :
        AnimeViewHolder(parent.inflate(R.layout.item_loading)) {
        override fun bind(item: AnimeDataItem) {
            // no-op
        }
    }
}

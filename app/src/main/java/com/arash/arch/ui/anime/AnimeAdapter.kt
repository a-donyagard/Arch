package com.arash.arch.ui.anime

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.arash.arch.R
import com.arash.arch.util.SafeListAdapter

class AnimeAdapter(private val listener: (item: AnimeDataItem.AnimeItem) -> Unit) :
    SafeListAdapter<AnimeDataItem, AnimeViewHolder>(AnimeDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        return when (viewType) {
            AnimeDataItem.ANIME_ITEM -> AnimeViewHolder.AnimeViewHolderItem(parent, listener)
            AnimeDataItem.LOADING_ITEM -> AnimeViewHolder.LoadingItem(parent)
            else -> throw TypeCastException(
                String.format(
                    parent.context.getString(R.string.view_type_not_handled_error),
                    viewType
                )
            )
        }
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }
}

class AnimeDiffCallback : DiffUtil.ItemCallback<AnimeDataItem>() {
    override fun areItemsTheSame(oldItem: AnimeDataItem, newItem: AnimeDataItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AnimeDataItem, newItem: AnimeDataItem): Boolean {
        return oldItem == newItem
    }

}

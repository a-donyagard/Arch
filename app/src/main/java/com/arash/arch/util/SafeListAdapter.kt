package com.arash.arch.util

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * Fix [ListAdapter] bug by move given list to new list of items
 */
abstract class SafeListAdapter<T, V : RecyclerView.ViewHolder>(callback: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, V>(callback) {

    override fun submitList(list: List<T?>?) {
        super.submitList(list?.toList())
    }
}
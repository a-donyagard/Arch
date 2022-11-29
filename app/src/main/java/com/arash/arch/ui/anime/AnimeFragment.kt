package com.arash.arch.ui.anime

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.arash.arch.R
import com.arash.arch.databinding.FragmentAnimeBinding
import com.arash.arch.ui.base.BaseFragment
import com.arash.arch.util.LoadMoreScrollListener
import com.arash.arch.util.extension.showMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimeFragment : BaseFragment<AnimeViewModel, FragmentAnimeBinding>() {
    override val viewModel: AnimeViewModel by viewModels()
    override val layoutId: Int = R.layout.fragment_anime
    private var adapter: AnimeAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.manualInit()
    }

    override fun onViewInitialized(binding: FragmentAnimeBinding) {
        super.onViewInitialized(binding)
        init()
        listeners()
        observers()
    }

    private fun init() {
        adapter = AnimeAdapter {
            requireView().showMessage(
                String.format(getString(R.string.item_click_format), it.anime.attributes.title)
            )
        }
        binding.adapter = adapter
    }

    private fun listeners() {
        binding.animeRecycler.addOnScrollListener(LoadMoreScrollListener {
            viewModel.loadMore()
        })
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun observers() {
        viewModel.animeItemsFlow.collectLatestLifecycleFlow {
            adapter?.submitList(it)
        }
    }
}
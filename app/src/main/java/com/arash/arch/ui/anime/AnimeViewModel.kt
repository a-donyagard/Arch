package com.arash.arch.ui.anime

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import arrow.core.extensions.list.foldable.isNotEmpty
import com.arash.arch.data.model.Error
import com.arash.arch.data.model.anime.AnimeListWrapper
import com.arash.arch.data.repository.anime.AnimeRepository
import com.arash.arch.data.util.DataConstants
import com.arash.arch.ui.base.BaseViewModel
import com.arash.arch.util.providers.ErrorMessageProvider
import com.arash.arch.util.providers.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val animeRepository: AnimeRepository,
    private val errorMessageProvider: ErrorMessageProvider,
    private val resourceProvider: ResourceProvider
) : BaseViewModel() {
    private val _animeItemsLiveData = MediatorLiveData<List<AnimeDataItem>>().apply {
        addSource(animeRepository.animeList) { kitsoResponse ->
            manipulateDatabaseAnimeList(kitsoResponse)
        }
    }
    val animeItemsLiveData: LiveData<List<AnimeDataItem>> = _animeItemsLiveData
    private var isLoading = false
    private var canLoadMore = true
    private var offset = 0

    fun manualInit() {
        isLoading = true
        _loadingLiveData.value = true
        fetchAnimeList(false)
    }

    private fun fetchAnimeList(refresh: Boolean) {
        viewModelScope.launch {
            when (val either =
                animeRepository.fetchAnimeList(DataConstants.apiListSize, offset, refresh)) {
                is Either.Right -> manipulateApiAnimList(either.b)
                is Either.Left -> getAnimeListApiFail(either.a)
            }
        }
    }

    private fun manipulateApiAnimList(response: AnimeListWrapper) {
        isLoading = false
        if (response.data.size == DataConstants.apiListSize && response.links?.next.isNullOrEmpty()
                .not()
        ) {
            canLoadMore = true
            offset += DataConstants.apiListSize
        } else {
            canLoadMore = false
        }
        val animeItems = mutableListOf<AnimeDataItem>()
        animeItems.addAll(_animeItemsLiveData.value ?: emptyList())
        if (animeItems.isNotEmpty() && animeItems.last() is AnimeDataItem.LoadingItem) {
            animeItems.removeLast()
        }
        _animeItemsLiveData.value = animeItems
        _loadingLiveData.value = false
    }

    private fun manipulateDatabaseAnimeList(response: AnimeListWrapper) {
        _animeItemsLiveData.value = response.data.toAnimeDataItems(resourceProvider)
    }

    private fun getAnimeListApiFail(error: Error) {
        if (offset == 0) {
            onEmptyListApiFailure(error, errorMessageProvider)
        } else {
            onApiFailure(error, errorMessageProvider)
        }
        val animeItems = mutableListOf<AnimeDataItem>()
        animeItems.addAll(_animeItemsLiveData.value ?: emptyList())
        if (animeItems.isNotEmpty() && animeItems.last() is AnimeDataItem.LoadingItem) {
            animeItems.removeLast()
            _animeItemsLiveData.value = animeItems
        }
    }

    fun loadMore() {
        if (!canLoadMore || isLoading) {
            return
        }
        isLoading = true
        val animeItems = mutableListOf<AnimeDataItem>()
        animeItems.addAll(_animeItemsLiveData.value ?: emptyList())
        animeItems.add(AnimeDataItem.LoadingItem)
        _animeItemsLiveData.value = animeItems
        fetchAnimeList(false)
    }

    fun refresh() {
        _loadingLiveData.value = true
        isLoading = true
        canLoadMore = true
        offset = 0
        fetchAnimeList(true)
    }
}
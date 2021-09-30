package com.arash.arch.ui.anime

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import arrow.core.extensions.list.foldable.isNotEmpty
import com.arash.arch.data.model.Error
import com.arash.arch.data.model.anime.KitsoResponse
import com.arash.arch.data.repository.anime.AnimeRepository
import com.arash.arch.data.util.DataConstants
import com.arash.arch.ui.base.BaseViewModel
import com.arash.arch.util.providers.ErrorMessageProvider
import com.arash.arch.util.providers.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hami.bourse.util.livedata.NonNullLiveData
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val animeRepository: AnimeRepository,
    private val errorMessageProvider: ErrorMessageProvider,
    private val resourceProvider: ResourceProvider
) : BaseViewModel() {
    private val _animeItemsLiveData = NonNullLiveData<List<AnimeDataItem>>(emptyList())
    val animeItemsLiveData: LiveData<List<AnimeDataItem>> = _animeItemsLiveData
    private var isLoading = false
    private var canLoadMore = true
    private var offset = 0

    fun manualInit() {
        isLoading = true
        _loadingLiveData.value = true
        getAnimeList()
    }

    private fun getAnimeList() {
        viewModelScope.launch {
            when (val either = animeRepository.getAnimeList(DataConstants.apiListSize, offset)) {
                is Either.Right -> manipulateAnimeList(either.b)
                is Either.Left -> getAnimeListApiFail(either.a)
            }
        }
    }

    private fun manipulateAnimeList(response: KitsoResponse) {
        val animeItems = mutableListOf<AnimeDataItem>()
        animeItems.addAll(_animeItemsLiveData.value)
        isLoading = false
        if (response.data.size == DataConstants.apiListSize && response.links.next.isNullOrEmpty()
                .not()
        ) {
            canLoadMore = true
            offset += 10
        } else {
            canLoadMore = false
        }
        if (animeItems.isNotEmpty() && animeItems.last() is AnimeDataItem.LoadingItem) {
            animeItems.removeLast()
        }
        animeItems.addAll(response.data.toAnimeDataItems(resourceProvider))
        _animeItemsLiveData.value = animeItems
        _loadingLiveData.value = false
    }

    private fun getAnimeListApiFail(error: Error) {
        if (offset == 0) {
            onEmptyListApiFailure(error, errorMessageProvider)
        } else {
            onApiFailure(error, errorMessageProvider)
        }
    }

    fun loadMore() {
        if (!canLoadMore || isLoading) {
            return
        }
        isLoading = true
        val animeItems = mutableListOf<AnimeDataItem>()
        animeItems.addAll(_animeItemsLiveData.value)
        animeItems.add(AnimeDataItem.LoadingItem)
        _animeItemsLiveData.value = animeItems
        getAnimeList()
    }

    fun refresh() {
        _loadingLiveData.value = true
        isLoading = true
        canLoadMore = true
        offset = 0
        _animeItemsLiveData.value = emptyList()
        getAnimeList()
    }
}
package com.arash.arch.ui.anime

import androidx.lifecycle.viewModelScope
import arrow.core.Either
import arrow.core.extensions.list.foldable.isNotEmpty
import com.arash.arch.data.model.Error
import com.arash.arch.data.model.anime.AnimeListDto
import com.arash.arch.data.repository.anime.AnimeRepository
import com.arash.arch.data.util.DataConstants
import com.arash.arch.ui.base.BaseViewModel
import com.arash.arch.util.providers.ErrorMessageProvider
import com.arash.arch.util.providers.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val animeRepository: AnimeRepository,
    private val errorMessageProvider: ErrorMessageProvider,
    private val resourceProvider: ResourceProvider
) : BaseViewModel() {
    private val _animeItemsFlow = MutableStateFlow<List<AnimeDataItem>>(emptyList())
    val animeItemsFlow: Flow<List<AnimeDataItem>> = _animeItemsFlow
    private var isLoading = false
    private var canLoadMore = true
    private var offset = 0

    fun manualInit() {
        isLoading = true
        _loadingLiveData.value = true
        getAnimeItemsFromDataBase()
        fetchAnimeList(false)
    }

    private fun getAnimeItemsFromDataBase() = viewModelScope.launch {
        animeRepository.animeList
            .map {
                manipulateDatabaseAnimeList(it)
            }.collect {
                _animeItemsFlow.emit(it)
            }
    }

    private fun fetchAnimeList(refresh: Boolean) = viewModelScope.launch {
        animeRepository.fetchAnimeList(DataConstants.apiListSize, offset, refresh)
            .collect {
                when (it) {
                    is Either.Right -> manipulateApiAnimList(it.b)
                    is Either.Left -> getAnimeListApiFail(it.a)
                }
            }
    }

    private suspend fun manipulateApiAnimList(response: AnimeListDto) {
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
        animeItems.addAll(_animeItemsFlow.value)
        if (animeItems.isNotEmpty() && animeItems.last() is AnimeDataItem.LoadingItem) {
            animeItems.removeLast()
        }
        _animeItemsFlow.emit(animeItems)
        _loadingLiveData.value = false
    }

    private fun manipulateDatabaseAnimeList(response: AnimeListDto): List<AnimeDataItem.AnimeItem> {
        return response.data.toAnimeDataItems(resourceProvider)
    }

    private suspend fun getAnimeListApiFail(error: Error) {
        if (offset == 0) {
            onEmptyListApiFailure(error, errorMessageProvider)
        } else {
            onApiFailure(error, errorMessageProvider)
        }
        val animeItems = mutableListOf<AnimeDataItem>()
        animeItems.addAll(_animeItemsFlow.value)
        if (animeItems.isNotEmpty() && animeItems.last() is AnimeDataItem.LoadingItem) {
            animeItems.removeLast()
            _animeItemsFlow.emit(animeItems)
        }
    }

    fun loadMore() = viewModelScope.launch {
        if (!canLoadMore || isLoading) {
            return@launch
        }
        isLoading = true
        val animeItems = mutableListOf<AnimeDataItem>()
        animeItems.addAll(_animeItemsFlow.value)
        animeItems.add(AnimeDataItem.LoadingItem)
        _animeItemsFlow.emit(animeItems)
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
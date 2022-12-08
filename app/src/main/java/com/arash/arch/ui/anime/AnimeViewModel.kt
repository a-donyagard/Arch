package com.arash.arch.ui.anime

import androidx.lifecycle.viewModelScope
import arrow.core.Either
import arrow.core.extensions.list.foldable.isNotEmpty
import arrow.core.extensions.list.foldable.toList
import com.arash.arch.data.util.DataConstants
import com.arash.arch.domain.base.Error
import com.arash.arch.domain.model.Anime
import com.arash.arch.domain.model.ResponseWrapper
import com.arash.arch.domain.usecase.GetAnimeListFromDbUseCase
import com.arash.arch.domain.usecase.GetAnimeListUseCase
import com.arash.arch.ui.base.BaseViewModel
import com.arash.arch.util.extension.UiState
import com.arash.arch.util.extension.toUiState
import com.arash.arch.util.providers.ErrorMessageProvider
import com.arash.arch.util.providers.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val getAnimeListUseCase: GetAnimeListUseCase,
    private val getAnimeListFromDbUseCase: GetAnimeListFromDbUseCase,
    private val resourceProvider: ResourceProvider
) : BaseViewModel() {
    private val _animeItemsUiStateFlow =
        MutableStateFlow<UiState<List<AnimeDataItem>>>(UiState.None())
    val animeItemsUiStateFlow = _animeItemsUiStateFlow.asStateFlow()

    private val _animeItemsFlow = MutableStateFlow<MutableList<AnimeDataItem>>(mutableListOf())
    val animeItemsFlow: StateFlow<List<AnimeDataItem>> = _animeItemsFlow.asStateFlow()

    private val _emptyListFlow = MutableSharedFlow<Boolean>()
    val emptyListFlow = _emptyListFlow.asSharedFlow()

    private var isLoading = false
    private var canLoadMore = true
    private var offset = 0

    fun manualInit() = viewModelScope.launch {
        isLoading = true
        _animeItemsUiStateFlow.emit(UiState.Loading())
        getAnimeItemsFromDataBase()
        fetchAnimeList(false)
    }

    private fun getAnimeItemsFromDataBase() = viewModelScope.launch {
        getAnimeListFromDbUseCase()
            .map {
                it.data.toAnimeDataItems(resourceProvider)
            }.collect {
                _animeItemsFlow.emit(it.toMutableList())
            }
    }

    private suspend fun fetchAnimeList(refresh: Boolean) {
        getAnimeListUseCase(DataConstants.apiListSize, offset, refresh)
            .collect {
                manipulateApiAnimList(it)
            }
    }

    private suspend fun manipulateApiAnimList(either: Either<Error, ResponseWrapper<List<Anime>>>) {
        val uiState = either.toUiState {
            isLoading = false
            if (it.data.size == DataConstants.apiListSize && it.links?.next.isNullOrEmpty().not()) {
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
            _animeItemsFlow.value = animeItems
            animeItems.toList()
        }
        _animeItemsUiStateFlow.emit(uiState)
    }

    fun removeLoadingItem() = viewModelScope.launch {
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

    fun refresh() = viewModelScope.launch {
        _animeItemsUiStateFlow.emit(UiState.Loading())
        isLoading = true
        canLoadMore = true
        offset = 0
        fetchAnimeList(true)
    }

    fun handleEmptyList() = viewModelScope.launch {
        if (offset == 0) {
            _emptyListFlow.emit(true)
        }
    }
}
package com.arash.arch.ui

import androidx.lifecycle.viewModelScope
import com.arash.arch.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : BaseViewModel() {
    private val _loadingFlow = MutableSharedFlow<Boolean>()
    val loadingFlow = _loadingFlow.asSharedFlow()

    fun showProgressBar(visible: Boolean) = viewModelScope.launch {
        _loadingFlow.emit(visible)
    }
}
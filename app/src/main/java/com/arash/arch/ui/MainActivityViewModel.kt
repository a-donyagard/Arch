package com.arash.arch.ui

import com.arash.arch.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : BaseViewModel() {
    fun showProgressBar(visible: Boolean) {
        _loadingLiveData.value = visible
    }
}
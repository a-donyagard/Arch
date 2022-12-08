package com.arash.arch.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arash.arch.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : BaseViewModel() {
    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    fun showProgressBar(visible: Boolean) {
        _loadingLiveData.value = visible
    }
}
package com.arash.arch.ui.base

import androidx.lifecycle.*
import com.arash.arch.domain.base.Error
import com.arash.arch.util.livedata.ActivityActionLiveData
import com.arash.arch.util.livedata.FragmentActionLiveData
import com.arash.arch.util.livedata.SingleEventLiveData
import com.arash.arch.util.providers.ErrorMessageProvider

/**
 * All of ViewModels should be inherited from [BaseViewModel]
 */
abstract class BaseViewModel : ViewModel(), LifecycleObserver {
    val activityAction = ActivityActionLiveData()
    val fragmentAction = FragmentActionLiveData()

    @Suppress("PropertyName")
    protected val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    @Suppress("PropertyName")
    protected val _messageLiveData = SingleEventLiveData<String>()
    val messageLiveData: LiveData<String> = _messageLiveData

    @Suppress("PropertyName")
    protected val _emptyListLiveData = SingleEventLiveData<Boolean>()
    val emptyListLiveData: LiveData<Boolean> = _emptyListLiveData

    /**
     * We can use lifeCycle in inherited classes if we need
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart() {
    }

    protected open fun onApiFailure(error: Error, errorMessageProvider: ErrorMessageProvider) {
        _messageLiveData.value = errorMessageProvider.getErrorMessage(error)
        _loadingLiveData.value = false
    }

    protected fun onEmptyListApiFailure(error: Error, errorMessageProvider: ErrorMessageProvider) {
        if (error is Error.EmptyResponse) {
            _emptyListLiveData.value = true
            _loadingLiveData.value = false
            return
        }
        onApiFailure(error, errorMessageProvider)
    }
}

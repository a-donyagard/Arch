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

    /**
     * We can use lifeCycle in inherited classes if we need
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart() {
    }
}

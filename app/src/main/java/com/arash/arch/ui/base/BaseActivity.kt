package com.arash.arch.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import com.arash.arch.util.extension.collectLatestLifecycleFlow
import com.arash.arch.util.extension.collectLifecycleFlow
import kotlinx.coroutines.flow.Flow

/**
 * Every Activity should inherit from this base activity in order to create relevant binding class,
 * inject dependencies and handling default actions.
 * @param V A ViewModel class that inherited from [BaseViewModel], will be used as default ViewModel of activity
 * @param B A Binding class that inherited from [ViewDataBinding], will be used for creating View of this activity
 */
abstract class BaseActivity<V : BaseViewModel, B : ViewDataBinding> : AppCompatActivity(),
    BaseView<V, B> {
    lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // initialize binding
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        // set suggestViewModel as an observer to this activity lifecycle events
        lifecycle.addObserver(viewModel)
        // observe suggestViewModel uiActions in order to pass this activity as argument of uiAction
        viewModel.activityAction.observe(this) { it?.invoke(this) }
        onViewInitialized(binding)
    }

    /**
     * Used for collect stateFlow data from fragment
     */
    protected fun <T> Flow<T>.collectLatestLifecycleFlow(
        state: Lifecycle.State = Lifecycle.State.STARTED,
        action: suspend (T) -> Unit
    ) {
        this@BaseActivity.collectLatestLifecycleFlow(flow = this, state = state, action = action)
    }

    /**
     * Used for collect sharedFlow data from fragment
     */
    protected fun <T> Flow<T>.collectLifecycleFlow(
        state: Lifecycle.State = Lifecycle.State.STARTED,
        action: suspend (T) -> Unit
    ) {
        this@BaseActivity.collectLifecycleFlow(flow = this, state = state, action = action)
    }
}

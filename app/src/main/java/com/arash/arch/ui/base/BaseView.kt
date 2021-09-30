package com.arash.arch.ui.base

import androidx.databinding.ViewDataBinding

/**
 * Base interaction for every View such as activities, fragments, dialogs
 */
interface BaseView<V : BaseViewModel, B : ViewDataBinding> {

    /**
     * default ViewModel of view
     */
    val viewModel: V

    /**
     * Resource Id of main layout for view
     */
    val layoutId: Int

    /**
     * will be called after intialization of view
     *
     * @param binding refers to [binding]
     */
    fun onViewInitialized(binding: B) {}
}

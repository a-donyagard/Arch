package com.arash.arch.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.arash.arch.ui.MainActivityViewModel
import com.arash.arch.util.extension.showMessage

abstract class BaseFragment<V : BaseViewModel, B : ViewDataBinding> : Fragment(),
    BaseView<V, B> {

    private var _binding: B? = null
    protected val binding get() = _binding!!
    protected val activityViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // initialize binding
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = this

        // set suggestViewModel as an observer to this activity lifecycle events
        lifecycle.addObserver(viewModel)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // observe suggestViewModel uiActions in order to pass parent activity as argument of uiAction
        viewModel.activityAction.observe(viewLifecycleOwner) { it?.invoke(requireActivity()) }
        viewModel.fragmentAction.observe(viewLifecycleOwner) { it?.invoke(this) }
        onViewInitialized(binding)
        observers()
    }

    private fun observers() {
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            activityViewModel.showProgressBar(it)
        }
        viewModel.messageLiveData.observe(viewLifecycleOwner) {
            requireView().showMessage(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

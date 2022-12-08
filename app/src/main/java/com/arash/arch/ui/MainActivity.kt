package com.arash.arch.ui

import androidx.activity.viewModels
import com.arash.arch.R
import com.arash.arch.databinding.ActivityMainBinding
import com.arash.arch.ui.base.BaseActivity
import com.arash.arch.util.extension.changeVisibility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>() {
    override val viewModel: MainActivityViewModel by viewModels()
    override val layoutId: Int = R.layout.activity_main

    override fun onViewInitialized(binding: ActivityMainBinding) {
        super.onViewInitialized(binding)
        observers()
    }

    private fun observers() {
        viewModel.loadingFlow.collectLifecycleFlow {
            binding.progressBar.changeVisibility(it)
        }
    }
}
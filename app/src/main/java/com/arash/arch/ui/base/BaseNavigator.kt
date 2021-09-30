package com.arash.arch.ui.base

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation.findNavController

/**
 * Handles navigation between Activities and fragments in the app.
 */
interface BaseNavigator {
    fun navigateTo(fragment: Fragment, directions: NavDirections) {
        findNavController(fragment.requireView()).navigate(directions)
    }

    fun navigateBack(fragment: Fragment) {
        findNavController(fragment.requireView()).popBackStack().not()
    }
}

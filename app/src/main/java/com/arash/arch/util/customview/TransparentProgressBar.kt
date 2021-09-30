package com.arash.arch.util.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.arash.arch.R

/**
 * A progressbar that size of it don't change in different display sizes.
 */
class TransparentProgressBar(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {
    init {
        View.inflate(context, R.layout.transparent_progress_bar, this)
    }
}
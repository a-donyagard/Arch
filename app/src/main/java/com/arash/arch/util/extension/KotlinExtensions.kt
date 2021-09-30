package com.arash.arch.util.extension

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.webkit.WebView
import android.widget.ImageView
import androidx.annotation.LayoutRes
import com.arash.arch.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar

/**
 * close keyboard on this view
 */
fun View.closeKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun View.openKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

/**
 * @param message Message to show as toast
 */
fun View.showMessage(message: String) {
    this.closeKeyboard()
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}

/**
 * Change [View] visibility by [Boolean]
 * @param visible define [View] visibility
 */
fun View.changeVisibility(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

fun ViewGroup.inflate(@LayoutRes layoutResId: Int): View {
    return LayoutInflater.from(context).inflate(layoutResId, this, false)
}

fun Context.browseIntent(url: String) {
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    startActivity(browserIntent)
}

fun Context.loadImageUrl(url: String, imageView: ImageView) {
    Glide.with(this)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.drawable.vc_profile_placeholder)
        .error(R.drawable.vc_profile_placeholder)
        .into(imageView)
}

@SuppressLint("SetJavaScriptEnabled")
fun WebView.loadWebView(url: String) {
    clearCache(true)
    clearHistory()
    settings.javaScriptEnabled = true
    loadUrl(url)
}

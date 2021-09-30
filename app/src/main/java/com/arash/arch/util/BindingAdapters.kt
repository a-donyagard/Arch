package com.arash.arch.util

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.arash.arch.R
import com.arash.arch.app.GlideApp
import com.bumptech.glide.load.engine.DiskCacheStrategy

class BindingAdapters {
    companion object {
        @JvmStatic
        @BindingAdapter("srcImageUrl")
        fun setImageSrc(imageView: ImageView, url: String?) {
            if (!url.isNullOrEmpty()) {
                GlideApp.with(imageView.context)
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(imageView)
            }
        }

        @JvmStatic
        @BindingAdapter("srcProfileImageUrl")
        fun setProfileImageSrc(imageView: ImageView, url: String?) {
            GlideApp.with(imageView.context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.vc_profile_placeholder)
                .error(R.drawable.vc_profile_placeholder)
                .into(imageView)
        }

        @JvmStatic
        @BindingAdapter("srcImageDrawableRes")
        fun setImageSrcDrawableRes(imageView: ImageView, @DrawableRes drawableRes: Int) {
            GlideApp.with(imageView.context)
                .load(drawableRes)
                .into(imageView)
        }
    }
}

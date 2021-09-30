package com.arash.arch.util

import com.arash.arch.BuildConfig
import javax.inject.Inject

/**
 * Helper class to save and get secret data using NDK
 */
class SecretFields @Inject constructor() {

    private val debugBaseUrl = "https://kitsu.io/"
    private val releaseBaseUrl = "https://kitsu.io/"

    fun getBaseUrl(): String {
        return if (BuildConfig.DEBUG) {
            debugBaseUrl
        } else {
            releaseBaseUrl
        }
    }
}

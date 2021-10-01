package com.arash.arch.data.source.preference

import android.content.Context
import android.content.SharedPreferences
import com.arash.arch.data.model.anime.PaginationLinks
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * With this helper we can access all shared preferences.
 * Every field uses [PreferenceDelegate] for managing get and set value
 */
class AppPreferencesHelper @Inject constructor(@ApplicationContext context: Context) {

    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences(
            PREF_NAME,
            Context.MODE_PRIVATE
        )
    }

    companion object {
        private const val PREF_NAME = "pref_hami_bourse"
        private const val ACCESS_TOKEN = "UserToken"
        private const val REFRESH_TOKEN = "RefreshToken"
        private const val UNIQUE_ID = "UniqueId"
        private const val FIRST_PAGE_LINK = "first_page_link"
        private const val NEXT_PAGE_LINK = "next_page_link"
        private const val PREV_PAGE_LINK = "prev_page_link"
        private const val LAST_PAGE_LINK = "last_page_link"
    }

    fun getToken(): String? {
        return prefs.getString(ACCESS_TOKEN, "")
    }

    fun setToken(token: String) {
        val editor = prefs.edit()
        editor.putString(ACCESS_TOKEN, token)
        editor.apply()
    }

    fun getRefreshToken(): String? {
        return prefs.getString(REFRESH_TOKEN, "")
    }

    fun setRefreshToken(token: String) {
        val editor = prefs.edit()
        editor.putString(REFRESH_TOKEN, token)
        editor.apply()
    }

    /**
     * provide saving and getting device unique id from preferences in order to use in [DeviceUtil]
     */
    var uniqueId: String by PreferenceDelegate(prefs, UNIQUE_ID, "")

    fun setPaginationLinks(paginationLinks: PaginationLinks) {
        val editor = prefs.edit()
        editor.putString(FIRST_PAGE_LINK, paginationLinks.first)
        editor.putString(NEXT_PAGE_LINK, paginationLinks.next)
        editor.putString(PREV_PAGE_LINK, paginationLinks.prev)
        editor.putString(LAST_PAGE_LINK, paginationLinks.last)
        editor.apply()
    }

    fun getPaginationLinks(): PaginationLinks {
        return PaginationLinks(
            first = prefs.getString(FIRST_PAGE_LINK, null),
            next = prefs.getString(NEXT_PAGE_LINK, null),
            prev = prefs.getString(PREV_PAGE_LINK, null),
            last = prefs.getString(LAST_PAGE_LINK, null),
        )
    }
}

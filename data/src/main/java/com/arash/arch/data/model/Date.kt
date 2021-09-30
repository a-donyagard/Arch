package com.arash.arch.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Date(
    val date: String,
    val time: String
) : Parcelable

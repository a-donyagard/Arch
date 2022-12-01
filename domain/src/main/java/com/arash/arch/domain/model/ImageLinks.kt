package com.arash.arch.domain.model

import com.arash.arch.domain.base.DomainModel

data class ImageLinks(
    val tiny: String,
    val large: String,
    val small: String,
    val medium: String?,
    val original: String
) : DomainModel
package com.arash.arch.domain.model

import com.arash.arch.domain.base.DomainModel

data class Anime(
    val id: Int,
    val attributes: Attributes
) : DomainModel

package com.arash.arch.domain.model

import com.arash.arch.domain.base.DomainModel

data class ResponseWrapper<T>(
    val data: T,
    val links: PaginationLinks?
) : DomainModel

data class PaginationLinks(
    val first: String?,
    val next: String?,
    val prev: String?,
    val last: String?
) : DomainModel
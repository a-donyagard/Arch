package com.arash.arch.data.model

import com.arash.arch.domain.model.PaginationLinks
import com.arash.arch.domain.model.ResponseWrapper
import com.google.gson.annotations.SerializedName

data class ResponseWrapperDto<T>(
    @SerializedName("data")
    val data: T,
    @SerializedName("links")
    val links: PaginationLinksDto?
) : EntityModel<ResponseWrapper<*>> {
    @Suppress("IMPLICIT_CAST_TO_ANY")
    override fun toDomain(): ResponseWrapper<*> {
        val domainData = when (data) {
            is EntityModel<*> -> data.toDomain()
            is List<*> -> data.map { (it as EntityModel<*>).toDomain() }
            else -> data
        }
        return ResponseWrapper(
            domainData,
            links?.toDomain()
        )
    }
}

data class PaginationLinksDto(
    @SerializedName("first")
    val first: String?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("prev")
    val prev: String?,
    @SerializedName("last")
    val last: String?
) : EntityModel<PaginationLinks> {
    override fun toDomain(): PaginationLinks {
        return PaginationLinks(
            first,
            next,
            prev,
            last
        )
    }
}
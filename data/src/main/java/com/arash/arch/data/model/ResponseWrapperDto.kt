package com.arash.arch.data.model

import com.arash.arch.domain.base.DomainModel
import com.arash.arch.domain.model.PaginationLinks
import com.arash.arch.domain.model.ResponseWrapper
import com.google.gson.annotations.SerializedName

data class ResponseWrapperDto<D : DomainModel, E : EntityModel<D>>(
    @SerializedName("data")
    val data: List<E>,
    @SerializedName("links")
    val links: PaginationLinksDto?
) : EntityModel<ResponseWrapper<D>> {
    override fun toDomain(): ResponseWrapper<D> {
        return ResponseWrapper(
            data.map { it.toDomain() },
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
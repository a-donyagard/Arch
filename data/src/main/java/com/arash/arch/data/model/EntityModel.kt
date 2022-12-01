package com.arash.arch.data.model

import com.arash.arch.domain.base.DomainModel

interface EntityModel<T : DomainModel> {
    fun toDomain(): T
}
package com.arash.arch.data.repository

import arrow.core.Either
import com.arash.arch.data.mapper.ErrorMapper
import com.arash.arch.data.model.EntityModel
import com.arash.arch.data.model.wrapper.PaginationResponse
import com.arash.arch.data.util.EmptyListException
import com.arash.arch.domain.base.Error
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseRepository(private val errorMapper: ErrorMapper) {
    protected fun <T, R> getResult(
        call: suspend () -> T
    ): Flow<Either<Error, R>> {
        return flow {
            kotlin.runCatching {
                emit(call.invoke().wrapResponse())
            }.onFailure {
                emit(Either.left(errorMapper.getError(it)))
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T, R> T.wrapResponse(): Either<Error, R> {
        return when (this) {
            is EntityModel<*> -> {
                Either.right(this.toDomain() as R)
            }
            is List<*> -> {
                if (this.isEmpty()) {
                    throw EmptyListException()
                }
                this.firstOrNull()?.let {
                    if (it is String) {
                        Either.right(this as R)
                    } else {
                        Either.right(this.map { item -> (item as EntityModel<*>).toDomain() } as R)
                    }
                } ?: Either.right(this.map { (it as EntityModel<*>).toDomain() } as R)
            }
            else -> {
                Either.right(this as R)
            }
        }
    }

    protected fun <T : Any, R : PaginationResponse<T>> getPaginationResponseResult(call: suspend () -> R): Flow<Either<Error, R>> {
        return flow {
            kotlin.runCatching {
                val paginationResponse = call.invoke()
                if (paginationResponse.results.isEmpty()) {
                    throw EmptyListException()
                }
                emit(Either.right(paginationResponse))
            }.onFailure {
                emit(Either.left(errorMapper.getError(it)))
            }
        }
    }
}

package com.arash.arch.data.repository

import arrow.core.Either
import com.arash.arch.data.mapper.ErrorMapper
import com.arash.arch.data.model.Error
import com.arash.arch.data.model.wrapper.PaginationResponse
import com.arash.arch.data.util.EmptyListException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseRepository(private val errorMapper: ErrorMapper) {
    protected fun <T : Any> getResult(call: suspend () -> T): Flow<Either<Error, T>> {
        return flow {
            kotlin.runCatching {
                emit(Either.right(call.invoke()))
            }.onFailure {
                emit(Either.left(errorMapper.getError(it)))
            }
        }
    }

    protected fun <T : List<Any>> getListResult(call: suspend () -> T): Flow<Either<Error, T>> {
        return flow {
            kotlin.runCatching {
                val list = call.invoke()
                if (list.isEmpty()) {
                    throw EmptyListException()
                }
                emit(Either.right(list))
            }.onFailure {
                emit(Either.left(errorMapper.getError(it)))
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

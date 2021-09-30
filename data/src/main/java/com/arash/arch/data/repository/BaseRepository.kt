package com.arash.arch.data.repository

import arrow.core.Either
import com.arash.arch.data.mapper.ErrorMapper
import com.arash.arch.data.model.Error
import com.arash.arch.data.model.wrapper.PaginationResponse
import com.arash.arch.data.util.EmptyListException

abstract class BaseRepository(private val errorMapper: ErrorMapper) {
    protected suspend fun <T : Any> getResult(call: suspend () -> T): Either<Error, T> {
        return try {
            Either.right(call.invoke())
        } catch (t: Throwable) {
            Either.left(errorMapper.getError(t))
        }
    }

    protected suspend fun <T : List<Any>> getListResult(call: suspend () -> T): Either<Error, T> {
        return try {
            val list = call.invoke()
            if (list.isEmpty()) {
                throw EmptyListException()
            }
            Either.right(list)
        } catch (t: Throwable) {
            Either.left(errorMapper.getError(t))
        }
    }

    protected suspend fun <T : Any, R : PaginationResponse<T>> getPaginationResponseResult(call: suspend () -> R): Either<Error, R> {
        return try {
            val paginationResponse = call.invoke()
            if (paginationResponse.results.isEmpty()) {
                throw EmptyListException()
            }
            Either.right(paginationResponse)
        } catch (t: Throwable) {
            Either.left(errorMapper.getError(t))
        }
    }
}

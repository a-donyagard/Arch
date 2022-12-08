package com.arash.arch.util.extension

import arrow.core.Either
import com.arash.arch.domain.base.Error

sealed class UiState<T>() {
    class Loading<T> : UiState<T>()
    class Error<T>(val error: com.arash.arch.domain.base.Error) : UiState<T>()
    class Data<T>(val data: T?) : UiState<T>()
    class Failure<T>(val message: String?) : UiState<T>()
    class None<T> : UiState<T>()
}

fun <T> Either<Error, T>.toUiState(): UiState<T> {
    return when (this) {
        is Either.Right -> {
            UiState.Data(this.b)
        }
        is Either.Left -> {
            UiState.Error(this.a)
        }
    }
}

fun <T, R> Either<Error, T>.toUiState(action: (T) -> R): UiState<R> {
    return when (this) {
        is Either.Right -> {
            UiState.Data(action.invoke(this.b))
        }
        is Either.Left -> {
            UiState.Error(this.a)
        }
    }
}

fun <T> UiState<T>.foldResponse(
    onLoading: () -> Unit = {},
    onStopLoading: () -> Unit = {},
    onSuccess: (T?) -> Unit,
    onFailure: (Error) -> Unit,
    onEmptyList: () -> Unit = {}
) {
    when (this) {
        is UiState.Data -> {
            onSuccess(this.data)
            onStopLoading()
        }
        is UiState.Error -> {
            onStopLoading()
            onFailure(this.error)
            if (error is Error.EmptyResponse) onEmptyList()
        }
        is UiState.Failure -> {}
        is UiState.Loading -> {
            onLoading()
        }
        is UiState.None -> {
            // no-op
        }
    }
}
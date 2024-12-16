package com.snap_fetch_kmp.util

sealed class ResponseState<out T> {
    data class Success<T>(val value: T) : ResponseState<T>()
    data class Error(val error: KError) : ResponseState<Nothing>()

    inline val isSuccess: Boolean
        get() = this is Success

    inline val isError: Boolean
        get() = this is Error

    fun value(): T? {
        return (this as? Success)?.value
    }

    fun error(): KError? {
        return (this as? Error)?.error
    }

    inline fun <T, K> ResponseState<T>.map(transform: (T) -> K): ResponseState<K> {
        return when (this) {
            is Success -> success(transform(value))
            is Error -> error(error)
        }
    }

    companion object {
        fun <T> success(value: T): ResponseState<T> = Success(value)

        fun failure(type: KErrorType, error: Throwable): ResponseState<Nothing> =
            Error(KError(type, error.message ?: "Unknown Error", error))

        fun failure(error: KError): ResponseState<Nothing> = Error(error)

    }
}

inline fun <T, R> T.runSafely(block: T.() -> R): ResponseState<R> {
    return try {
        ResponseState.success(block())
    } catch (e: Throwable) {
        ResponseState.failure(KErrorType.UNKNOWN, e)
    }
}

data class KError(
    val type: KErrorType,
    val message: String,
    val throwable: Throwable? = null
)

val ERROR_UNKNOWN = KError(KErrorType.UNKNOWN, "Unknown Error")
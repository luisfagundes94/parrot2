package com.luisfagundes.common.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import retrofit2.HttpException

fun <T> Flow<T>.parseError(): Flow<T> {
    return catch { throwable ->
        throw throwable.parseThrowable()
    }
}

private fun Throwable.parseThrowable(): Throwable {
    return when (this) {
        is HttpException -> this.toRequestThrowable()
        else -> this
    }
}

private fun HttpException.toRequestThrowable(): Throwable {
    return when (this.code()) {
        401 -> Exception("Authentication error: Invalid API key", this.cause)
        403 -> Exception("Authentication error: Access forbidden", this.cause)
        413 -> Exception("Text too long: Request payload exceeds size limit", this.cause)
        429 -> Exception("Quota exceeded: API rate limit or character limit reached", this.cause)
        456 -> Exception("Quota exceeded: Character limit reached", this.cause)
        400 -> Exception("Invalid language: Unsupported language combination", this.cause)
        else -> Exception("HTTP ${this.code()}: ${this.message()}", this.cause)
    }
}
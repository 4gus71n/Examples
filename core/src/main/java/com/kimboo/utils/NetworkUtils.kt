package com.kimboo.utils

/**
 * Simple wrapper class to keep track of the source of the intformation.
 */
class DataResponse<MODEL: Any>(
    val response: MODEL,
    val status: DataResponse.Status
) {
    enum class Status {
        SUCCESS,
        EMPTY,
        CACHED_RESPONSE
    }
}

/**
 * Triggered any time that something goes wrong with an API call to the backend.
 */
open class BackendException : Throwable()

/**
 * Triggered if we don't have internet connection when we do an API call to the backend.
 */
class NoInternetConnectionException : BackendException()

/**
 * Triggered when we get a reply from a backend endpoint that isn't a 2xx.
 */
class BackendErrorException(
    val statusCode: Int
) : BackendException()
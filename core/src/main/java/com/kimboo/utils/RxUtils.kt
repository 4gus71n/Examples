package com.kimboo.utils

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response

/**
 * Takes Retrofit's Response wrapper and turns it into a DataResponse wrapper.
 */
fun <T : Any> Single<Response<T>>.toDataResponse(): Observable<DataResponse<T>> {
    return map {
        if (it.isSuccessful) {
            when (it.code()) {
                204 -> {
                    DataResponse(it.body()!!, DataResponse.Status.EMPTY)
                }
                304 -> {
                    DataResponse(it.body()!!, DataResponse.Status.CACHED_RESPONSE)
                }
                else -> {
                    DataResponse(it.body()!!, DataResponse.Status.SUCCESS)
                }
            }
        } else {
            throw BackendErrorException(it.code())
        }
    }
    .toObservable()
}
package com.kimboo.repositories

import com.kimboo.retrofit.responses.ApiBusinessSkilssResponse
import com.kimboo.retrofit.responses.ApiProfileResponse
import com.kimboo.utils.DataResponse
import io.reactivex.Observable

interface ProfileRepository {
    fun fetchProfileInformation(): Observable<DataResponse<ApiProfileResponse>>
    fun fetchBusinessSkils(): Observable<DataResponse<ApiBusinessSkilssResponse>>
}
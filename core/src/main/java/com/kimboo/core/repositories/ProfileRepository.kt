package com.kimboo.core.repositories

import com.kimboo.core.retrofit.responses.ApiBusinessSkilssResponse
import com.kimboo.core.retrofit.responses.ApiProfileResponse
import com.kimboo.core.utils.DataResponse
import io.reactivex.Observable

interface ProfileRepository {
    fun fetchProfileInformation(): Observable<DataResponse<ApiProfileResponse>>
    fun fetchBusinessSkils(): Observable<DataResponse<ApiBusinessSkilssResponse>>
}
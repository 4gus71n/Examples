package com.kimboo.core.repositories

import com.kimboo.core.retrofit.responses.ApiRecipeResponse
import com.kimboo.core.utils.DataResponse
import io.reactivex.Observable

interface RecipesNetworkRepository{
    fun fetchRecepies(): Observable<DataResponse<List<ApiRecipeResponse>>>
}
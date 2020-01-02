package com.kimboo.repositories

import com.kimboo.retrofit.responses.ApiRecipeResponse
import com.kimboo.utils.DataResponse
import io.reactivex.Observable

interface RecipesNetworkRepository{
    fun fetchRecepies(): Observable<DataResponse<List<ApiRecipeResponse>>>
}
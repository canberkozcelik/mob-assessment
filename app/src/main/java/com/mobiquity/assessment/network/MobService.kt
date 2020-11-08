package com.mobiquity.assessment.network

import com.mobiquity.assessment.network.data.CategoryResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface MobService {

    @GET(".")
    suspend fun getCategories(): ApiResponse<List<CategoryResponse>>
}
package com.mobiquity.assessment.network

import javax.inject.Inject

class MobServiceClient @Inject constructor(private val mobService: MobService) {

    suspend fun getCategories() = mobService.getCategories()
}
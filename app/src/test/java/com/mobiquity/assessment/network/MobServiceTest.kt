package com.mobiquity.assessment.network

import com.mobiquity.assessment.MainCoroutinesRule
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MobServiceTest : ApiAbstract<MobService>() {

    private lateinit var service: MobService

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @Before
    fun initService() {
        service = createService(MobService::class.java)
    }

    @Test
    fun getCategoriesFromNetworkTest() = runBlocking {
        enqueueResponse("response.json")
        val response = service.getCategories()
        val responseBody = requireNotNull((response as ApiResponse.Success).data)
        mockServer.takeRequest()

        assertThat(responseBody.isEmpty(), `is`(false))
        assertThat(responseBody[0].id, `is`("36802"))
        assertThat(responseBody[0].name, `is`("food"))
        assertThat(responseBody[0].products, `is`(false))
        assertThat(responseBody[0].products?.get(0)?.id, `is`("1"))
        assertThat(responseBody[0].products?.get(0)?.name, `is`("Bread"))
        assertThat(responseBody[0].id, `is`(responseBody[0].products?.get(0)?.categoryId))
    }
}
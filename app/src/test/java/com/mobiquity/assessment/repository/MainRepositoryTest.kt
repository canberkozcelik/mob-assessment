package com.mobiquity.assessment.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.mobiquity.assessment.MainCoroutinesRule
import com.mobiquity.assessment.MockUtil.mockCategories
import com.mobiquity.assessment.network.MobService
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class MainRepositoryTest {

    private lateinit var repository: MainRepository
    private val service: MobService = mock()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        repository = MainRepository(service)
    }

    @Test
    fun getCategoriesFromNetworkTest(): Unit = runBlocking {
        val mockData = mockCategories()
        whenever(service.getCategories()).thenReturn(ApiResponse.of { Response.success(mockData) })

        repository.getCategories(
            onError = {},
            onSuccess = {}
        )
            .test {
                assertEquals(expectItem()[0].id, "36802")
                assertEquals(expectItem()[0].name, "Food")
                assertEquals(expectItem()[0].products?.get(0)?.id, "1")
                assertEquals(expectItem()[0].products?.get(0)?.categoryId, expectItem()[0].id)
                assertEquals(expectItem()[0].products?.get(0)?.name, "Bread")
            }

        verify(service, atLeastOnce()).getCategories()
    }
}
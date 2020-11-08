package com.mobiquity.assessment.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.mobiquity.assessment.MainCoroutinesRule
import com.mobiquity.assessment.MockUtil
import com.mobiquity.assessment.network.MobService
import com.mobiquity.assessment.network.data.CategoryResponse
import com.mobiquity.assessment.repository.MainRepository
import com.mobiquity.assessment.ui.main.MainViewModel
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class MainViewModelTest {

    private lateinit var viewModel: MainViewModel
    private lateinit var mainRepository: MainRepository
    private val service: MobService = mock()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        mainRepository = MainRepository(service)
        viewModel = MainViewModel(mainRepository)
    }

    @Test
    fun getCategoriesTest() = runBlocking {
        val mockData = MockUtil.mockCategories()
        whenever(service.getCategories()).thenReturn(ApiResponse.of { Response.success(mockData) })

        val observer: Observer<List<CategoryResponse>> = mock()
        val fetchedData: LiveData<List<CategoryResponse>> =
            mainRepository.getCategories(
                onSuccess = {},
                onError = {}
            ).asLiveData()
        fetchedData.observeForever(observer)
        viewModel.getCategories(1)

        verify(service, atLeastOnce()).getCategories()
        verify(observer).onChanged(mockData)
        fetchedData.removeObserver(observer)
    }
}
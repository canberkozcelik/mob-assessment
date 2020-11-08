package com.mobiquity.assessment.ui.main

import androidx.annotation.MainThread
import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.mobiquity.assessment.base.LiveCoroutinesViewModel
import com.mobiquity.assessment.network.data.CategoryResponse
import com.mobiquity.assessment.repository.MainRepository
import timber.log.Timber

class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository
) : LiveCoroutinesViewModel() {

    private var getCategoriesLiveData: MutableLiveData<Int> = MutableLiveData(0)
    val categoryListLiveData: LiveData<List<CategoryResponse>>

    private val _toastLiveData: MutableLiveData<String> = MutableLiveData()
    val toastLiveData: LiveData<String> get() = _toastLiveData

    val isLoading: ObservableBoolean = ObservableBoolean(false)

    init {
        Timber.d("init MainViewModel")

        categoryListLiveData = getCategoriesLiveData.switchMap {
            isLoading.set(true)
            launchOnViewModelScope {
                mainRepository.getCategories(
                    onSuccess = { isLoading.set(false) },
                    onError = { _toastLiveData.postValue(it) }
                ).asLiveData()
            }
        }
    }

    // can be used for pagination
    // used for testing
    @MainThread
    fun getCategories(trigger: Int) {
        getCategoriesLiveData.value = trigger
    }
}

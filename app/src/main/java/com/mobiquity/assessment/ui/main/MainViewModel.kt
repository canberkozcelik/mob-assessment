package com.mobiquity.assessment.ui.main

import androidx.databinding.ObservableBoolean
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mobiquity.assessment.base.LiveCoroutinesViewModel
import com.mobiquity.assessment.network.data.CategoryResponse
import com.mobiquity.assessment.repository.MainRepository
import timber.log.Timber

class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
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
}

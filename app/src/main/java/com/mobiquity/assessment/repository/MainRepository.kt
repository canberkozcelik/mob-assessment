package com.mobiquity.assessment.repository

import androidx.annotation.WorkerThread
import com.mobiquity.assessment.network.MobService
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(private val service: MobService) {

    @WorkerThread
    suspend fun getCategories(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) = flow {
        val response = service.getCategories()

        response.suspendOnSuccess {
            data?.let {
                emit(it)
                onSuccess()
            }
        }
            // handle server error
            .onError {
                onError(message())
            }
            // handle exception
            .onException {
                onError(message())
            }
    }.flowOn(Dispatchers.IO)
}

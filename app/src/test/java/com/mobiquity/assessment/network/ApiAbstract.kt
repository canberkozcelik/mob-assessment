package com.mobiquity.assessment.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.nio.charset.StandardCharsets

@RunWith(JUnit4::class)
abstract class ApiAbstract<T> {

    @Rule
    @JvmField
    val instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var mockServer: MockWebServer

    @Before
    fun mockServer() {
        mockServer = MockWebServer()
        mockServer.start(8080)
    }

    fun enqueueResponse(fileName: String) {
        enqueueResponse(fileName, emptyMap())
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String>) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream("api-response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockServer.enqueue(mockResponse.setBody(source.readString(StandardCharsets.UTF_8)))
    }

    fun createService(serviceClass: Class<T>): T {
        return Retrofit.Builder()
            .baseUrl(mockServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory())
            .build()
            .create(serviceClass)
    }

    @After
    fun stopServer() {
        mockServer.shutdown()
    }
}

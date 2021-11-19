package com.gideondev.creditscore.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gideondev.creditscore.network.CreditScoreApiInterface
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class CreditScoreServiceTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: CreditScoreApiInterface

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
                .baseUrl(mockWebServer.url(""))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CreditScoreApiInterface::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun requestCreditScore() {
        runBlocking {
            enqueueResponse("CreditScoreResponse.json")
            val resultResponse = service.getCreditScore()

            val request = mockWebServer.takeRequest()
            assertNotNull(resultResponse)
            assertThat(request.path, `is`("/endpoint.json"))
        }
    }

    @Test
    fun getCreditScoreResponse() {
        runBlocking {
            enqueueResponse("CreditScoreResponse.json")
            val resultResponse = service.getCreditScore()
            assertThat(resultResponse.accountIDVStatus, `is`("PASS"))
            assertThat(resultResponse.creditReportInfo!!.score, `is`(514))
        }
    }
//
//    @Test
//    fun getLegoSetsPagination() {
//        runBlocking {
//            enqueueResponse("legosets.json")
//            val resultResponse = service.getSets().body()
//
//            assertNull(resultResponse!!.next)
//            assertNull(resultResponse.previous)
//        }
//    }
//
//
//    @Test
//    fun getLegoSetItem() {
//        runBlocking {
//            enqueueResponse("legosets.json")
//            val resultResponse = service.getSets().body()
//            val legoSets = resultResponse!!.results
//
//            val legoSet = legoSets[0]
//            assertThat(legoSet.id, `is`("30212-1"))
//            assertThat(legoSet.name, `is`("Mirkwood Elf Guard"))
//            assertThat(legoSet.year, `is`(2012))
//            assertThat(legoSet.themeId, `is`(563))
//            assertThat(legoSet.numParts, `is`(27))
//            assertThat(legoSet.imageUrl, `is`("https://cdn.rebrickable.com/media/sets/30212-1.jpg"))
//            assertThat(legoSet.url, `is`("https://rebrickable.com/sets/30212-1/mirkwood-elf-guard/"))
//            assertThat(legoSet.lastModifiedDate, `is`("2016-04-23T12:25:04.325081Z"))
//        }
//    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader
                .getResourceAsStream("api-response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(mockResponse.setBody(
                source.readString(Charsets.UTF_8))
        )
    }
}

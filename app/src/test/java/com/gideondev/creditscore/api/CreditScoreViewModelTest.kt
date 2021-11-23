package com.gideondev.creditscore.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import com.gideondev.creditscore.model.CreditScoreResponse
import com.gideondev.creditscore.network.CreditScoreApiInterface
import com.gideondev.creditscore.repository.CreditScoreRepository
import com.gideondev.creditscore.utils.BASE_URL
import com.gideondev.creditscore.utils.TestCoroutineRule
import com.gideondev.creditscore.viewModel.CreditScoreViewModel
import com.gideondev.creditscore.viewModel.Resource
import com.gideondev.creditscore.viewModel.Status
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.app.Activity
import org.mockito.Mockito.mock


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CreditScoreViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    @Mock
    private lateinit var apiUsersObserver: Observer<Resource<CreditScoreResponse>>

    @Mock
    private lateinit var repo: CreditScoreRepository

    @Mock
    private lateinit var apiHelper: CreditScoreApiInterface

    private lateinit var activity: FragmentActivity
    private val receivedUiStates = mutableListOf<Status>()
    private lateinit var viewModel: CreditScoreViewModel

    @Before
    fun setUp(){
        viewModel = CreditScoreViewModel(repo)
      //  repo = Mockito.spy(CreditScoreRepository())
    }

    @Test
    fun `should return Success when network request is successful`() =
        testCoroutineRule.runBlockingTest {
            observeViewModel(viewModel)
            Assert.assertTrue(receivedUiStates.isEmpty())
            viewModel.getCreditScoreFromServer()

            assertEquals(
                listOf(
                    Status.LOADING,
                    Status.SUCCESS,
                ),
                receivedUiStates
            )
        }

    @Test
    fun `should return Error when network request fails`() =
        testCoroutineRule.runBlockingTest {

            viewModel.getCreditScoreFromServer()
            observeViewModel(viewModel)

            Assert.assertTrue(receivedUiStates.isEmpty())

            viewModel.getCreditScoreFromServer()
            assertEquals(
                listOf(
                    Status.LOADING,
                    Status.ERROR,
                ),
                receivedUiStates
            )
        }

    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        val postService = mock(CreditScoreApiInterface::class.java)

        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error Message For You"
            Mockito.doThrow(RuntimeException(errorMessage))
                .`when`(postService)
                .getCreditScore()
            viewModel.getCreditScoreFromServer()
                //.observeForever(apiUsersObserver)
            observeViewModel(viewModel)
            Mockito.verify(postService).getCreditScore()
            assertEquals(
                listOf(
                    Status.LOADING,
                    Status.ERROR,
                ),
                receivedUiStates
            )
//            Mockito.verify(apiUsersObserver).onChanged(
//                Resource.error(
//                    RuntimeException(errorMessage).toString(),
//                    null
//                )
//            )
        //    viewModel.getUsers().removeObserver(apiUsersObserver)
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: CreditScoreApiInterface = getRetrofit().create(CreditScoreApiInterface::class.java)



    private fun observeViewModel(viewModel: CreditScoreViewModel) {
        viewModel.creditScoreResponse.observeForever { uiState ->
            if (uiState != null) {
                receivedUiStates.add(uiState.status)
            }
        }
    }

}
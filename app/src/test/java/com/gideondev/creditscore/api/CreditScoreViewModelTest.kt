package com.gideondev.creditscore.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.gideondev.creditscore.model.CreditScoreResponse
import com.gideondev.creditscore.repository.CreditScoreRepository
import com.gideondev.creditscore.utils.BaseTestTry
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
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.Robolectric

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CreditScoreViewModelTest : BaseTestTry() {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    @Mock
    private lateinit var apiUsersObserver: Observer<Resource<CreditScoreResponse>>

    @Mock
    private lateinit var repo: CreditScoreRepository

    private lateinit var activity: FragmentActivity
    private val receivedUiStates = mutableListOf<Status>()
    private lateinit var viewModel: CreditScoreViewModel

    @Before
    override fun setUp(){
        super.setUp()
        viewModel = CreditScoreViewModel(repo)
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

//    @Test
//    fun givenServerResponseError_whenFetch_shouldReturnError() {
//        testCoroutineRule.runBlockingTest {
//            val errorMessage = "Error Message For You"
//            doThrow(RuntimeException(errorMessage))
//                .`when`(apiHelper)
//                .getUsers()
//            val viewModel = SingleNetworkCallViewModel(apiHelper, databaseHelper)
//            viewModel.getUsers().observeForever(apiUsersObserver)
//            verify(apiHelper).getUsers()
//            verify(apiUsersObserver).onChanged(
//                Resource.error(
//                    RuntimeException(errorMessage).toString(),
//                    null
//                )
//            )
//            viewModel.getUsers().removeObserver(apiUsersObserver)
//        }
//    }



    override fun isMockServerEnabled(): Boolean {
       return true
    }


    private fun observeViewModel(viewModel: CreditScoreViewModel) {
        viewModel.creditScoreResponse.observeForever { uiState ->
            if (uiState != null) {
                receivedUiStates.add(uiState.status)
            }
        }
    }

}
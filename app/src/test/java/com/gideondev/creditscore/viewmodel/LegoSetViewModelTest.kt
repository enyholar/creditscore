package com.gideondev.creditscore.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gideondev.creditscore.repository.CreditScoreRepository
import com.gideondev.creditscore.utils.TestCoroutineRule
import com.gideondev.creditscore.viewModel.CreditScoreViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class LegoSetViewModelTest {

    private val themeId = 567

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val repository = mock(CreditScoreRepository::class.java)
    private var viewModel = CreditScoreViewModel(repository)

    @ExperimentalCoroutinesApi
    @Test
    fun testNull() {
        testCoroutineRule.runBlockingTest {
            assertThat(viewModel.creditScoreResponse.value, nullValue())
            verify(repository, never()).getCreditScore()
        }

    }

//    @Test
//    fun doNotFetchWithoutObservers() {
//        viewModel.themeId = themeId
//        verify(repository, never()).observePagedSets(false, themeId, coroutineScope)
//    }
//
//    @Test
//    fun doNotFetchWithoutObserverOnConnectionChange() {
//        viewModel.themeId = themeId
//        viewModel.connectivityAvailable = true
//
//        verify(repository, never()).observePagedSets(true, themeId, coroutineScope)
//    }

}
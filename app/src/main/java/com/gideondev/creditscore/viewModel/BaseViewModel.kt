package com.gideondev.creditscore.viewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gideondev.creditscore.model.CreditScoreResponse
import com.gideondev.creditscore.viewModel.Resource

open class BaseViewModel : ViewModel() {

    val commonMessage: MutableLiveData<String> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean?> = MutableLiveData()
    val errorMessage: MutableLiveData<String?> = MutableLiveData()
//    fun uiState(): LiveData<T> = uiState
//    protected val uiState: MutableLiveData<T> = MutableLiveData()
    val creditScoreResponse = MutableLiveData<Resource<CreditScoreResponse>>()

}
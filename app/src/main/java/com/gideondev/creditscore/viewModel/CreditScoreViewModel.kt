package com.gideondev.creditscore.viewModel

import javax.inject.Inject
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.gideondev.creditscore.repository.CreditScoreRepository

class CreditScoreViewModel
@Inject
constructor(
    private val creditScoreRepository: CreditScoreRepository
) : BaseViewModel(){
    val TAG = CreditScoreViewModel::class.java.name


    fun getCreditScoreFromServer() {
        viewModelScope.launch {
            creditScoreResponse.postValue(Resource.loading(null))
            try {
                val creditScoreFromApi = creditScoreRepository.getCreditScore()
                creditScoreResponse.postValue(Resource.success(creditScoreFromApi))
            } catch (e: Exception) {
                creditScoreResponse.postValue(Resource.error(e.toString(), null))
            }
        }
    }

}
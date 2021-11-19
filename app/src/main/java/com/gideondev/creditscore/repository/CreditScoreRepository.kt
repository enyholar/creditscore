package com.gideondev.creditscore.repository

import com.gideondev.creditscore.network.CreditScoreApiInterface



class CreditScoreRepository(
    private val creditScoreApiInterface: CreditScoreApiInterface
){

    suspend fun getCreditScore() = creditScoreApiInterface.getCreditScore()

}
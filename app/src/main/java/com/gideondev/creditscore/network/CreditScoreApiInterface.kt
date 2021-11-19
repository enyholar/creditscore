package com.gideondev.creditscore.network
import com.gideondev.creditscore.model.CreditScoreResponse
import retrofit2.http.*


interface CreditScoreApiInterface {

    @GET("endpoint.json")
    suspend fun getCreditScore(): CreditScoreResponse


}
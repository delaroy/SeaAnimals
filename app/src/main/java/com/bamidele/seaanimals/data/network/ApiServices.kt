package com.bamidele.seaanimals.data.network

import com.bamidele.seaanimals.data.model.SeaAnimalsResponse
import retrofit2.Retrofit
import retrofit2.http.GET

interface ApiServices {

    @GET("/api/species")
    suspend fun fetchSeaAnimals(): List<SeaAnimalsResponse>

    companion object Factory {
        fun create(retrofit: Retrofit): ApiServices = retrofit.create(ApiServices::class.java)
    }
}
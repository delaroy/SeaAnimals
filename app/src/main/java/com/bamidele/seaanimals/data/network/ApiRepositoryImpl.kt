package com.bamidele.seaanimals.data.network

import android.util.Log
import com.bamidele.seaanimals.data.model.SeaAnimalsResponse
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ApiRepositoryImpl(private val services: ApiServices) : ApiRepository {

    override suspend fun fetchSeaAnimals(): Flow<List<SeaAnimalsResponse>> = flow {
        emit(services.fetchSeaAnimals())
    }
}
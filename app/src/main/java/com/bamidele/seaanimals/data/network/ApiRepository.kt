package com.bamidele.seaanimals.data.network

import com.bamidele.seaanimals.data.model.SeaAnimalsResponse
import kotlinx.coroutines.flow.Flow

interface ApiRepository {

    suspend fun fetchSeaAnimals(): Flow<List<SeaAnimalsResponse>>

}
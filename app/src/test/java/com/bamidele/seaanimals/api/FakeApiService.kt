package com.bamidele.seaanimals.api

import com.bamidele.seaanimals.JsonProvider
import com.bamidele.seaanimals.data.model.SeaAnimalsResponse
import com.bamidele.seaanimals.data.network.ApiServices
import java.lang.Exception
import javax.inject.Inject

class FakeApiService @Inject constructor() : ApiServices {

    var failUserApi: Boolean = false
    var wrongResponse: Boolean = false

    override suspend fun fetchSeaAnimals(): List<SeaAnimalsResponse> {
        if (failUserApi) throw Exception("Api failed")
        val fakeResponse: List<SeaAnimalsResponse> = JsonProvider.objectFromJsonFileWithType(FISH_JSON)

        if (wrongResponse) return fakeResponse.apply {
           this[0].speciesName = ""
        }

        return fakeResponse
    }

    companion object {
        private const val FISH_JSON = "/json/fish.json"
    }
}
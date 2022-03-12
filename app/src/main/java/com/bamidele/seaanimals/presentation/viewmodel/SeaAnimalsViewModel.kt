package com.bamidele.seaanimals.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bamidele.seaanimals.data.model.SeaAnimalsResponse
import com.bamidele.seaanimals.data.network.ApiRepository
import com.bamidele.seaanimals.presentation.model.UIState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class SeaAnimalsViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {

    private val _seaAnimalsFlow = MutableStateFlow<UIState<List<SeaAnimalsResponse>>>(UIState.Loading())
    val seaAnimalsFlow: StateFlow<UIState<List<SeaAnimalsResponse>>> = _seaAnimalsFlow

    fun loadSeaAnimals() {
        viewModelScope.launch {
            apiRepository.fetchSeaAnimals()
                .catch { exception ->
                    when (exception) {
                        is IOException -> _seaAnimalsFlow.value = UIState.Error("Network failure")
                        else -> _seaAnimalsFlow.value = UIState.Error("Conversion error")
                    }
                }
                .collect {
                    _seaAnimalsFlow.value = UIState.Success(it)
                }
        }
    }


}
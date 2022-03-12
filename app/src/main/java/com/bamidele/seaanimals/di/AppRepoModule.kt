package com.bamidele.seaanimals.di

import com.bamidele.seaanimals.data.network.ApiRepository
import com.bamidele.seaanimals.data.network.ApiRepositoryImpl
import com.bamidele.seaanimals.data.network.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class AppRepoModule {
    @Provides
    fun providesApiRepository(apiServices: ApiServices): ApiRepository =
        ApiRepositoryImpl(apiServices)
}

@InstallIn(SingletonComponent::class)
@Module
class ApiServiceModule {
    @Provides
    fun providesApiServices(retrofit: Retrofit): ApiServices = ApiServices.create(retrofit)
}
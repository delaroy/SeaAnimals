package com.bamidele.seaanimals.hilt

import com.bamidele.seaanimals.api.FakeApiService
import com.bamidele.seaanimals.data.network.ApiServices
import com.bamidele.seaanimals.di.ApiServiceModule
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [ApiServiceModule::class])
abstract class FakeApiServiceModule {

    @Binds
    @Singleton
    abstract fun providesApiServices(fakeApiService: FakeApiService): ApiServices
}
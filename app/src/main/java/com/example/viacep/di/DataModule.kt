package com.example.viacep.di

import com.example.viacep.data.api.ServiceApi
import com.example.viacep.network.ServiceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    /**
     * @Provides: uma anotação utilizada para indicar que um
     * método de um módulo é responsável por prover uma instância de uma determinada classe.
     */

    @Provides
    fun providesServiceApi(serviceProvides: ServiceProvider) : ServiceApi {
        return serviceProvides.createService(ServiceApi::class.java)
    }
}
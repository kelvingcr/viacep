package com.example.viacep.di

import com.example.viacep.network.ServiceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    /**
     * Crie uma única instancia e funcione para o projeto todoo.
     * (SingletonComponent::class)
     */

    @Provides
    fun providesServiceProvider() = ServiceProvider
}
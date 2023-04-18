package com.example.viacep.di

import com.example.viacep.data.local.repository.AddressLocalRepositoryImpl
import com.example.viacep.data.remote.repository.AddressRepositoryImpl
import com.example.viacep.domain.api.repository.AddressRepository
import com.example.viacep.domain.local.repository.AddressLocalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DomainModule {

    /**
     *  @Binds:
     *  uma anotação utilizada para indicar que um método
     *  é responsável por fornecer uma instância de uma classe que implementa uma interface ou classe abstrata.
     */

    /*
     * A gente está vinculando a Impl a uma interface
     * Ou seja, AddressRepositoryImpl(implementação) está vinculando com AddressRepository(interface)
     */
    @Binds
    abstract fun bindsAddressRepositoryImpl(addressRepositoryImpl: AddressRepositoryImpl) : AddressRepository

    @Binds
    abstract fun bindsAddressLocalRepositoryImpl(addressLocalRepositoryImpl: AddressLocalRepositoryImpl) : AddressLocalRepository
}
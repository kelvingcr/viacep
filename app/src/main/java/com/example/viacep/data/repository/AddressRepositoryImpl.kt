package com.example.viacep.data.repository

import com.example.viacep.data.api.ServiceApi
import com.example.viacep.data.model.AddressResponse
import com.example.viacep.domain.repository.AddressRepository
import javax.inject.Inject

/**
 * Essa classe é uma implementação de uma interface
 */

/**
 * @Inject: uma anotação utilizada para indicar que uma propriedade,
 * construtor ou método precisa de uma instância de uma determinada classe para funcionar corretamente.
 */

                                                //Dependende de ServiceApi
class AddressRepositoryImpl @Inject constructor( private val serviceApi:ServiceApi ): AddressRepository {

    override suspend fun getAddress(cep: String): AddressResponse {
       return serviceApi.getAddress(cep)
    }
}
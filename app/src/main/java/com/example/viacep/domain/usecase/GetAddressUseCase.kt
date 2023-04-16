package com.example.viacep.domain.usecase

import com.example.viacep.data.mapper.toDomain
import com.example.viacep.domain.model.Address
import com.example.viacep.domain.repository.AddressRepository
import javax.inject.Inject


/**
 * @Inject: uma anotação utilizada para indicar que uma propriedade,
 * construtor ou método precisa de uma instância de uma determinada classe para funcionar corretamente.
 */

class GetAddressUseCase @Inject constructor(
    private val repository: AddressRepository //Recebeu a instancia de AddressRepositoryImpl
) {

    /**
     * Faça com que o Objeto GetAddressUseCase
     * fosse usado como função "suspend operator fun invoke"
     * "getAddressUseCase(cep)"
     */

    suspend operator fun invoke(cep: String): Address {
        return repository.getAddress(cep).toDomain()
    }

}
package com.example.viacep.domain.api.usecase

import com.example.viacep.data.mapper.toDomain
import com.example.viacep.domain.model.Address
import com.example.viacep.domain.api.repository.AddressRepository
import javax.inject.Inject


/**
 * @Inject: uma anotação utilizada para indicar que uma propriedade,
 * construtor ou método precisa de uma instância de uma determinada classe para funcionar corretamente.
 */

class GetAddressUseCase @Inject constructor(
    private val repository: AddressRepository //Recebeu a instancia de AddressRepositoryImpl
) {

    /**
        O operador invoke é um operador padrão em Kotlin que é usado para chamar uma instância de classe como se fosse uma função.
        getAllAddressUseCase.invoke()
     */

    suspend operator fun invoke(cep: String): Address {
        return repository.getAddress(cep).toDomain()
    }

}
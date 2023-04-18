package com.example.viacep.domain.local.usecase

import com.example.viacep.data.local.entity.AddressEntity
import com.example.viacep.domain.local.repository.AddressLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAddressByIdUseCase @Inject constructor(private val repository: AddressLocalRepository) {

    /**
    O operador invoke é um operador padrão em Kotlin que é usado para chamar uma instância de classe como se fosse uma função.
    getAddressByIdUseCase.invoke(id)
     */

    suspend operator fun invoke(id: Int) : AddressEntity? {
        return repository.getAddressById(id)
    }
}
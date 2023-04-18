package com.example.viacep.domain.local.usecase

import com.example.viacep.data.local.entity.AddressEntity
import com.example.viacep.data.mapper.toEntity
import com.example.viacep.domain.local.repository.AddressLocalRepository
import com.example.viacep.domain.model.Address
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteAddressUseCase @Inject constructor(private val repository: AddressLocalRepository) {

    /**
    O operador invoke é um operador padrão em Kotlin que é usado para chamar uma instância de classe como se fosse uma função.
    deleteAddressUseCase.invoke(address)
     */

    suspend operator fun invoke(address: Address) {
        return repository.deleteAddress(address.toEntity())
    }
}
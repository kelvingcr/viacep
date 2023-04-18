package com.example.viacep.data.local.repository

import com.example.viacep.data.local.dao.AddressDao
import com.example.viacep.data.local.entity.AddressEntity
import com.example.viacep.domain.local.repository.AddressLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * AddressLocalRepositoryImpl é a implementação de AddressLocalRepository
 * Server para separar a camada de dominio e a de dados.
 * E para facilitar novas features e manutenções;
 */

class AddressLocalRepositoryImpl @Inject constructor(private val addressDao: AddressDao) : AddressLocalRepository {

    override fun getAllAddress(): Flow<List<AddressEntity>> {
        return addressDao.getAllAddress()
    }

    override suspend fun getAddressById(id: Int): AddressEntity? {
        return addressDao.getAddressById(id)
    }

    override suspend fun insertAddress(addressEntity: AddressEntity): Long {
        return addressDao.insertAddress(addressEntity)
    }

    override suspend fun updateAddress(addressEntity: AddressEntity) {
        return addressDao.updateAddress(addressEntity)
    }

    override suspend fun deleteAddress(addressEntity: AddressEntity) {
        return addressDao.deleteAddress(addressEntity)
    }
}
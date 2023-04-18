package com.example.viacep.domain.local.repository

import androidx.room.*
import com.example.viacep.data.local.entity.AddressEntity
import kotlinx.coroutines.flow.Flow

/**
 * Classe criada para separar a interface da implementação
 * Consequentemente você poderá usar esse mesmo método em diversas classes
 * Sem alterar interferencias.
 */

interface AddressLocalRepository {


    fun getAllAddress(): Flow<List<AddressEntity>>

    suspend fun getAddressById(id: Int): AddressEntity?

    suspend fun insertAddress(addressEntity: AddressEntity) : Long

    suspend fun updateAddress(addressEntity: AddressEntity)

    suspend fun deleteAddress(addressEntity: AddressEntity)
}
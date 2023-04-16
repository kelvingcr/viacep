package com.example.viacep.domain.repository

import com.example.viacep.data.model.AddressResponse
import com.example.viacep.domain.model.Address


/**
 * Classe criada para separar a interface da implementação
 * Consequentemente você poderá usar esse mesmo método em diversas classes
 * Sem alterar interferencias.
 */

interface AddressRepository {
    suspend fun getAddress(cep: String): AddressResponse
}
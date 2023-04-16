package com.example.viacep.data.mapper

import com.example.viacep.data.model.AddressResponse
import com.example.viacep.domain.model.Address

/**
 * Arquivo usado para converter AddressResponse em Address
 * Pois algumas propriedades de AddressResponse não precisamos.
 */

fun AddressResponse.toDomain() : Address {
    return Address(
        cep = cep,
        localidade = localidade,
        bairro = bairro,
        complemento = complemento,
        logradouro = logradouro,
        uf = uf
    )
}
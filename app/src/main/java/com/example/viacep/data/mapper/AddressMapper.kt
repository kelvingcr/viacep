package com.example.viacep.data.mapper

import com.example.viacep.data.local.entity.AddressEntity
import com.example.viacep.data.remote.model.AddressResponse
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

fun Address.toEntity() : AddressEntity {
    return AddressEntity(
        id = id,
        cep = cep,
        localidade = localidade,
        bairro = bairro,
        complemento = complemento,
        uf = uf,
        logradouro = logradouro
    )
}

fun AddressEntity.toDomain() : Address {
    return Address(
        id = id,
        cep = cep,
        localidade = localidade,
        bairro = bairro,
        complemento = complemento,
        uf = uf,
        logradouro = logradouro,
    )
}
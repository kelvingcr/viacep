package com.example.viacep.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity é o objeto que vai ser salvo no banco de dados.
 * cada variavel terá sua coluna.
 */

@Entity(tableName = "address")
data class AddressEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val bairro: String?,
    val cep: String?,
    val complemento: String?,
    val localidade: String?,
    val logradouro: String?,
    val uf: String?
)

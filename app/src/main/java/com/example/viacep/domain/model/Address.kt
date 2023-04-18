package com.example.viacep.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Objeto principal do aplicativo
 * São feitas mapeamentos para essas classes.
 * (essa classe pode ser usada para mapear outras)
 */
@Parcelize
data class Address(
    val id: Int = 0,
    val bairro: String?,
    val cep: String?,
    val complemento: String?,
    val localidade: String?,
    val logradouro: String?,
    val uf: String?
) : Parcelable {
    fun getFullAddress(): String {
        return "$logradouro - $bairro\n$localidade/$uf - $cep"
    }
}
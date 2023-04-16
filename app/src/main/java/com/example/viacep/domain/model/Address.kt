package com.example.viacep.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Address(
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
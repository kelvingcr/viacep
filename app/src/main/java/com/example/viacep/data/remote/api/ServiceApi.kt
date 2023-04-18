package com.example.viacep.data.remote.api

import com.example.viacep.data.remote.model.AddressResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Nessa classe fica todos os ends points da api.
 *
 * Obs: Ela precisa ser usada pelo retrofit
 * Para usar basta chamar o método  "serviceProvides.createService"
 */

interface ServiceApi {
    @GET("{cep}/json/")
    suspend fun getAddress(@Path("cep") cep: String) : AddressResponse
}
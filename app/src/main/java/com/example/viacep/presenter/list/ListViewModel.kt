package com.example.viacep.presenter.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.viacep.domain.model.Address
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Inject: uma anotação utilizada para indicar que uma propriedade,
 * construtor ou método precisa de uma instância de uma determinada classe para funcionar corretamente.
 */


@HiltViewModel
class ListViewModel @Inject constructor() : ViewModel() {

    private val _addressList = MutableLiveData<MutableList<Address>>()
    val currentAddressList: LiveData<MutableList<Address>> = _addressList

    fun insertAddress(address: Address) {
        val currentList = _addressList.value ?: mutableListOf()
        currentList.add(address)
        _addressList.value = currentList
    }
}
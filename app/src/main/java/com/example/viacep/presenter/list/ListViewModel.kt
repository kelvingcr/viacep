package com.example.viacep.presenter.list

import androidx.lifecycle.*
import com.example.viacep.data.mapper.toDomain
import com.example.viacep.domain.local.usecase.DeleteAddressUseCase
import com.example.viacep.domain.local.usecase.GetAllAddressUseCase
import com.example.viacep.domain.model.Address
import com.example.viacep.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Inject: uma anotação utilizada para indicar que uma propriedade,
 * construtor ou método precisa de uma instância de uma determinada classe para funcionar corretamente.
 */

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getAllAddressUseCase: GetAllAddressUseCase,
    private val deleteAddressUseCase: DeleteAddressUseCase,
) : ViewModel() {

    private val _addressList = MutableLiveData(mutableListOf<Address>())
    val addressList: LiveData<MutableList<Address>> = _addressList

    /**
     * Esse LiveData só server de aviso ao Fragment
     * Por que recebe um "Unit", ou seja 'função não retorna nenhum valor útil'
     */
    private val _addressChanged = MutableLiveData(Unit)
    val addressChanged : LiveData<Unit> = _addressChanged

    /** Toda vez que o viewModel for chamado, irá chamar essa função.
     * que notificará o observado "addressList" */
/*
    init {
        getAllAddress()
    }
*/

    fun getAllAddress() = viewModelScope.launch {
        getAllAddressUseCase.invoke().collect { addresses ->
            _addressList.value = addresses.map {
                it.toDomain() //Passe por cada AddressEntity e transfome num Address (Domain)
            }.toMutableList() //Transforme esses dados novamente em uma lista.
        }
    }

    fun deleteAddress(address: Address) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())
            val response = deleteAddressUseCase.invoke(address)
            emit(StateView.Success(response))
        }catch (e: java.lang.Exception) {
            e.printStackTrace()
            emit(StateView.Error(e.message))
        }
    }

    /**
     * Nessa função o addressChanged() está avisando o "_addressChanged" que houve uma mudança
     * Porém não quero que trate, somente avise o observador "addressChanged"
     */
    fun addressChanged() {
        _addressChanged.value = Unit
    }
}
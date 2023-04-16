package com.example.viacep.presenter.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.viacep.domain.usecase.GetAddressUseCase
import com.example.viacep.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * @Inject: uma anotação utilizada para indicar que uma propriedade,
 * construtor ou método precisa de uma instância de uma determinada classe para funcionar corretamente.
 */

/**
 * emit(value: T) é  é um método público da classe LiveData em Android
 * e é usada para adicionar um LiveData como fonte de dados para outro LiveData.
 * Essa função é semelhante ao método addSource da classe MediatorLiveData.
 */


@HiltViewModel
class SearchViewModel @Inject constructor(private val getAddressUseCase: GetAddressUseCase)
    : ViewModel() {

    fun getAddress(cep: String) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())
            val address = getAddressUseCase(cep)
            emit(StateView.Success(address))

        }catch (e: java.lang.Exception) {
            e.printStackTrace()
            emit(StateView.Error(e.message))
        }

    }
}
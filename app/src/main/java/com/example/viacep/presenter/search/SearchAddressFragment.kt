package com.example.viacep.presenter.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.viacep.R
import com.example.viacep.databinding.FragmentSearchAddressBinding
import com.example.viacep.domain.model.Address
import com.example.viacep.util.Constants
import com.example.viacep.util.StateView
import com.example.viacep.util.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchAddressFragment : Fragment() {

    private var _binding: FragmentSearchAddressBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by viewModels()
    private var address: Address? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
    }

    private fun initListeners() {
        binding.editCep.addTextChangedListener {
            val text = it.toString() ?: ""
            if(text.isNotEmpty()) {
                if(text.length == 8) {
                    hideKeyboard()
                    getAddress(cep = text)
                }
            }
        }

        binding.btnSave.setOnClickListener {
            if(address != null) {
                parentFragmentManager.setFragmentResult(
                    Constants.REQUEST_KEY,
                    bundleOf(Pair(Constants.ADDRESS_BUNDLE_KEY, address))
                )
            }
            findNavController().popBackStack()
        }
    }

    /** Realizando a requisição... */
    private fun getAddress(cep: String) {
        viewModel.getAddress(cep).observe(viewLifecycleOwner) { stateView ->
            when(stateView) {
                is StateView.Loading -> { /** Procurando... */
                    binding.btnSave.isEnabled = false
                    binding.itemAddress.viewFlipper.displayedChild = 2 //Exibi a progressbar
                }
                is StateView.Success -> { /** Requisição bem-sucedida. */
                    if(stateView.data?.cep != null) { // Existe algum dado no "cep"?
                        address = stateView.data
                        binding.btnSave.isEnabled = true
                        binding.itemAddress.viewFlipper.displayedChild = 1
                        binding.itemAddress.textAddress.text = address?.getFullAddress()
                    }else {
                        stateError()
                    }
                }
                is StateView.Error -> {
                    stateError()
                }
            }
        }
    }

    private fun stateError() {
        address = null
        binding.btnSave.isEnabled = false
        binding.itemAddress.viewFlipper.displayedChild = 0
        binding.itemAddress.textEmptyAddress.text = getString(R.string.label_address_empty_search_address_fragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.viacep.presenter.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.viacep.R
import com.example.viacep.databinding.FragmentListAddressBinding
import com.example.viacep.domain.model.Address
import com.example.viacep.presenter.list.adapter.AddressAdapter
import com.example.viacep.util.Constants
import com.example.viacep.util.getParcelableCompat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListAddressFragment : Fragment() {

    private var _binding: FragmentListAddressBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ListViewModel by viewModels()

    private lateinit var addressAdapter: AddressAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initObserver()
        initListeners()
    }

    private fun initListeners() {
        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listAddressFragment_to_searchAddressFragment)
        }

        parentFragmentManager.setFragmentResultListener(
            Constants.REQUEST_KEY, this) { _, bundle ->
            val address = bundle.getParcelableCompat(Constants.ADDRESS_BUNDLE_KEY, Address::class.java)
            if(address != null) {
                viewModel.insertAddress(address)
            }
        }
    }

    private fun initObserver() {
        viewModel.currentAddressList.observe(viewLifecycleOwner) { address ->
            addressAdapter.submitList(address)
        }
    }

    private fun initRecycler() {
        addressAdapter = AddressAdapter()
        with(binding.recyclerAddress) {
            adapter = addressAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}
package com.example.viacep.presenter.search

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.viacep.R
import com.example.viacep.databinding.FragmentSearchAddressBinding
import com.example.viacep.domain.model.Address
import com.example.viacep.presenter.list.ListViewModel
import com.example.viacep.util.Constants
import com.example.viacep.util.StateView
import com.example.viacep.util.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchAddressFragment : Fragment() {

    private var _binding: FragmentSearchAddressBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by viewModels()

    /**
     * A delegação por activityViewModels() cria uma instância compartilhada da
     * ViewModel associada à atividade do fragmento, para que ela possa ser compartilhada entre vários fragmentos dessa atividade.
     * Dessa forma, cada fragmento que usa a ViewModel pode acessar o mesmo objeto ViewModel e compartilhar dados e estados entre si.
     * Evitando A delegação por activityViewModels() cria uma instância compartilhada da ViewModel
     * associada à atividade do fragmento, para que ela possa ser compartilhada entre vários fragmentos dessa atividade. Dessa forma, cada fragmento que usa a ViewModel pode acessar o mesmo objeto ViewModel e compartilhar dados e estados entre si.
     */
    private val listAddressViewModel: ListViewModel by activityViewModels()
    private var address: Address? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**Define o titulo da actionBar*/
        requireActivity().title = "Novo endereço"
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

        /**
         * Inicia a animação de sucesso após clicar no botão "SALVAR"
         * logo depois salva o address no banco de dados.
         */
        binding.btnSave.setOnClickListener {
            if(address != null) {
                binding.lottieSuccess.playAnimation()
                binding.lottieSuccess.addAnimatorListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(p0: Animator) {}

                    override fun onAnimationEnd(p0: Animator) {
                        insertAddress(address!!)
                    }
                    override fun onAnimationCancel(p0: Animator) {}

                    override fun onAnimationRepeat(p0: Animator) {}

                })
            } else {
                findNavController().popBackStack()
            }
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

    private fun insertAddress(address: Address) {
        viewModel.insertAddress(address).observe(viewLifecycleOwner) { stateView ->
            when(stateView) {
                is StateView.Loading -> {
                }
                is StateView.Success -> {
                    listAddressViewModel.addressChanged()
                    findNavController().popBackStack()
                    Toast.makeText(requireContext(), "Endereço salvo com sucesso.", Toast.LENGTH_SHORT).show()
                }
                is StateView.Error -> {
                    Toast.makeText(requireContext(), "Erro ao salvar o endereço", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
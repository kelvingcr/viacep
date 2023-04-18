package com.example.viacep.presenter.list

import android.animation.Animator
import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.viacep.R
import com.example.viacep.databinding.FragmentListAddressBinding
import com.example.viacep.domain.model.Address
import com.example.viacep.presenter.list.adapter.AddressAdapter
import com.example.viacep.util.StateView
import com.tsuryo.swipeablerv.SwipeLeftRightCallback
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.notify

@AndroidEntryPoint
class ListAddressFragment : Fragment() {

    private var _binding: FragmentListAddressBinding? = null
    private val binding get() = _binding!!

    /**
     * A delegação por activityViewModels() cria uma instância compartilhada da
     * ViewModel associada à atividade do fragmento, para que ela possa ser compartilhada entre vários fragmentos dessa atividade.
     * Dessa forma, cada fragmento que usa a ViewModel pode acessar o mesmo objeto ViewModel e compartilhar dados e estados entre si.
     * Evitando A delegação por activityViewModels() cria uma instância compartilhada da ViewModel
     * associada à atividade do fragmento, para que ela possa ser compartilhada entre vários fragmentos dessa atividade. Dessa forma, cada fragmento que usa a ViewModel pode acessar o mesmo objeto ViewModel e compartilhar dados e estados entre si.
     */
    private val viewModel: ListViewModel by activityViewModels()

    private lateinit var addressAdapter: AddressAdapter
    private var isAnimationCalled = false

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
        /**Define o titulo da actionBar*/
        requireActivity().title = "Endereços salvos"

        /** Verificação para chamar essa animação somente
         * 1 vez durante a inicialização do app */
        if(!isAnimationCalled) {
            startAnimationLittie()
            isAnimationCalled = true
        } else {
            initRecycler()
            initObserver()
            initListeners()
        }
    }

    private fun initListeners() {
        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listAddressFragment_to_searchAddressFragment)
        }

        binding.recyclerAddress.setListener(object : SwipeLeftRightCallback.Listener{
            override fun onSwipedLeft(position: Int) {
                val addressRecovery = addressAdapter.currentList[position]
                viewModel.deleteAddress(addressRecovery).observe(viewLifecycleOwner) {
                    when(it) {
                        is StateView.Error -> {
                        }
                        is StateView.Loading -> {
                        }
                        is StateView.Success -> {
                            viewModel.addressChanged()
                        }
                    }
                }
            }

            override fun onSwipedRight(position: Int) {
                println("onSwipedRight")
            }

        })
    }

    private fun initObserver() {
        /**
         * Fica observando se entrará algum dado
         * Se entrar, chamará o getAllAddress() que consequentemente o observer "addressList"
         * que avisará o recyclerView
         */
        viewModel.addressChanged.observe(viewLifecycleOwner) {
           viewModel.getAllAddress()
        }
        /**
         * A classe ListViewModel tem um init que puxa todos os dados do banco de dados
         * automaticamente quando ela é instancia.
         * Esse observer só está adicionando ao recyclerView os itens;
         * */
        viewModel.addressList.observe(viewLifecycleOwner) { address ->
            addressAdapter.submitList(address)
        }
    }

    private fun initRecycler() {
        addressAdapter = AddressAdapter()
        with(binding.recyclerAddress) {//Parecido com o .apply
            adapter = addressAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Inicia a animações e ao acabar inicia os observers, recycler e listeners
     */
    private fun startAnimationLittie() {

        binding.lottieSearch.visibility = View.VISIBLE
        binding.lottieSearch.playAnimation()
        binding.lottieSearch.addAnimatorListener(object : Animator.AnimatorListener {

            override fun onAnimationStart(p0: Animator) {}
            override fun onAnimationEnd(p0: Animator) {
                initRecycler()
                initObserver()
                initListeners()
                viewModel.getAllAddress()
                binding.lottieSearch.visibility = View.GONE
            }

            override fun onAnimationCancel(p0: Animator) {}
            override fun onAnimationRepeat(p0: Animator) {}

        })
    }


}
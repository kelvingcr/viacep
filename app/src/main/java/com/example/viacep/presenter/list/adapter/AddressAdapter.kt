package com.example.viacep.presenter.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.viacep.databinding.ItemAddressBinding
import com.example.viacep.domain.model.Address

class AddressAdapter constructor(private val onItemClicked: (Address) -> Unit): ListAdapter<Address, AddressAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Address>() {
            override fun areItemsTheSame(oldItem: Address, newItem: Address): Boolean {
                return oldItem.cep == newItem.cep
            }

            override fun areContentsTheSame(oldItem: Address, newItem: Address): Boolean {
                return newItem.cep == oldItem.cep
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAddressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val address = getItem(position)
        holder.binding.imgRouting.visibility = View.VISIBLE

        holder.binding.textAddress.text = address.getFullAddress()

        holder.binding.viewFlipper.displayedChild = 1

        holder.binding.imgRouting.setOnClickListener {
            onItemClicked(address)
        }
    }
    inner class ViewHolder(val binding: ItemAddressBinding) : RecyclerView.ViewHolder(binding.root)
}
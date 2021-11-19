package com.vaasugambhir.furniturekart.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vaasugambhir.furniturekart.R
import com.vaasugambhir.furniturekart.data.room.ProductEntity
import com.vaasugambhir.furniturekart.databinding.FragmentOrderBinding
import com.vaasugambhir.furniturekart.ui.adapter.ProductAdapter
import com.vaasugambhir.furniturekart.ui.interfaces.OnProductClickListener
import com.vaasugambhir.furniturekart.viewmodel.ProductVMFactory
import com.vaasugambhir.furniturekart.viewmodel.ProductViewModel

class OrdersFragment: Fragment() {
    private lateinit var binding: FragmentOrderBinding
    private lateinit var adapter: ProductAdapter
    private lateinit var viewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            requireActivity(),
            ProductVMFactory(requireActivity().application)
        )[ProductViewModel::class.java]

        adapter = ProductAdapter(requireContext(), true)
        adapter.setOnClickListeners(object : OnProductClickListener {
            override fun onProductClick(product: ProductEntity) {
                // nothing
            }

            override fun onDeleteClick(product: ProductEntity) {
                viewModel.delete(product)
            }

            override fun onOrderClick(product: ProductEntity) {
                // nothing
            }
        })
        binding.ordersRV.adapter = adapter
        binding.ordersRV.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getAllProducts().observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }
}
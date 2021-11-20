package com.vaasugambhir.furniturekart.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vaasugambhir.furniturekart.R
import com.vaasugambhir.furniturekart.data.room.ProductEntity
import com.vaasugambhir.furniturekart.databinding.FragmentShopBinding
import com.vaasugambhir.furniturekart.ui.activities.ArDisplayActivity
import com.vaasugambhir.furniturekart.ui.adapter.ProductAdapter
import com.vaasugambhir.furniturekart.ui.dialog.ConfirmDialog
import com.vaasugambhir.furniturekart.ui.interfaces.OnProductClickListener
import com.vaasugambhir.furniturekart.utils.Utils
import com.vaasugambhir.furniturekart.viewmodel.ProductVMFactory
import com.vaasugambhir.furniturekart.viewmodel.ProductViewModel

class ShopFragment: Fragment() {
    private lateinit var binding: FragmentShopBinding
    private lateinit var adapter: ProductAdapter
    private lateinit var viewModel: ProductViewModel
    private lateinit var alert: ConfirmDialog
    private val timer = object : CountDownTimer(1000,1000) {
        override fun onTick(p0: Long) {}

        override fun onFinish() {
            alert.dismissDialog()
        }

    }

    private val array: List<ProductEntity> = listOf(
        ProductEntity("1", R.drawable.bed1p, R.raw.bed1, "Bed 1"),
        ProductEntity("2", R.drawable.bed2p, R.raw.bed2, "Bed 2"),
        ProductEntity("3", R.drawable.bed3p, R.raw.bed3, "Bed 3"),
        ProductEntity("4", R.drawable.bed4p, R.raw.bed4, "Bed 4"),
        ProductEntity("5", R.drawable.bed5p, R.raw.bed5, "Bed 5"),

        ProductEntity("6", R.drawable.chair1p, R.raw.chair1, "Chair 1"),
        ProductEntity("7", R.drawable.chair2p, R.raw.chair2, "Chair 2"),
        ProductEntity("8", R.drawable.chair3p, R.raw.chair3, "Chair 3"),
        ProductEntity("9", R.drawable.chair4p, R.raw.chair4, "Chair 4"),
        ProductEntity("10", R.drawable.chair5p, R.raw.chair5, "Chair 5"),

        ProductEntity("11", R.drawable.table1p, R.raw.table1, "Table 1"),
        ProductEntity("12", R.drawable.table2p, R.raw.table2, "Table 2"),
        ProductEntity("13", R.drawable.table3p, R.raw.table3, "Table 3"),
        ProductEntity("14", R.drawable.table4p, R.raw.table4, "Table 4"),
        ProductEntity("15", R.drawable.table5p, R.raw.table5, "Table 5"),

        ProductEntity("16", R.drawable.wardrobe1p, R.raw.wardrobe1, "Wardrobe 1"),
        ProductEntity("17", R.drawable.wardrobe2p, R.raw.wardrobe2, "Wardrobe 2"),
        ProductEntity("18", R.drawable.wardrobe3p, R.raw.wardrobe3, "Wardrobe 3"),
        ProductEntity("19", R.drawable.wardrobe4p, R.raw.wardrobe4, "Wardrobe 4"),
        ProductEntity("20", R.drawable.wardrobe5p, R.raw.wardrobe5, "Wardrobe 5"),
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShopBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        alert = ConfirmDialog(requireActivity())
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        viewModel = ViewModelProvider(
            requireActivity(),
            ProductVMFactory(requireActivity().application)
        )[ProductViewModel::class.java]

        adapter = ProductAdapter(requireContext(), false)
        adapter.setOnClickListeners(object : OnProductClickListener {
            override fun onProductClick(product: ProductEntity) {
                val intent = Intent(requireContext(), ArDisplayActivity::class.java)
                intent.putExtra(Utils.AR_INTENT, product.arRaw)
                startActivity(intent)
            }

            override fun onDeleteClick(product: ProductEntity) {
                // nothing
            }

            override fun onOrderClick(product: ProductEntity) {
                viewModel.insert(product)
                alert.startLoading()
                timer.start()
            }
        })
        binding.productsRV.adapter = adapter
        binding.productsRV.layoutManager = LinearLayoutManager(requireContext())

        adapter.submitList(array)
    }
}
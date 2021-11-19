package com.vaasugambhir.furniturekart.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vaasugambhir.furniturekart.databinding.FragmentShopBinding
import com.vaasugambhir.furniturekart.ui.activities.ArDisplayActivity

class ShopFragment: Fragment() {
    private lateinit var binding: FragmentShopBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShopBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.buttonOpenAr.setOnClickListener {
            val intent = Intent(requireContext(), ArDisplayActivity::class.java)
            requireActivity().startActivity(intent)
        }
    }
}
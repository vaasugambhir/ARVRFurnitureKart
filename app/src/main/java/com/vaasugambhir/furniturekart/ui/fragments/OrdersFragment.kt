package com.vaasugambhir.furniturekart.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vaasugambhir.furniturekart.databinding.FragmentOrderBinding

class OrdersFragment: Fragment() {
    private lateinit var binding: FragmentOrderBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderBinding.inflate(layoutInflater)
        return binding.root
    }
}
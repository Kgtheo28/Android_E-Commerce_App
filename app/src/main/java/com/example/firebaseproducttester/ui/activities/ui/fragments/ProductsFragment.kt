package com.example.firebaseproducttester.ui.activities.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.firebaseproducttester.R
import com.example.firebaseproducttester.databinding.FragmentProductsBinding
import com.example.firebaseproducttester.ui.activities.activities.MainActivity
import com.example.firebaseproducttester.ui.activities.activities.ProductDetailsActivity
import com.example.firebaseproducttester.ui.activities.activities.ScrollViewActivity


class ProductsFragment : Fragment(R.layout.fragment_products) {

    private var _binding: FragmentProductsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // val homeViewModel =  ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        // initiate functions here
        binding.button5.setOnClickListener {
            val intent = Intent(requireContext(), ProductDetailsActivity::class.java)
            startActivity(intent)
        }
        binding.button3.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }

        return root

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

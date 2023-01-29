package com.example.firebaseproducttester.ui.activities.activities.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.firebaseproducttester.databinding.FragmentHomeBinding
import com.example.firebaseproducttester.ui.activities.activities.AddingProductsActivity
import com.example.firebaseproducttester.ui.activities.activities.FetchingDataActivity
import com.example.firebaseproducttester.ui.activities.activities.MainActivity
import com.example.firebaseproducttester.ui.activities.activities.ProductDetailsActivity

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    lateinit var fab: Unit
    // This property is only valid between onCreateView and
    // onDestroyView.


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        binding = FragmentHomeBinding.inflate(layoutInflater)
        val root: View = binding!!.root

        binding.imageView7.setOnClickListener {
            val intent = Intent(requireContext(), ProductDetailsActivity::class.java)
            startActivity(intent)
        }
        binding.textView10.setOnClickListener {
            val intent = Intent(requireContext(), ProductDetailsActivity::class.java)
            startActivity(intent)
        }

        fab = binding.floatingActionButton.setOnClickListener{
            val intent = Intent(requireContext(), AddingProductsActivity::class.java)
            startActivity(intent)
        }

        binding.button6.setOnClickListener {
            val intent = Intent(requireContext(), FetchingDataActivity::class.java)
            startActivity(intent)
        }




        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding
    }
}
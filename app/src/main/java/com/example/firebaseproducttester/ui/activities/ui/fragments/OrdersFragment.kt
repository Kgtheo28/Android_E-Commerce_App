package com.example.firebaseproducttester.ui.activities.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebaseproducttester.adapter.MyProductsAdapter
import com.example.firebaseproducttester.databinding.FragmentOrdersBinding
import com.example.firebaseproducttester.models.ProductsModel

class OrdersFragment : Fragment()  {

    private var _binding: FragmentOrdersBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // val notificationsViewModel =  ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentOrdersBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerview = binding.recyclerview

        recyclerview.layoutManager = LinearLayoutManager(context)

        getItemsList()

        return root
    }

    private fun getItemsList(): ArrayList<String> {
        val list = ArrayList<String>()

        for(i in 1..10){
            list.add("Item $i")
        }
        return list
    }
}
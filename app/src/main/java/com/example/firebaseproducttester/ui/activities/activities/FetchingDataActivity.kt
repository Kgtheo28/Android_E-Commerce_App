package com.example.firebaseproducttester.ui.activities.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseproducttester.R
import com.example.firebaseproducttester.adapter.AddProductsAdaptor
import com.example.firebaseproducttester.databinding.ActivityAddingProductsBinding
import com.example.firebaseproducttester.databinding.ActivityFetchingDataBinding
import com.example.firebaseproducttester.models.ProductsModel
import com.google.firebase.database.*

class FetchingDataActivity : BaseActivity() {

    private lateinit var binding : ActivityFetchingDataBinding
    private lateinit var productsRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var productList: ArrayList<ProductsModel>

    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFetchingDataBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        productsRecyclerView = binding.rvProducts
        productsRecyclerView.layoutManager = LinearLayoutManager(this)
        productsRecyclerView.setHasFixedSize(true)
        tvLoadingData = binding.tvLoadingData

        productList = arrayListOf<ProductsModel>()

        getProductData()
    }

    private fun getProductData() {
        productsRecyclerView.visibility = View.VISIBLE
        tvLoadingData.visibility = View.GONE

        databaseReference = FirebaseDatabase.getInstance().getReference("Products")

        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                productList.clear()
                if (snapshot.exists()){
                    for (productSnap in snapshot.children){
                        val productData = productSnap.getValue(ProductsModel::class.java)
                        productList.add(productData!!)
                    }
                    val mAdapter = AddProductsAdaptor(productList)
                    productsRecyclerView.adapter = mAdapter

                    productsRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}
package com.example.firebaseproducttester.ui.activities.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firebaseproducttester.Utils.Constants
import com.example.firebaseproducttester.Utils.SpaceItemDecoration
import com.example.firebaseproducttester.adapter.MyProductsAdapter
import com.example.firebaseproducttester.databinding.ActivityMainBinding
import com.example.firebaseproducttester.listener.ProductsLoadListener
import com.example.firebaseproducttester.models.ProductsModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity(), ProductsLoadListener {

    private lateinit var binding: ActivityMainBinding

    lateinit var productsLoadListener: ProductsLoadListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val sharedPreferences =
            getSharedPreferences(
                Constants.MYSHOPPAL_PREFERENCES,
                Context.MODE_PRIVATE
            )

        init()
        loadProductsFromFirebase()


    }

    private fun loadProductsFromFirebase() {
        val productModels : MutableList<ProductsModel> = ArrayList()
        FirebaseDatabase.getInstance()
            .getReference("products")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        for(productsSnapshot in snapshot.children){
                            val productsModel = productsSnapshot.getValue(ProductsModel::class.java)
                            productsModel!!.key = productsSnapshot.key
                            productModels.add(productsModel)
                        }
                        productsLoadListener.onProductsLoadSuccess(productModels)
                    }else
                        productsLoadListener.onProductsLoadFailed("Your item does not exist")
                }

                override fun onCancelled(error: DatabaseError) {
                    productsLoadListener.onProductsLoadFailed(error.message)
                }
            })
    }

    private fun init() {
        productsLoadListener = this

        val gridLayoutManager = GridLayoutManager(this, 2)
        binding.recyclerProducts.layoutManager = gridLayoutManager
        binding.recyclerProducts.addItemDecoration(SpaceItemDecoration())

    }


    override fun onProductsLoadSuccess(productModelList: List<ProductsModel>) {
        val adapter = MyProductsAdapter(this, productModelList as ArrayList<ProductsModel>)
        binding.recyclerProducts.adapter = adapter
    }

    override fun onProductsLoadFailed(message: String) {
        println("you have failed to uploaded the product details")
    }
}

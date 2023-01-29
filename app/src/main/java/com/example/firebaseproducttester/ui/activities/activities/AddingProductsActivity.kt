package com.example.firebaseproducttester.ui.activities.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import com.example.firebaseproducttester.R
import com.example.firebaseproducttester.databinding.ActivityAddingProductsBinding
import com.example.firebaseproducttester.models.ProductValuesModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AddingProductsActivity : BaseActivity() {

    private lateinit var binding: ActivityAddingProductsBinding

    private lateinit var databaseRef: DatabaseReference

    private lateinit var productName: EditText
    private lateinit var sellingPrice: EditText
    private lateinit var shippingCostInt: EditText
    private lateinit var shippingCostRsa: EditText
    private lateinit var submitButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddingProductsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        productName = binding.addProductName
        sellingPrice = binding.addProductSellingPrice
        shippingCostInt = binding.addShippingPriceInt
        shippingCostRsa = binding.addShippingPriceRsa
        submitButton = binding.floatingActionButton2

        databaseRef = FirebaseDatabase.getInstance().getReference("Products")

        submitButton.setOnClickListener {
            addNewProduct()
        }

    }

    private fun addNewProduct() {

        // getting values
        val productName = productName.text.toString()
        val sellingPrice = sellingPrice.text.toString()
        val shippingCostRsa = shippingCostRsa.text.toString()
        val shippingCostInt = shippingCostInt.text.toString()

        if(validateProductDetails()){
            showProgressDialog(resources.getString(R.string.please_wait))

            databaseRef = Firebase.database.reference

            val productId = databaseRef.push().key!!

            val product = ProductValuesModel(productName, sellingPrice, shippingCostRsa, shippingCostInt)

            databaseRef.child(productId).setValue(product)
                .addOnCompleteListener {
                    Toast.makeText(this, "data inserted succesfully",
                        Toast.LENGTH_LONG)
                        .show()
                    startActivity(Intent(this@AddingProductsActivity, NavigationDrawerActivity::class.java))
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to upload data",
                        Toast.LENGTH_LONG)
                        .show()
                }
        }
    }


    private fun validateProductDetails(): Boolean {
        return when {
            TextUtils.isEmpty(binding.addProductName.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_product_name), true)
                false
            }
            TextUtils.isEmpty(binding.addProductSellingPrice.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_list_price), true)
                false
            }
            TextUtils.isEmpty(binding.addShippingPriceInt.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }
            TextUtils.isEmpty(binding.addShippingPriceRsa.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password), true)
                false
            }
            else -> {
                //showErrorSnackBar(resources.getString(R.string.register_successful), false)
                true
            }
        }
    }
}
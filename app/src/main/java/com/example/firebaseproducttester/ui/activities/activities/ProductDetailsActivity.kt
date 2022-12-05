package com.example.firebaseproducttester.ui.activities.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.firebaseproducttester.R
import com.example.firebaseproducttester.databinding.ActivityLoginBinding
import com.example.firebaseproducttester.databinding.ActivityProductDetailsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityProductDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }
}
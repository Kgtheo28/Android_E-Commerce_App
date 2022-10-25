package com.example.firebaseproducttester.activities

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebaseproducttester.R
import com.example.firebaseproducttester.Utils.Constants
import com.example.firebaseproducttester.databinding.ActivityLoginBinding
import com.example.firebaseproducttester.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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
        val username = sharedPreferences.getString(
            Constants.LOGGED_IN_USERNAME, "")
        binding.tvMain.text = "hello $username."
    }
}
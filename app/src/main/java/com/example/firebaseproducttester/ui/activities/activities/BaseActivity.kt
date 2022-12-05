package com.example.firebaseproducttester.ui.activities.activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.firebaseproducttester.R
import com.example.firebaseproducttester.databinding.ActivityBaseBinding
import com.firebase.ui.auth.AuthUI.SupportedProvider
import com.google.android.material.snackbar.Snackbar

open class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding
    private lateinit var mProgressDialog: Dialog

    private var doubleBackToExitPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun showErrorSnackBar(message: String, errorMessage: Boolean){
        val snackBar =
            Snackbar.make(findViewById(android.R.id.content),message, Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view

        if (errorMessage){
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    this@BaseActivity,
                    R.color.colorSnackBarError
                )
            )
        } else{
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    this@BaseActivity,
                    R.color.colorSnackBarSuccess
                )
            )
        }
        snackBar.show()
    }

    fun showProgressDialog(text: String) {
        mProgressDialog = Dialog(this)

        mProgressDialog.setContentView(R.layout.dialog_progress)

        mProgressDialog.findViewById<TextView>(R.id.tv_progress_text).text = text

        mProgressDialog.setCancelable(false)
        mProgressDialog.setCanceledOnTouchOutside(false)

        mProgressDialog.show()
    }

    fun hideProgressDialog(){
        mProgressDialog.dismiss()
    }

    fun doublebackToExit() {
        if (doubleBackToExitPressed) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressed = true

        Toast.makeText(
            this,
        resources.getString(R.string.please_click_back_again_to_exit),
        Toast.LENGTH_SHORT
        ).show()

        Handler().postDelayed({doubleBackToExitPressed = false}, 2000)
    }
}
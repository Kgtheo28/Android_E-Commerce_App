package com.example.firebaseproducttester.ui.activities.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide.init
import com.example.firebaseproducttester.R
import com.example.firebaseproducttester.Utils.Constants
import com.example.firebaseproducttester.Utils.GlideLoader
import com.example.firebaseproducttester.databinding.ActivitySettingsBinding
import com.example.firebaseproducttester.databinding.ActivityUserProfileDetailBinding
import com.example.firebaseproducttester.firestore.FirestoreClass
import com.example.firebaseproducttester.models.User
import com.google.firebase.auth.FirebaseAuth

class UserProfileDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityUserProfileDetailBinding
    private lateinit var mUserDetails: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.imageView50.setOnClickListener{
            val intent = Intent(this@UserProfileDetailActivity, SettingsActivity::class.java)
            startActivity(intent)
        }

        getUserDetails()
    }



    private fun getUserDetails(){
        showProgressDialog(resources.getString(R.string.please_wait))
        FirestoreClass().getUserDetails(this)
    }

    fun userDetailsSuccess(user: User){
        mUserDetails = user

        hideProgressDialog()

        GlideLoader(this@UserProfileDetailActivity).loaduserPicture(user.image, binding.imageView28)
        binding.textView14.text = "Hi, ${user.firstName} ${user.lastName}"
        binding.textView45.text = user.firstName
        binding.textView46.text = user.lastName
        binding.textView48.text = "${user.mobile}"
    }


    override fun onResume() {
        super.onResume()
        getUserDetails()
    }


}
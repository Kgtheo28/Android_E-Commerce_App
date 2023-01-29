package com.example.firebaseproducttester.ui.activities.activities

import android.content.Intent
import android.os.Bundle
import android.telecom.Call.Details
import android.view.View
import com.example.firebaseproducttester.R
import com.example.firebaseproducttester.Utils.Constants
import com.example.firebaseproducttester.Utils.GlideLoader
import com.example.firebaseproducttester.databinding.ActivitySettingsBinding
import com.example.firebaseproducttester.firestore.FirestoreClass
import com.example.firebaseproducttester.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class SettingsActivity : BaseActivity(), View.OnClickListener {

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var mUserDetails: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.tvEdit.setOnClickListener(this)
        binding.btnLogout.setOnClickListener(this)
        setupActionBar()
    }

    private fun setupActionBar() {
        setSupportActionBar(binding.toolbarSettingsActivity)

        val actionBar = supportActionBar
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_keyboard_arrow_left)
        }

        binding.toolbarSettingsActivity.setNavigationOnClickListener { onBackPressed() }
    }

    private fun getuserDetails(){
        showProgressDialog(resources.getString(R.string.please_wait))
        FirestoreClass().getUserDetails(this)
    }
    fun userDetailsSuccess(user: User){
        mUserDetails = user

        hideProgressDialog()

        GlideLoader(this@SettingsActivity).loaduserPicture(user.image, binding.ivUserPhoto)
        binding.tvName.text = "${user.firstName} ${user.lastName}"
        binding.tvGender.text = user.gender
        binding.tvEmail.text = user.email
        binding.tvMobileNumber.text = "${user.mobile}"
    }

    override fun onResume() {
        super.onResume()
        getuserDetails()
    }

    override fun onClick(v: View?) {
        if(v != null){
            when(v.id) {
                R.id.tv_edit -> {
                    val intent = Intent(this@SettingsActivity, UserProfileActivity::class.java)
                    intent.putExtra(Constants.EXTRA_USER_DETAILS, mUserDetails)
                    startActivity(intent)
                }
                R.id.btn_logout -> {
                    FirebaseAuth.getInstance().signOut()
                    val intent = Intent(this@SettingsActivity, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}
package com.example.firebaseproducttester.ui.activities.activities

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.firebaseproducttester.R
import com.example.firebaseproducttester.Utils.Constants
import com.example.firebaseproducttester.Utils.GlideLoader
import com.example.firebaseproducttester.databinding.ActivityUserProfileBinding
import com.example.firebaseproducttester.firestore.FirestoreClass
import com.example.firebaseproducttester.models.User
import java.io.IOException

class UserProfileActivity : BaseActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserProfileBinding

    private lateinit var mUserDetails: User
    private var mSelectedImageFileUri: Uri? = null
    private var mUserProfileImageURL: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        if (intent.hasExtra(Constants.EXTRA_USER_DETAILS)) {
            mUserDetails = intent.getParcelableExtra(Constants.EXTRA_USER_DETAILS)!!
        }

        if(mUserDetails.profileCompleted == 0) {
            binding.tvTitle.text = resources.getString(R.string.title_complete_profile)

            binding.etFirstName.isEnabled = false
            binding.etFirstName.setText(mUserDetails.firstName)

            binding.etLastName.isEnabled = false
            binding.etLastName.setText(mUserDetails.lastName)

            binding.etEmail.isEnabled = false
            binding.etEmail.setText(mUserDetails.email)
        } else{
            binding.tvTitle.text = resources.getString(R.string.title_edit_profile)
            GlideLoader(this@UserProfileActivity).loaduserPicture(mUserDetails.image, binding.ivUserPhoto)
            binding.etFirstName.setText(mUserDetails.firstName)
            binding.etLastName.setText(mUserDetails.lastName)

            if (mUserDetails.mobile != 0L){
                binding.etMobileNumber.setText(mUserDetails.mobile.toString())
            }
            if (mUserDetails.gender == Constants.MALE) {
                binding.rbMale.isChecked = true
            } else {
                binding.rbMale.isChecked = true
            }
        }


        binding.etFirstName.isEnabled = false
        binding.etFirstName.setText(mUserDetails.firstName)

        binding.etLastName.isEnabled = false
        binding.etLastName.setText(mUserDetails.lastName)

        binding.etEmail.isEnabled = false
        binding.etEmail.setText(mUserDetails.email)

        binding.ivUserPhoto.setOnClickListener(this@UserProfileActivity)
        binding.btnSubmit.setOnClickListener(this@UserProfileActivity)
    }


    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.iv_user_photo -> {
                    if (ContextCompat.checkSelfPermission(
                            this,
                            android.Manifest.permission.READ_EXTERNAL_STORAGE
                        )
                        == PackageManager.PERMISSION_GRANTED
                    ) {
                        Constants.showImageChooser(this)
                    } else {
                        ActivityCompat.requestPermissions(
                            this,
                            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                            Constants.READ_STORAGE_PERMISSION_CODE
                        )
                    }
                }

                R.id.btn_submit -> {
                   if(validateUserProfileDetails()){

                       showProgressDialog(resources.getString(R.string.please_wait))

                       if(mSelectedImageFileUri != null)
                       FirestoreClass().uploadImageToCloudStorage(this, mSelectedImageFileUri)
                       else{
                           updateUserProfileDetails()
                       }
                    }
                }
            }
        }
    }

    private fun updateUserProfileDetails(){
        val userHashMap = HashMap<String, Any>()

        val mobileNumber = binding.etMobileNumber.text.toString().trim{ it <= ' ' }

        val gender = if (binding.rbMale.isChecked) {
            Constants.MALE
        } else {
            Constants.FEMALE
        }
        if (mUserProfileImageURL.isNotEmpty()) {
            userHashMap[Constants.IMAGE] = mUserProfileImageURL
        }
        if (mobileNumber.isNotEmpty()) {
            userHashMap[Constants.MOBILE] = mobileNumber.toLong()
        }
        userHashMap[Constants.GENDER] = gender

        userHashMap[Constants.COMPLETED_PROFILE] = 1

        FirestoreClass().updateUserprofileData(this, userHashMap)
    }

    fun userProfileUpdateSuccess() {
        hideProgressDialog()

        Toast.makeText(this@UserProfileActivity,
        resources.getString(R.string.msg_profile_update_success),
        Toast.LENGTH_SHORT
        ).show()

        startActivity(Intent(this@UserProfileActivity, MainActivity::class.java))
        finish()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constants.READ_STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Constants.showImageChooser(this)
            } else {
                Toast.makeText(
                    this,
                    resources.getString(R.string.read_storage_permission_denied),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constants.PICK_IMAGE_REQUEST_CODE) {
                if (data != null) {
                    try {
                        mSelectedImageFileUri = data.data!!

                        //binding.ivUserPhoto.setImageURI(Uri.parse(selectedImageFileUri.toString()))
                        GlideLoader(this).loaduserPicture(mSelectedImageFileUri!!, binding.ivUserPhoto)
                    } catch (e: IOException) {
                        e.printStackTrace()
                        Toast.makeText(
                            this@UserProfileActivity,
                            resources.getString(R.string.image_selection_failed),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Log.e("Request Cancelled", "Image selection canceled")
        }
    }
    private fun validateUserProfileDetails() : Boolean {
        return when {
            TextUtils.isEmpty(binding.etMobileNumber.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_mobile_number), true)
                false
            } else -> {
                true
            }
        }
    }

    fun imageUploadSuccess(imageURL: String) {
        mUserProfileImageURL = imageURL
        updateUserProfileDetails()
    }

}
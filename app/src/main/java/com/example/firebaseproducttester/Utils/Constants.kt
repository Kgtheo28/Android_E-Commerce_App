package com.example.firebaseproducttester.Utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.webkit.MimeTypeMap

object Constants {

    const val USERS: String = "users"
    const val MYSHOPPAL_PREFERENCES: String = "MyShopPrefs"
    const val LOGGED_IN_USERNAME: String = "looged_in_username"
    const val EXTRA_USER_DETAILS: String = "extra_user_details"
    const val READ_STORAGE_PERMISSION_CODE = 2
    const val PICK_IMAGE_REQUEST_CODE = 1

    const val MALE: String = "male"
    const val FEMALE: String = "female"
    const val MOBILE: String = "mobile"
    const val GENDER: String = "gender"
    const val ADDRESS: String = "address"
    const val IMAGE: String = "image"
    const val USER_PROFILE_IMAGE: String = "USER_PROFILE_IMAGE"
    const val COMPLETED_PROFILE: String = "profileCompleted"

    /* Shopping items*/
    const val ITEM_TITLE: String = "item title"
    const val ITEM_DESCRIPTION: String = "item description"
    const val ITEM_PRICE: String = "item price"

    fun showImageChooser(activity: Activity) {
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        activity.startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE)
    }

    fun getFileExtension(activity: Activity, uri: Uri?): String? {
        return MimeTypeMap.getSingleton()
            .getExtensionFromMimeType(activity.contentResolver.getType(uri!!))
    }

}
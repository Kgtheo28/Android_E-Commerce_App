package com.example.firebaseproducttester.Utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.firebaseproducttester.R
import java.io.IOException

class GlideLoader(val context: Context) {

    fun loaduserPicture(image: Any, imageView: ImageView){

        try {
            Glide
                .with(context)
                .load(image)
                .centerCrop()
                .placeholder(R.drawable.user_image_background)
                .into(imageView)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
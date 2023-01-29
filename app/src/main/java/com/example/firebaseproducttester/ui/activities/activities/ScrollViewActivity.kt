package com.example.firebaseproducttester.ui.activities.activities

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.example.firebaseproducttester.databinding.ActivityScrollViewBinding
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.io.IOException
import java.util.UUID

class ScrollViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrollViewBinding

    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScrollViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference


    }

    private fun getItemList(): ArrayList<String>{
        val list = ArrayList<String>()

        for (i in 1..15){
            list.add("item $i")
        }
        return list
    }


}

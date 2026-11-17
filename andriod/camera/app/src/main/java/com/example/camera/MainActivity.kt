package com.example.camera

import android.graphics.ImageDecoder
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val selectBtn:Button=findViewById(R.id.selectBtn)
        val imageView:ImageView=findViewById(R.id.image)
        val select=registerForActivityResult(ActivityResultContracts.GetContent()){ uri: Uri?->
            uri?.let{ it->
                val bitmap=ImageDecoder.decodeBitmap(ImageDecoder.createSource(this.contentResolver,it))
                imageView.setImageBitmap(bitmap)
        }
        }
        selectBtn.setOnClickListener{
            select.launch("image/*")
        }
    }
}
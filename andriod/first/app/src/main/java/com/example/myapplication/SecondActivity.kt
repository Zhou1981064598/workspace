package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

//        val id:Int = intent.getIntExtra("id", 0)
//        val code = intent.getStringExtra("code")
//
//        Log.e("id", id.toString())
//
//        val textView:TextView = findViewById(R.id.textView)
//        textView.text = code.toString()
        val backBtn:Button = findViewById(R.id.second_btn_back)
        backBtn.setOnClickListener {
            val intent = Intent()
            intent.putExtra("name", "good")
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}
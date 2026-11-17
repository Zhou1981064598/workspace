package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class ShowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)
        val intent:Intent= intent
        val id: String? =intent.getStringExtra("id")
        val studentname: String? =intent.getStringExtra("name")
        val imgid: Int=intent.getIntExtra("imgid",0)
        val entrancetime: String? =intent.getStringExtra("entrancetime")
        val perphone: String? =intent.getStringExtra("perphone")
        val birthday: String? =intent.getStringExtra("birthday")
        val politicalface: String? =intent.getStringExtra("politicalface")
        val native: String? =intent.getStringExtra("native")
        val national: String? =intent.getStringExtra("national")
        val idtext:TextView=findViewById(R.id.id)
        idtext.text=id.toString()
        val stuimg:ImageView=findViewById(R.id.studentimg)
        stuimg.setImageResource(imgid)
        val entrancetimetext:TextView=findViewById(R.id.entrancetime)
        entrancetimetext.text=entrancetime.toString()
        val studentnametext:TextView=findViewById(R.id.studentname)
        studentnametext.text=studentname.toString()
        val perphonetext:TextView=findViewById(R.id.perphone)
        perphonetext.text=perphone.toString()
        val birthdaytext:TextView=findViewById(R.id.birthday)
        birthdaytext.text=birthday.toString()
        val politicalfacetext:TextView=findViewById(R.id.politicalface)
        politicalfacetext.text=politicalface.toString()
        val nativetext:TextView=findViewById(R.id.nativetext)
        nativetext.text=native.toString()
        val nationaltext:TextView=findViewById(R.id.national)
        nationaltext.text=national.toString()
        val xiugai:Button=findViewById(R.id.xiugai)
        xiugai.setOnClickListener {
            val intent= Intent(this@ShowActivity,UpdateActivity::class.java)
            intent.putExtra("id",id)
            intent.putExtra("name",studentname)
            intent.putExtra("imgid",imgid)
            intent.putExtra("entrancetime",entrancetime)
            intent.putExtra("perphone",perphone)
            intent.putExtra("birthday",birthday)
            intent.putExtra("politicalface",politicalface)
            intent.putExtra("native",native)
            intent.putExtra("national",national)
            startActivity(intent)
        }
    }
}
package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.test.db.StudentDatabase

class UpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        val intent: Intent = intent
        val id1: String = intent.getStringExtra("id").toString()
        Log.e("id",id1)
        val studentname: String ?=intent.getStringExtra("name")
        val imgid: Int=intent.getIntExtra("imgid",0)
        val entrancetime: String? =intent.getStringExtra("entrancetime")
        val perphone: String ?=intent.getStringExtra("perphone")
        val birthday: String ?=intent.getStringExtra("birthday")
        val politicalface: String? =intent.getStringExtra("politicalface")
        val native: String ?=intent.getStringExtra("native")
        val national: String?=intent.getStringExtra("national")
        val idtext: EditText =findViewById(R.id.updateid)
        idtext.setText(id1)
        val stuimg: ImageView =findViewById(R.id.touxiang)
        stuimg.setImageResource(imgid)
        val studentnametext: EditText =findViewById(R.id.updatestudentname)
        studentnametext.setText(studentname)
        val studentname1=studentnametext.text
        val entrancetimetext: TextView =findViewById(R.id.updateentrancetime)
        entrancetimetext.setText(entrancetime)
        val entrancetime1=entrancetimetext.text
        val perphonetext: TextView =findViewById(R.id.updateperphone)
        perphonetext.setText(perphone)
        val perphone1=perphonetext.text
        val birthdaytext: TextView =findViewById(R.id.updatebirthday)
        birthdaytext.setText(birthday)
        val birthday1=birthdaytext.text
        val politicalfacetext: TextView =findViewById(R.id.updatepoliticalface)
        politicalfacetext.setText(politicalface)
        val politicalface1=politicalfacetext.text
        val nativetext: TextView =findViewById(R.id.updatenativetext)
        nativetext.setText(native)
        val native1=nativetext.text
        val nationaltext: TextView =findViewById(R.id.updatenational)
        nationaltext.setText(national)
        val national1=nationaltext.text
        val xiugai: Button =findViewById(R.id.save)
        xiugai.setOnClickListener {
            val myDatabase= StudentDatabase(this,"student.db",1)
            val db =myDatabase.writableDatabase
            db.execSQL("update  student set studentname=?, entrancetime=?,perphone=?,birthday=?,politicalface=?,native=?,national=? where id=?",
                arrayOf( studentname1,entrancetime1,perphone1,birthday1,politicalface1,native1,national1,id1))
            Toast.makeText(this,"修改成功", Toast.LENGTH_SHORT).show()
        }
    }
}
package com.example.test

import android.graphics.ImageDecoder
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.test.db.StudentDatabase
import com.example.third.db.MyDatabase

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        val addid:EditText=findViewById(R.id.addid)
        val addname:EditText=findViewById(R.id.addstudentname)
        val addentrancetime: EditText =findViewById(R.id.addentrancetime)
        val addphone: EditText =findViewById(R.id.addperphone)
        val addbirthday: EditText =findViewById(R.id.addbirthday)
        val addpoliticalface: EditText =findViewById(R.id.addpoliticalface)
        val addnational: EditText =findViewById(R.id.addnational)
        val addnativetext: EditText =findViewById(R.id.addnativetext)
        val addimg:Button=findViewById(R.id.add)
//        val touxiang:ImageView=findViewById(R.id.touxiang)
        val id = addid.text
        val name= addname.text
        val entrancetime = addentrancetime.text
        val phone = addphone.text
        val birthday=addbirthday.text
        val politicalface = addpoliticalface.text
        val national = addnational.text
        val ativetext=addnativetext.text

        addimg.setOnClickListener{
            val myDatabase= StudentDatabase(this,"student.db",1)
            val db =myDatabase.writableDatabase

            db.execSQL("insert into student(id,imgid,studentname, entrancetime,perphone,birthday,politicalface,native,national) values(?,?,?,?,?,?,?,?,?)",
                arrayOf(id,R.drawable.liba, name,entrancetime,phone,birthday,politicalface,ativetext,national))

            db.close()
            Toast.makeText(this,"添加成功", Toast.LENGTH_SHORT).show()

        }
        }


}
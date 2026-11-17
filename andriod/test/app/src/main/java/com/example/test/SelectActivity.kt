package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.test.db.StudentDatabase

class SelectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)
        val select:Button=findViewById(R.id.select)
        val chaxun:EditText=findViewById(R.id.chaxun)
        var id=""
        var imgid=1
        var studentname=""
        var entrancetime=""
        var perphone=""
        var birthday=""
        var politicalface=""
        var native=""
        var national=""
        select.setOnClickListener {
            val myDatabase= StudentDatabase(this,"student.db",1)
            val db =myDatabase.writableDatabase
            val selectid=chaxun.text.toString()
            val cursor=db.rawQuery("select * from student where id=?", arrayOf(selectid))
            if (cursor.moveToNext()){
                do{
                    var index=cursor.getColumnIndex("id")
                    id= cursor.getInt(index).toString()
                    index=cursor.getColumnIndex("studentname")
                    studentname=cursor.getString(index)
                    index=cursor.getColumnIndex("imgid")
                    imgid=cursor.getInt(index)
                    index=cursor.getColumnIndex("entrancetime")
                    entrancetime=cursor.getString(index)
                    index=cursor.getColumnIndex("perphone")
                    perphone=cursor.getString(index)
                    index=cursor.getColumnIndex("birthday")
                    birthday=cursor.getString(index)
                    index=cursor.getColumnIndex("politicalface")
                    politicalface=cursor.getString(index)
                    index=cursor.getColumnIndex("native")
                    native=cursor.getString(index)
                    index=cursor.getColumnIndex("national")
                    national=cursor.getString(index)

                }while (cursor.moveToNext())
            }
            val s0=Studentdetil(imgid,studentname,id,entrancetime,perphone,birthday,politicalface,native,national)

            val id=s0.id
            val name=s0.name
            val imgid=s0.imgid
            val entrancetime=s0.entrancetime
            val perphone=s0.perphone
            val birthday=s0.birthday
            val politicalface=s0.politicalface
            val native=s0.native
            val national=s0.national
            cursor.close()
            val intent= Intent(this@SelectActivity,ShowActivity::class.java)
            intent.putExtra("id",id)
            intent.putExtra("name",name)
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

package com.example.third

import android.content.ContentValues
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.third.db.MyDatabase
import java.io.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn:Button=findViewById(R.id.btn)
        val textView:TextView=findViewById(R.id.textview)
//        val pfs=getSharedPreferences("my_pfs",Context.MODE_PRIVATE)
//        val name=pfs.getString("name","")
//        val id=pfs.getInt("id",0)
//        val flag=pfs.getBoolean("flag",false)
//        Log.e("name",name.toString())
//        Log.e("id","$id")
//        Log.e("flag","$flag")
        btn.setOnClickListener{
//            writeFile("hello_world")
//            val pfs=getSharedPreferences("my_pfs",Context.MODE_PRIVATE).edit()
//            pfs.putString("name","张三")
//            pfs.putInt("id",100)
//            pfs.putBoolean("flag",true)
//            pfs.apply()
            val myDatabase=MyDatabase(this,"myDatabase.db",2)
            val db =myDatabase.writableDatabase
//            db.execSQL("create table admin("+
//                    "id integer primary key autoincrement,"+
//                    "account text,"+
//                    "password text,"+
//                    "token text)")
            val cursor=db.rawQuery("select * from user",null)
            if (cursor.moveToFirst()){
                do {
                    var index=cursor.getColumnIndex("name")
                    val name:String=cursor.getString(index)
                    Log.e("name",name)
                    index=cursor.getColumnIndex("password")
                    val password:String=cursor.getString(index)
                    Log.e("password",password)
                }while (cursor.moveToNext())
            }
            cursor.close()

//            myDatabase.writableDatabase
//            db.execSQL("delete from user where id =?", arrayOf("2"))
//            val db=myDatabase.writableDatabase
//            val res=ContentValues().apply {
//                put("name","张三")
//                put("password","666666")
//                put("work_age",5)
//                put("sex",1)
//            }
//            db.insert("user",null,res)
//            val res1=ContentValues().apply {
//                put("name","李四")
//                put("password","123456")
//                put("work_age",5)
//                put("sex",0)
//            }
//            db.insert("user",null,res1)


        }
        val tmpstr:String= readFile()
        textView.text=tmpstr

    }
    private fun writeFile(text:String){
        try {
            val output=openFileOutput("my_data",Context.MODE_PRIVATE)
            val writer=BufferedWriter(OutputStreamWriter(output))
            writer.use {
                it.write(text)
            }
        }catch (e:IOException){
            e.printStackTrace()
        }
    }
    private fun readFile():String{
        val content= StringBuilder()
        try {
            val input=openFileInput("my_data")
            val reader=BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    content.append(it)
                }
            }
        }catch (e:IOException){
            e.printStackTrace()
        }
        return content.toString()
    }
}
package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.test.db.StudentDatabase

class DeleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)
        val delete: Button =findViewById(R.id.delete)
        val shanchu:EditText=findViewById(R.id.shanchu)
        val id=shanchu.text
        delete.setOnClickListener {
            val myDatabase= StudentDatabase(this,"student.db",1)
            val db =myDatabase.writableDatabase
            db.execSQL("delete from student where id = ?", arrayOf(id))
            db.close()
            Toast.makeText(this,"删除成功", Toast.LENGTH_SHORT).show()
        }
    }
}
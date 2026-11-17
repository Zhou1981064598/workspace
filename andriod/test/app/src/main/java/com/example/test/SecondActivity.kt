package com.example.test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.db.StudentDatabase

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        var id=""
        var imgid=1
        var studentname=""
        var entrancetime=""
        var perphone=""
        var birthday=""
        var politicalface=""
        var native=""
        var national=""
        val listView: RecyclerView =findViewById(R.id.listView)
        val data=ArrayList<Student>()
        val data1=ArrayList<Studentdetil>()
        val myDatabase= StudentDatabase(this,"student.db",1)
        val db =myDatabase.writableDatabase
        val cursor=db.rawQuery("select * from student",null)
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
                val s0=Studentdetil(imgid,studentname,id,entrancetime,perphone,birthday,politicalface,native,national)
                val s = Student(imgid,studentname,id)
                data1.add(s0)
                data.add(s)
            }while (cursor.moveToNext())
        }
        val layoutManager= LinearLayoutManager(this)
        layoutManager.orientation= LinearLayoutManager.VERTICAL
        listView.layoutManager=layoutManager
        val adapter=StudentAdapter(data)
        listView.adapter=adapter
        adapter.setOnKotlinItemClickListener(object :StudentAdapter.IKotlinItemClickListener{
            override fun onItemClickListener(position: Int) {
                val intent=Intent(this@SecondActivity,ShowActivity::class.java)
                val id=data1[position].id
                val name=data1[position].name
                val imgid=data1[position].imgid
                val entrancetime=data1[position].entrancetime
                val perphone=data1[position].perphone
                val birthday=data1[position].birthday
                val politicalface=data1[position].politicalface
                val native=data1[position].native
                val national=data1[position].national
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
        })
        cursor.close()
        val backBtn: Button = findViewById(R.id.backbtn)
        backBtn.setOnClickListener {
            val intent = Intent()
            setResult(RESULT_OK, intent)
            finish()
        }
        val add:Button=findViewById(R.id.add)
        add.setOnClickListener{
            val intent=Intent(this@SecondActivity,AddActivity::class.java)
            startActivity(intent)
        }
        val delete:Button=findViewById(R.id.delete)
        delete.setOnClickListener{
            val intent=Intent(this@SecondActivity,DeleteActivity::class.java)
            startActivity(intent)
        }
        val select:Button=findViewById(R.id.select)
        select.setOnClickListener {
            val intent=Intent(this@SecondActivity,SelectActivity::class.java)
            startActivity(intent)
        }
    }
}
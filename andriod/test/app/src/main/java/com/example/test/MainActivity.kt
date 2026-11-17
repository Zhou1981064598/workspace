package com.example.test
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.third.db.MyDatabase
import java.text.FieldPosition
import kotlin.math.log
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView:TextView=findViewById(R.id.text1)
        textView.text="用户名"
        val editTextUerName:EditText=findViewById(R.id.edit1)
        val checkBox:CheckBox=findViewById(R.id.checkbox)
        val editTextPassword:EditText=findViewById(R.id.edit2)
        val btn:Button=findViewById(R.id.btn)
        val myDatabase= MyDatabase(this,"zhou.db",1)
        val db =myDatabase.writableDatabase
        var username=""
        var password=""
        var autoLogin=0
        val cursor=db.rawQuery("select * from user where username=? ", arrayOf("admin"))
        if (cursor.moveToNext()){
            do{
               var index=cursor.getColumnIndex("username")
                username=cursor.getString(index)
                index=cursor.getColumnIndex("password")
                password=cursor.getString(index)
                index=cursor.getColumnIndex("auto_login")
                autoLogin=cursor.getInt(index)
            }while (cursor.moveToNext())
        }
        cursor.close()
        if(autoLogin==1){
            editTextUerName.setText(username)
            editTextPassword.setText(password)
            checkBox.isChecked=true
        }
        btn.setOnClickListener{
            val userName: String = editTextUerName.text.toString()
            val passWord: String = editTextPassword.text.toString()
            if (checkBox.isChecked){

                db.execSQL("update user set auto_login=? where username=?", arrayOf("1", "admin"))
            }else
                db.execSQL("update user set auto_login=? where username=?", arrayOf("0", "admin"))
            val name = userName == username
            val pass= passWord ==password
            if (TextUtils.isEmpty(editTextUerName.getText().toString()) || TextUtils.isEmpty(editTextPassword.getText().toString())) {
                Toast.makeText(this, "用户名或者密码不能为空", Toast.LENGTH_SHORT).show();
            } else if (name&&pass) {
                val intent: Intent =Intent(this,SecondActivity::class.java)
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "用户名或者密码错误", Toast.LENGTH_SHORT).show();
            }}
        val imageView:ImageView=findViewById(R.id.img)
        imageView.setImageResource(R.drawable.img)
        var count=0
        imageView.setOnClickListener{
            if (count==0){
                imageView.setImageResource(R.drawable.img1)
                count++
            }else if (count==1){
                imageView.setImageResource(R.drawable.img2)
                count++
            }else{
                imageView.setImageResource(R.drawable.img)
                count=0
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add-> Toast.makeText(this,"add",Toast.LENGTH_LONG).show()
            R.id.delete-> Toast.makeText(this,"delete",Toast.LENGTH_LONG).show()
        }
        return true
    }
}
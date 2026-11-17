package com.example.second

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.example.second.db.MyDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val accountEdit: EditText = findViewById(R.id.main_edit_account)
        val passwordEdit: EditText = findViewById(R.id.main_edit_password)
        val checkbox: CheckBox = findViewById(R.id.main_checkbox)

        val myDatabase = MyDatabase(this, "my_admin.db",1)
        val db = myDatabase.writableDatabase

        var account = ""
        var password = ""
        var autoLogin = 0

        val cursor = db.rawQuery("select * from admin where account = ?", arrayOf("admin"))
        if (cursor.moveToFirst()) {
            do {
                var index = cursor.getColumnIndex("account")
                account = cursor.getString(index)

                index = cursor.getColumnIndex("password")
                password = cursor.getString(index)

                index = cursor.getColumnIndex("auto_login")
                autoLogin = cursor.getInt(index)

            } while (cursor.moveToNext())
        }
        cursor.close()

        if (autoLogin == 1) {
            accountEdit.setText(account)
            passwordEdit.setText(password)
            checkbox.isChecked = true
        }

        val btn:Button = findViewById(R.id.main_btn)
        btn.setOnClickListener { //login
            if (checkbox.isChecked) {
                db.execSQL("update admin set auto_login=? where account=?", arrayOf("1", "admin"))
            } else {
                db.execSQL("update admin set auto_login=? where account=?", arrayOf("0", "admin"))
            }
        }

    }

}
package com.example.third.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDatabase(val context: Context, name: String, version:Int):
    SQLiteOpenHelper(context,name,null,version) {
    val user="create table user("+
            "id integer primary key autoincrement,"+
            "username text,"+
            "password text,"+
            "auto_login integer)"


    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(user)
        p0?.execSQL("insert into user(username, password, auto_login) values(?,?,?)",
            arrayOf("admin", "123456","0"))
    }

    override fun onUpgrade(p0: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}
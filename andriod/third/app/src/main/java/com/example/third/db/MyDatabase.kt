package com.example.third.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDatabase(val context: Context, name: String, version:Int):
    SQLiteOpenHelper(context,name,null,version) {
    val user="create table user("+
            "id integer primary key autoincrement,"+
            "name text,"+
            "password text,"+
            "work_age real,"+
            "sex integer)"
    val order="create table orders("+
            "id integer primary key autoincrement,"+
            "num text,"+
            "price real,"+
            "addr text)"
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(user)
        p0?.execSQL(order)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        p0?.execSQL(order)
        Toast.makeText(context, "onUpgrade", Toast.LENGTH_SHORT).show()
    }
}
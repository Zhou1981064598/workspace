package com.examadple.second.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabase(private val context: Context, name: String, version:Int):
    SQLiteOpenHelper(context, name, null, version) {

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table admin (" +
                "id integer primary key autoincrement," +
                "account text,"  +
                "password text," +
                "auto_login integer)")
        p0?.execSQL("insert into admin(account, password, auto_login) values(?,?,?)",
            arrayOf("admin", "123456","0"))
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}
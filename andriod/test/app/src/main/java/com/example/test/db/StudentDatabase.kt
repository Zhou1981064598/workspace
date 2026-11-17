package com.example.test.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.test.R

class StudentDatabase(val context: Context,name:String,version:Int):
    SQLiteOpenHelper(context,name,null,version) {
    val student="create table student("+
                "id integer primary key autoincrement,"+
                "imgid integer,"+
                "studentname text,"+
                "entrancetime text,"+
                "perphone text,"+
                "birthday text,"+
                "politicalface text,"+
                "native text,"+
                "national text)"

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(student)
        p0?.execSQL("insert into student(id,imgid,studentname, entrancetime,perphone,birthday,politicalface,native,national) values(?,?,?,?,?,?,?,?,?)",
            arrayOf("202201", R.drawable.zhangsan, "张三","2019-05","15685468815","2000-09-15","团员","湖南","汉族"))
        p0?.execSQL("insert into student(id,imgid,studentname, entrancetime,perphone,birthday,politicalface,native,national) values(?,?,?,?,?,?,?,?,?)",
            arrayOf("202202", R.drawable.lisi,"李四","2019-05","18651616822","2001-08-15","团员","北京","汉族"))
        p0?.execSQL("insert into student(id,imgid,studentname, entrancetime,perphone,birthday,politicalface,native,national) values(?,?,?,?,?,?,?,?,?)",
            arrayOf("202203",R.drawable.wangwu, "王五","2018-05","11481121545","2002-07-23","党员","贵州","土家族"))
        p0?.execSQL("insert into student(id,imgid,studentname, entrancetime,perphone,birthday,politicalface,native,national) values(?,?,?,?,?,?,?,?,?)",
            arrayOf("202204", R.drawable.liba,"李八","2019-05","126596189156","1999-06-26","群众","上海","汉族"))
        p0?.execSQL("insert into student(id,imgid,studentname, entrancetime,perphone,birthday,politicalface,native,national) values(?,?,?,?,?,?,?,?,?)",
            arrayOf("202205",R.drawable.zhangyu, "张羽","2017-05","16584815122","2000-11-21","群众","浙江","汉族"))
        p0?.execSQL("insert into student(id,imgid,studentname, entrancetime,perphone,birthday,politicalface,native,national) values(?,?,?,?,?,?,?,?,?)",
            arrayOf("202206", R.drawable.wanghong,"王红","2020-05","1841544848","2002-12-25","党员","贵州","回族"))
        p0?.execSQL("insert into student(id,imgid,studentname, entrancetime,perphone,birthday,politicalface,native,national) values(?,?,?,?,?,?,?,?,?)",
            arrayOf("202207", R.drawable.chenfang, "陈芳","2019-05","15238532815","2001-12-15","团员","广东","汉族"))
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}
package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var timeChangeReceiver: TimeChangeReceiver


    inner class TimeChangeReceiver: BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            Log.e("time change receiver", "run")
        }
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        unregisterReceiver(timeChangeReceiver)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.TIME_TICK")
        timeChangeReceiver = TimeChangeReceiver()
        registerReceiver(timeChangeReceiver, intentFilter)

        supportActionBar?.hide()

        val rightTextView:TextView = findViewById(R.id.right_textView)

        val listView:RecyclerView = findViewById(R.id.listView)
        val data = ArrayList<Fruit>()

        repeat(5) {
            data.add(Fruit(R.drawable.apple_pic, "Apple"))
            data.add(Fruit(R.drawable.banana_pic, "Banana"))
            data.add(Fruit(R.drawable.cherry_pic, "Cherry"))
            data.add(Fruit(R.drawable.grape_pic, "Grape"))
            data.add(Fruit(R.drawable.mango_pic, "Mango"))
        }

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        listView.layoutManager = layoutManager
        val adapter = FruitAdapter(data)
        listView.adapter = adapter

        adapter.setOnKotlinItemClickListener(object : FruitAdapter.IKotlinItemClickListener {
            override fun onItemClickListener(position: Int) {
                Toast.makeText(applicationContext, position.toString(), Toast.LENGTH_SHORT).show()
                rightTextView.text = position.toString()
            }
        })
    }

    fun test() {
        Log.e("main_activity", "run")
    }
}
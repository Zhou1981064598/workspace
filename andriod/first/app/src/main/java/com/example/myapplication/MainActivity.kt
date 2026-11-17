package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView:RecyclerView = findViewById(R.id.listView)
        val data = ArrayList<Fruit>()

        repeat(1) {
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
                val name = data[position].name
                Toast.makeText(applicationContext, name, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
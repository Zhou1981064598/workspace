package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class LeftFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (activity != null) {
            val mainActivity = activity as MainActivity
            mainActivity.test()
        }
        return inflater.inflate(R.layout.left_fragment, container, false)
    }

//    fun initData() {
//        val listView:RecyclerView = findViewById(R.id.listView)
//        val data = ArrayList<Fruit>()
//        repeat(5) {
//            data.add(Fruit(R.drawable.apple_pic, "Apple"))
//            data.add(Fruit(R.drawable.banana_pic, "Banana"))
//            data.add(Fruit(R.drawable.cherry_pic, "Cherry"))
//            data.add(Fruit(R.drawable.grape_pic, "Grape"))
//            data.add(Fruit(R.drawable.mango_pic, "Mango"))
//        }
//
//        val layoutManager = LinearLayoutManager(this)
//        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
//        listView.layoutManager = layoutManager
//        val adapter = FruitAdapter(data)
//        listView.adapter = adapter
//    }

    fun test() {
        Log.e("left_fragment", "run")
    }
}
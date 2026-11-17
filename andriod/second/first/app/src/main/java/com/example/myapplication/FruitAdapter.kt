package com.example.myapplication

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FruitAdapter(private var data: List<Fruit>): RecyclerView.Adapter<FruitAdapter.ViewHolder>() {

//    private var context: Context? = null
    private var itemClickListener: IKotlinItemClickListener? = null

//    constructor (mContext: Context, data: List<Fruit>) {
//        this.context = mContext
//        this.data = data
//    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val fruitImg: ImageView = view.findViewById(R.id.item_img)
        val fruitName: TextView = view.findViewById(R.id.item_textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_list_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = data[position]
        holder.fruitImg.setImageResource(fruit.imgId)
        holder.fruitName.text = fruit.name

        holder.itemView.setOnClickListener {
            itemClickListener!!.onItemClickListener(position)
        }
    }

    override fun getItemCount() = data.size


    fun setOnKotlinItemClickListener(itemClickListener: IKotlinItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    //自定义接口
    interface IKotlinItemClickListener {
        fun onItemClickListener(position: Int)
    }

}
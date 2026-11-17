package com.example.test
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(private var data:List<Student>):RecyclerView.Adapter<StudentAdapter.ViewHolder>() {
    private var itemClickListener: IKotlinItemClickListener? = null
    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val studentImg: ImageView = view.findViewById(R.id.item_img)
        val studentName: TextView = view.findViewById(R.id.student_name)
        val studentId:TextView=view.findViewById(R.id.student_id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_list_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentAdapter.ViewHolder, position: Int) {
        val student = data[position]
        holder.studentImg.setImageResource(student.imgid)
        holder.studentName.text = student.name
        holder.studentId.text=student.id
        holder.itemView.setOnClickListener {
            itemClickListener!!.onItemClickListener(position)
        }
    }

    override fun getItemCount()=data.size


fun setOnKotlinItemClickListener(itemClickListener: IKotlinItemClickListener) {
    this.itemClickListener = itemClickListener
}
interface IKotlinItemClickListener {
    fun onItemClickListener(position: Int)
}


}
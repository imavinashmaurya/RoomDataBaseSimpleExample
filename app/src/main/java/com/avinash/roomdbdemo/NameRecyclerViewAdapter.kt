package com.avinash.roomdbdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.avinash.roomdbdemo.room.entity.NameEntity
import kotlinx.android.synthetic.main.row_name.view.*
import java.util.*

class NameRecyclerViewAdapter(
    private val nameList: ArrayList<NameEntity>,
    var clickListener: ClickListener?
) :
    RecyclerView.Adapter<NameRecyclerViewAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_name, parent, false)
        return ViewHolder(v)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return nameList.size
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(nameList[position], clickListener)
    }

    //the class is holding the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(nameEntity: NameEntity, clickListener: ClickListener?) {
            itemView.tvName?.text = nameEntity.name
            itemView.tvId?.text = nameEntity.id.toString()
            itemView.ivDelete?.setOnClickListener(View.OnClickListener {
                clickListener?.onDelete(nameEntity)
            })
            itemView.ivEdit?.setOnClickListener(View.OnClickListener {
                clickListener?.onEdit(nameEntity)
            })

        }
    }

}
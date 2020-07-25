package com.example.accounting

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar


class MainListAdapter constructor(
    private val context: Context
): RecyclerView.Adapter<MainListAdapter.ListViewHolder>() {

    private var listData= arrayListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)


    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val layout: ConstraintLayout= itemView.findViewById(R.id.layout_item)
        val imageType: ImageView = itemView.findViewById(R.id.item_image)
        val name: TextView = itemView.findViewById(R.id.item_name)
        val price: TextView = itemView.findViewById(R.id.item_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainListAdapter.ListViewHolder {
        return ListViewHolder(
            LayoutInflater
                .from(context)
                .inflate(R.layout.main_item_layout
                    , parent
                    , false
                )
        )
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: MainListAdapter.ListViewHolder, position: Int) {
        holder.name.text= listData[position].toString()
        holder.price.text= listData[position].toString()
        holder.layout.setOnClickListener{

        }
    }

//    fun addNewItem(data: ArrayList<data>) {
//        this.listData = data
//        notifyDataSetChanged()
//    }
}
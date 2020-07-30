package com.example.accounting.main.listFragment

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.accounting.R
import com.example.accounting.room.ItemEntity


class ListRecyclerAdapter constructor(
    private val context: Context
): RecyclerView.Adapter<ListRecyclerAdapter.ListViewHolder>() {

    private var listData= emptyList<ItemEntity>()

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val layout: ConstraintLayout= itemView.findViewById(R.id.layout_item)
        val imageType: ImageView = itemView.findViewById(R.id.item_image)
        val name: TextView = itemView.findViewById(R.id.item_name)
        val price: TextView = itemView.findViewById(R.id.item_price)
        val date: TextView = itemView.findViewById(R.id.item_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        Log.d(TAG, "Adapter Data: $listData")
        return ListViewHolder(
            LayoutInflater
                .from(context)
                .inflate(
                    R.layout.main_item_layout
                    , parent
                    , false
                )
        )
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.name.text= listData[position].name
        holder.date.text= listData[position].date
        holder.price.text= listData[position].price.toString()
        holder.layout.setOnClickListener{
            Log.d(ContentValues.TAG, "$position is Clicked!")
        }
    }

    fun addNewItem(data: List<ItemEntity>) {
        this.listData = data
        notifyDataSetChanged()
    }


}
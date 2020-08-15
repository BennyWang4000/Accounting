package com.example.accounting.main.mainPager.mainList.adapter

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
import com.example.accounting.main.listFragment.ListViewModel
import com.example.accounting.database.model.ItemEntity


class ListRecyclerAdapter constructor(
    private val context: Context,
    private val viewModel: ListViewModel,
    private val pagerPosition: Int
): RecyclerView.Adapter<ListRecyclerAdapter.ListViewHolder>() {
    private val MAX_LIST_VALUE= Int.MAX_VALUE
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
            LayoutInflater.from(context).inflate(R.layout.main_item_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if(viewModel.getDateData(pagerPosition).value!= null)
            viewModel.getDateData(pagerPosition).value!!.size
        else
            0
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//        Log.d("Recycler view data ${position}: ",
////            viewModel.getDateData(pagerPosition).value!![position].toString()
//        )
        if (viewModel.getDateData(position).value!= null) {
            holder.name.text= viewModel.getDateData(pagerPosition).value!![position].name
            holder.date.text= viewModel.getDateData(pagerPosition).value!![position].date
            holder.price.text= viewModel.getDateData(pagerPosition).value!![position].price.toString()
            Log.d("Recycler view data ${pagerPosition}: ", "${viewModel.getDateData(pagerPosition).value!![position]}")
        } else {
            Log.d("Recycler view data ${pagerPosition}: ", "Null data..")
        }
        holder.layout.setOnClickListener{
            Log.d(ContentValues.TAG, "$position is Clicked!")
        }
    }

    fun addNewItem(data: List<ItemEntity>) {
        this.listData = data
        notifyDataSetChanged()
    }
}
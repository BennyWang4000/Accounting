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
import com.example.accounting.database.model.ExpenseEntity


class ListRecyclerAdapter constructor(
    private val context: Context,
    private val viewModel: ListViewModel,
    private val pagerPosition: Int
): RecyclerView.Adapter<ListRecyclerAdapter.ListViewHolder>() {
    private val MAX_LIST_VALUE= Int.MAX_VALUE
    private var listData= emptyList<ExpenseEntity>()

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val layout: ConstraintLayout= itemView.findViewById(R.id.layout_item)
        val ivCategory: ImageView = itemView.findViewById(R.id.item_iv_category)
        val tvDescr: TextView = itemView.findViewById(R.id.item_tv_category)
        val tvAmount: TextView = itemView.findViewById(R.id.item_tv_amount)
        val tvDate: TextView = itemView.findViewById(R.id.item_tv_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        Log.d(TAG, "Adapter Data: $listData")
        return ListViewHolder(
            LayoutInflater.from(context).inflate(R.layout.main_item_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if(viewModel.getDateData().value!= null)
            viewModel.getDateData().value!!.size
        else
            0
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//        Log.d("Recycler view data ${position}: ",
////            viewModel.getDateData(pagerPosition).value!![position].toString()
//        )

        holder.tvDescr.text= viewModel.pageDateData.value!![position].descr
        holder.tvDate.text= viewModel.pageDateData.value!![position].date
        holder.tvAmount.text= viewModel.pageDateData.value!![position].amount.toString()
        Log.d("Recycler view data ${pagerPosition}: ", "${viewModel.getDateData().value!![position]}")

        holder.layout.setOnClickListener{
            Log.d(ContentValues.TAG, "$position is Clicked!")
        }
    }

    fun addNewItem(data: List<ExpenseEntity>) {
        this.listData = data
        notifyDataSetChanged()
    }
}
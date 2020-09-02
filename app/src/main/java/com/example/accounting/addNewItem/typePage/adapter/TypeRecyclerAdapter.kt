package com.example.accounting.addNewItem.typePage.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.accounting.R
import com.example.accounting.addNewItem.typePage.AddNewTypeListViewModel
import java.lang.Exception

class TypeRecyclerAdapter(private val context: Context, private val viewModel: AddNewTypeListViewModel): RecyclerView.Adapter<TypeRecyclerAdapter.TypeViewHolder>() {
    inner class TypeViewHolder(item: View): RecyclerView.ViewHolder(item){
        var tvName: TextView = item.findViewById(R.id.add_type_item_tv_name)
        var layoutItem: LinearLayout = item.findViewById(R.id.add_type_item_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeRecyclerAdapter.TypeViewHolder {
        return TypeViewHolder(
            LayoutInflater.from(context).inflate(R.layout.add_new_pager_type_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        //一頁十個
//        Log.e(" viewModel.pageCategories.value!!.size", "${ viewModel.pageCategories.value!!.size}")
        return viewModel.pageCategories.value!!.size
//        return 10
    }

    override fun onBindViewHolder(holder: TypeRecyclerAdapter.TypeViewHolder, position: Int) {
        //寬度為螢幕的五分之一
        holder.layoutItem.layoutParams.width= context.resources.displayMetrics.widthPixels/ 5
        holder.layoutItem.setOnClickListener{
            viewModel.selectedCategoryId.value= position
            Log.e("position", "$position")
//            return@setOnClickListener
        }
        // 有錯誤，卻能顯示成功
        try {
            holder.tvName.text = "${viewModel.pageCategories.value!![position].name}${ viewModel.pageCategories.value!![position].id}"
        } catch (e: Exception){
            Log.e("EXCEPTION", "$e")
        }
    }

    private fun clickListener(v: View){

    }
}
package com.example.accounting.setting.category.categoryFragment.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.accounting.R
import com.example.accounting.setting.category.categoryFragment.CategoryFragmentViewModel

class CategoryRecyclerAdapter(
    private val context: Context,
    private val viewModel: CategoryFragmentViewModel
): RecyclerView.Adapter<CategoryRecyclerAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvName: TextView= itemView.findViewById(R.id.category_item_tv)
        val tvImg: ImageView= itemView.findViewById(R.id.category_item_iv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(context).inflate(R.layout.category_item, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.tvName.text= viewModel.behaviorCategories.value!![position].name +
                "${viewModel.behaviorCategories.value!![position].id}"
    }

    override fun getItemCount(): Int {
        return  viewModel.behaviorCategories.value!!.size
    }
}
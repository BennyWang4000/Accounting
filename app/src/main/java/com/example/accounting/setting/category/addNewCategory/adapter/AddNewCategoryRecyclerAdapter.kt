package com.example.accounting.setting.category.addNewCategory.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.accounting.IconStyle
import com.example.accounting.R
import kotlinx.android.synthetic.main.category_add_new_icon_item.*

class AddNewCategoryRecyclerAdapter(
    val context: Context
): RecyclerView.Adapter<AddNewCategoryRecyclerAdapter.CategoryViewHolder>() {
    val icon= IconStyle.CATEGORY_ICON
    inner class CategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val ivIcon: ImageView = itemView.findViewById<ImageView>(R.id.category_add_new_item_iv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(context).inflate(R.layout.category_add_new_icon_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.ivIcon.setImageResource(icon[position])
    }

    override fun getItemCount(): Int = icon.size

}
package com.example.accounting.addNewItem.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.accounting.R

class TypePagerAdapter() : RecyclerView.Adapter<TypePagerAdapter.PagerViewHolder>() {
    inner class PagerViewHolder(item: View): RecyclerView.ViewHolder(item){
        val textView: TextView= item.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        return PagerViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.add_new_pager_type
                    , parent
                    , false
                )
        )
    }

    override fun getItemCount(): Int = 2

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.textView.text= "pager $position"
    }
}
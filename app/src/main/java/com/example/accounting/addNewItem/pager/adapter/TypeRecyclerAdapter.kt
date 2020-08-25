package com.example.accounting.addNewItem.pager.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.accounting.R

class TypeRecyclerAdapter(private val context: Context, private val viewModel: ): RecyclerView.Adapter<TypeRecyclerAdapter.TypeViewHolder>() {
    inner class TypeViewHolder(item: View): RecyclerView.ViewHolder(item){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeRecyclerAdapter.TypeViewHolder {
        return TypeViewHolder(
            LayoutInflater.from(context).inflate(R.layout.add_new_pager_type_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: TypeRecyclerAdapter.TypeViewHolder, position: Int) {

    }
}
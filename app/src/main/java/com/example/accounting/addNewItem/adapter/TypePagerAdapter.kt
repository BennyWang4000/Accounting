package com.example.accounting.addNewItem.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.accounting.R
import com.google.android.material.snackbar.Snackbar
import kotlin.coroutines.coroutineContext

class TypePagerAdapter() : RecyclerView.Adapter<TypePagerAdapter.PagerViewHolder>() {
    inner class PagerViewHolder(item: View): RecyclerView.ViewHolder(item){
        val radioGroup= item.findViewById<RadioGroup>(R.id.pager_type)
        val breakfast= item.findViewById<RadioButton>(R.id.radio_breakfast)
        val lunch= item.findViewById<RadioButton>(R.id.radio_lunch)
        val dinner= item.findViewById<RadioButton>(R.id.radio_dinner)
        val drinks= item.findViewById<RadioButton>(R.id.radio_drinks)
        val snake= item.findViewById<RadioButton>(R.id.radio_snake)
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
//        holder.radioGroup.setOnCheckedChangeListener { group, checkedId ->
////            val radioButton= findViewById<RadioButton>(checkedId)
//            Log.d("radioGroup", "${checkedId}")
//        }
    }
}
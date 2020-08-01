package com.example.accounting.main.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
/**
 * 將 listFragment 的東西移動到此 adapter 中
 *      施工完成後預計下一個做 item info
 *
 * 目前想到的問題；
 *      - 個數：return item count?
 * */
class ListPagerAdapter : RecyclerView.Adapter<ListPagerAdapter.PagerViewHolder>(){
    inner class PagerViewHolder(item: View): RecyclerView.ViewHolder(item){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


}
package com.example.accounting.addNewItem.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.accounting.addNewItem.AddNewViewModel
import com.example.accounting.addNewItem.typePage.AddNewTypeListFragment
import java.lang.Exception


class TypePagerAdapter(val viewModel: AddNewViewModel, activity: FragmentActivity): FragmentStateAdapter(activity){
    override fun getItemCount(): Int {
//        Log.d("viewModel.categories.value!!.size / 10 + 1", "${viewModel.categories.value!!.size / 10 + 1}")
        return viewModel.categories.value!!.size / 10 + 1
    }

    override fun createFragment(position: Int): Fragment {
        return AddNewTypeListFragment(position)
    }
}
//class TypePagerAdapter() : RecyclerView.Adapter<TypePagerAdapter.PagerViewHolder>() {
//    inner class PagerViewHolder(item: View): RecyclerView.ViewHolder(item){
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
//        return PagerViewHolder(
//            LayoutInflater
//                .from(parent.context)
//                .inflate(
//                    R.layout.add_new_pager_type
//                    , parent
//                    , false
//                )
//        )
//    }
//
//    override fun getItemCount(): Int = 2
//
//    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
////        holder.radioGroup.setOnCheckedChangeListener { group, checkedId ->
//////            val radioButton= findViewById<RadioButton>(checkedId)
////            Log.d("radioGroup", "${checkedId}")
////        }
//    }
//}
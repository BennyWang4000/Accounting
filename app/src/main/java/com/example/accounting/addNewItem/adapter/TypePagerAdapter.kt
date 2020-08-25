package com.example.accounting.addNewItem.addNewFragment.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.accounting.addNewItem.addNewFragment.AddNewViewModel
import com.example.accounting.addNewItem.pager.AddNewPagerTypeFragment


class TypePagerAdapter(val viewModel: AddNewViewModel, activity: FragmentActivity): FragmentStateAdapter(activity){
    override fun getItemCount(): Int {
        return 10
    }

    override fun createFragment(position: Int): Fragment {
        return AddNewPagerTypeFragment()
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
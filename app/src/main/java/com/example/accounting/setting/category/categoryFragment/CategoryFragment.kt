package com.example.accounting.setting.category.categoryFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.accounting.R
import com.example.accounting.setting.category.categoryFragment.adapter.CategoryRecyclerAdapter

class CategoryFragment(): Fragment() {

    private lateinit var recyclerAdapter: CategoryRecyclerAdapter
    private var behavior= -1
    constructor(behavior: Int): this(){
        this.behavior= behavior
    }

    private lateinit var viewModel: CategoryFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root=  inflater.inflate(R.layout.category_fragment, container, false)

        //view model
        val factory= CategoryFragmentViewModelFactory(activity!!.application, behavior)
        viewModel= ViewModelProvider(this, factory).get(CategoryFragmentViewModel::class.java)

        val recyclerview = root.findViewById<RecyclerView>(R.id.category_rv_category)
        recyclerAdapter = CategoryRecyclerAdapter(context!!, viewModel)
        val linearLayoutManager = LinearLayoutManager(context!!)
        recyclerview.layoutManager= linearLayoutManager
//    recyclerview.adapter = recyclerAdapter
        viewModel.behaviorCategories.observe(this, Observer{
            with(recyclerview){
                adapter = recyclerAdapter
                layoutManager= linearLayoutManager
            }
            recyclerAdapter.notifyDataSetChanged()
        })

        return root
    }
}
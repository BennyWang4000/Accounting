package com.example.accounting.addNewItem.typePage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.accounting.R
import com.example.accounting.addNewItem.typePage.adapter.TypeRecyclerAdapter


class AddNewTypeListFragment(): Fragment() {

    private lateinit var rvType: RecyclerView
    private lateinit var recyclerAdapter: TypeRecyclerAdapter

    private lateinit var viewModel: AddNewTypeListViewModel

    private var page: Int= 0

    constructor(page: Int) : this() {
        this.page= page
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.add_new_pager_type, container, false)

        //viewModel
        val factory = AddNewTypeListViewModelFactory(activity!!.application, page)
        viewModel = ViewModelProvider(this, factory).get(AddNewTypeListViewModel::class.java)

        //recyclerView
        rvType= root.findViewById(R.id.add_type_rv_)
        recyclerAdapter= TypeRecyclerAdapter(context!!, viewModel)
        val gridLayoutManager = GridLayoutManager(context!!, 5).apply {
            orientation= RecyclerView.VERTICAL
        }
        viewModel.pageCategories.observe(this, Observer {
            with(rvType) {
                adapter = recyclerAdapter
                layoutManager = gridLayoutManager
            }
        })

        return root
    }
}
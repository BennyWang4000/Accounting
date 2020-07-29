package com.example.accounting.main.listFragment

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.accounting.R

class ListFragment(val application: Application) : Fragment(){

    private lateinit var recyclerAdapter: ListRecyclerAdapter
    private lateinit var viewModel:  ListViewModel
    private lateinit var rvList:  RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root=  inflater.inflate(R.layout.main_fragment_list, container, false)

        //view model
        val factory= ListViewModelFactory(application)
        viewModel= ViewModelProvider(this, factory).get(ListViewModel::class.java)

        //recycler view
        Log.d("test", "viewModel.dateData.value.toString(): ${viewModel.dateData.value}")

        try {
            rvList= root.findViewById(R.id.rv_list)
//            viewModel.allData.value.let{}
            recyclerAdapter =
                ListRecyclerAdapter(context!!)
            rvList.adapter = recyclerAdapter
            rvList.layoutManager = LinearLayoutManager(context!!)
        } catch (e: Exception) {
            Log.d("test", "Recycler View Load Error\n$e")
        }


        //observe data
        try {
            viewModel.dateData.observe(this, Observer { item ->
                // Update the cached copy of the words in the adapter.
                item?.let {
                    Log.d("test", "ViewModel Observe")
                    recyclerAdapter.addNewItem(it)
                }
            })
        } catch (e: Exception) {
            Log.d("test", "Data Observe Error...\n$e")
        }
        return root
    }
}
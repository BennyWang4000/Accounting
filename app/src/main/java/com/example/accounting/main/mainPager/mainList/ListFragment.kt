package com.example.accounting.main.listFragment

import android.app.Application
import android.content.ContentValues
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
import com.example.accounting.main.mainPager.mainList.adapter.ListRecyclerAdapter

class ListFragment(var position: Int, val application: Application) : Fragment(){

    private lateinit var recyclerAdapter: ListRecyclerAdapter
    private lateinit var viewModel:  ListViewModel
    private lateinit var rvList:  RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root=  inflater.inflate(R.layout.main_fragment_list, container, false)

        //view model
        val factory= ListViewModelFactory(application)
        viewModel= ViewModelProvider(this, factory).get(ListViewModel::class.java)

        //recycler view
        rvList= root.findViewById(R.id.rv_list)
//           viewModel.allData.value.let{}
        recyclerAdapter = ListRecyclerAdapter(context!!, viewModel, position)
        rvList.adapter = recyclerAdapter
        rvList.layoutManager = LinearLayoutManager(context!!)

        //observe page changed
        viewModel.selectedDate.observe(this, Observer {
//            viewModel.pageChanged()
            Log.d(ContentValues.TAG, "list viewModel observe selectedDate: $it :D")
            recyclerAdapter.notifyDataSetChanged()
        })

//        viewModel.nextDayData.observe(this, Observer{})
//        viewModel.privousDayData.observe(this, Observer {})
//        viewModel.selectedDateData.observe(this, Observer{})

        //observe data
        viewModel.getDateData(position).observe(this, Observer { item ->
            // Update the cached copy of the words in the adapter.
            item?.let {
                recyclerAdapter.addNewItem(it)
                recyclerAdapter.notifyDataSetChanged()
            }
        })

        return root
    }
}
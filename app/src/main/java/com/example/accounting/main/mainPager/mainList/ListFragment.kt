package com.example.accounting.main.mainPager.mainList


import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.accounting.R
import com.example.accounting.main.listFragment.ListViewModel
import com.example.accounting.main.mainPager.mainList.adapter.ListRecyclerAdapter

class ListFragment() : Fragment(){

    private lateinit var recyclerAdapter: ListRecyclerAdapter
    private lateinit var viewModel: ListViewModel
    private lateinit var rvList:  RecyclerView
    private lateinit var tvSum: TextView
    var position: Int= -1

    constructor(p: Int) : this() {
        this.position= p
//        Log.v("Fragment position", "$position")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.main_fragment_list, container, false)

        tvSum= root.findViewById(R.id.tv_cost)

        //view model
        val factory = ListViewModelFactory(activity!!.application, position)
        viewModel = ViewModelProvider(this, factory).get(ListViewModel::class.java)
        //recycler view
        rvList = root.findViewById(R.id.rv_list)
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



//        //observe selected date
//        viewModel.selectedDate.observe(this, Observer { date ->
//            // Update the cached copy of the words in the adapter.()
//            date?.let {
////               tvToday.text= viewModel.selectedDate.value.toString
//            }
//        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //observe data
        viewModel.getDateData().observe(this, Observer { item ->
            // Update the cached copy of the words in the adapter.
            item?.let {
                tvSum.text= viewModel.getSum().toString()
                recyclerAdapter.addNewItem(it)
                recyclerAdapter.notifyDataSetChanged()
            }
        })
    }

//    override fun onResume() {
//        super.onResume()
//        Log.v("lifecycle", "onResume() -> $position")
//    }
//
//    override fun onStart() {
//        super.onStart()
//        Log.v("lifecycle", "onResume() -> $position")
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        Log.v("lifecycle", "onResume() -> $position")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.v("lifecycle", "onResume() -> $position")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.v("lifecycle", "onResume() -> $position")
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.v("lifecycle", "onResume() -> $position")
//    }
}
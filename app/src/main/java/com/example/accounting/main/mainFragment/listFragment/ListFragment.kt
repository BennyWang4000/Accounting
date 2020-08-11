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
import com.example.accounting.main.listFragment.mainFragment.adapter.ListRecyclerAdapter

class ListFragment(var position: Int, val application: Application) : Fragment(){

    private lateinit var recyclerAdapter: ListRecyclerAdapter
    private lateinit var viewModel: ListViewModel
    private lateinit var rvList: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root=  inflater.inflate(R.layout.main_fragment_list, container, false)

        //view model
        val factory= ListViewModelFactory(application)
        viewModel= ViewModelProvider(this, factory).get(ListViewModel::class.java)

        //recycler view
        rvList= root.findViewById(R.id.rv_list)
//           viewModel.allData.value.let{}
        recyclerAdapter = ListRecyclerAdapter(context!!, viewModel)
        rvList.adapter = recyclerAdapter
        rvList.layoutManager = LinearLayoutManager(context!!)


        //observe page changed
        viewModel.selectedDate.observe(this, Observer {
//            viewModel.pageChanged()
            Log.d(ContentValues.TAG, "list viewModel observe selectedDate: $it :D")
            Log.d(ContentValues.TAG, "selected date data: ${viewModel.selectedDateData.value}")

            if(viewModel.selectedDateData.value!= null) {
                recyclerAdapter.addNewItem(viewModel.selectedDateData.value!!)
            }else{
                Log.d(ContentValues.TAG, "NULL DATA...")
            }
            recyclerAdapter.notifyDataSetChanged()
        })

        // 抓到了，就是它搞得鬼。沒有這串會使得上面的資料為 Null ，有的話卻會顯示重複資料
        // **新問題，剛開的時候會沒資料
        // 第一次往左右滑會是空白的，是否要回歸原本的 p, s, n 的方式解決
        // 目前研判是 viewModel 會提前 observe 左右各一的資料，導致此狀況產生
        viewModel.selectedDateData.observe(this, Observer{
            Log.e(ContentValues.TAG, "viewModel.selectedDateData.observe...")
            recyclerAdapter.notifyDataSetChanged()
        })


//        viewModel.nextDayData.observe(this, Observer{})
//        viewModel.privousDayData.observe(this, Observer {})
//        viewModel.selectedDateData.observe(this, Observer{})

        //observe data
//        viewModel.getDateData(position).observe(this, Observer { item ->
//            // Update the cached copy of the words in the adapter.
//            item?.let {
////                recyclerAdapter.addNewItem(it)
//                Log.d(ContentValues.TAG, "current position: ${viewModel.currentPosition.value}")
//                when {
//                    position> viewModel.currentPosition.value!! -> {
//                        recyclerAdapter.addNewItem(viewModel.nextDayData.value!!)
//                    }
//                    position< viewModel.currentPosition.value!! -> {
//                        recyclerAdapter.addNewItem(viewModel.privousDayData.value!!)
//                    }
//                    else -> {
//                        recyclerAdapter.addNewItem(viewModel.selectedDateData.value!!)
//                    }
//                }
//                recyclerAdapter.notifyDataSetChanged()
//            }
//        })


//
//        viewModel.currentPosition.observe(this, Observer{
//            when {
//                position> viewModel.currentPosition.value!! -> {
//                    recyclerAdapter.addNewItem(viewModel.nextDayData.value!!)
//                }
//                position< viewModel.currentPosition.value!! -> {
//                    recyclerAdapter.addNewItem(viewModel.privousDayData.value!!)
//                }
//                else -> {
//                    recyclerAdapter.addNewItem(viewModel.selectedDateData.value!!)
//                }
//            }
//            recyclerAdapter.notifyDataSetChanged()
//
//        })

        return root
    }
}
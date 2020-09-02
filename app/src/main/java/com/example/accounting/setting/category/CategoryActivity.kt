package com.example.accounting.setting.category

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.accounting.R
import com.example.accounting.setting.category.addNewCategory.AddNewCategoryActivity
import com.example.accounting.setting.category.categoryFragment.CategoryFragment
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout

class CategoryActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener {

    private lateinit var viewModel: CategoryViewModel
    private lateinit var layoutReceipt: LinearLayout

    private lateinit var tabExpenseItem: TabItem
    private lateinit var tabIncomeItem: TabItem

    private val frgExpense= CategoryFragment(0)
    private val frgIncome= CategoryFragment(1)
    var transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

    private var behavior= 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category_activity)
        layoutReceipt= findViewById(R.id.category_layout_bottom_add_new)
        layoutReceipt.setOnClickListener(this::clickListener)

        //view model
        val factory= CategoryViewModelFactory(application)
        viewModel= ViewModelProvider(this, factory).get(CategoryViewModel::class.java)

//        frgExpense= CategoryFragment(0)
//        frgIncome= CategoryFragment(1)
        transaction.apply {
            replace(R.id.category_frg, frgExpense)
            commit()
        }


        val tabLayout= findViewById<TabLayout>(R.id.tablayout_category)
        tabLayout.addOnTabSelectedListener(this)
    }

    //fragment


    override fun onTabSelected(tab: TabLayout.Tab?) {
        when(tab!!.position) {
            0 -> {
                Log.v("switch", "expense")
                behavior= 0

                transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.category_frg, frgExpense)
                transaction.commit()
            }
            1 -> {
                Log.v("switch", "income")
                behavior= 1

                transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.category_frg, frgIncome)
                transaction.commit()
            }
            else -> {
            }
        }
    }


    private fun clickListener(v: View) {
        when (v.id){
            R.id.category_layout_bottom_add_new ->{
                startActivity(Intent(this, AddNewCategoryActivity::class.java).putExtra("behavior", behavior))
            }
        }
    }
    override fun onTabUnselected(tab: TabLayout.Tab?){}
    override fun onTabReselected(tab: TabLayout.Tab?){}
}
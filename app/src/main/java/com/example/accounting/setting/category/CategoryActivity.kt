package com.example.accounting.setting.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.accounting.R
import com.example.accounting.setting.category.categoryFragment.CategoryFragment
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout

class CategoryActivity : AppCompatActivity() {

    private lateinit var viewModel: CategoryViewModel

    private lateinit var tabExpenseItem: TabItem
    private lateinit var tabIncomeItem: TabItem

    val frgCategoryExpense= CategoryFragment(0)
    val frgCategoryIncome= CategoryFragment(1)
    var transaction: FragmentTransaction = supportFragmentManager.beginTransaction()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category_activity)

        //view model
        val factory= CategoryViewModelFactory(application)
        viewModel= ViewModelProvider(this, factory).get(CategoryViewModel::class.java)

//        tabExpenseItem= findViewById(R.id.category_tab_item_expense)
//        tabIncomeItem= findViewById(R.id.category_tab_item_income)
        //initial fragment
//        transaction.add(R.id.category_frg, frgCategoryExpense)
//        transaction.commit()

        val tabLayout= findViewById<TabLayout>(R.id.tablayout_category).apply {
            addOnTabSelectedListener(this)
        }
    }

    private fun addOnTabSelectedListener(tabLayout: TabLayout?) {
        when(tabLayout!!.selectedTabPosition){
            0 -> {
                Log.v("switch", "expense")
                transaction.replace(R.id.category_frg, frgCategoryExpense)
                transaction.commit()
            }
            1 -> {
                Log.v("switch", "income")
                transaction.replace(R.id.category_frg, frgCategoryIncome)
                transaction.commit()
            }
        }
    }

}
package com.example.accounting.main

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.accounting.R
import com.example.accounting.addNewItem.AddNewActivity
import com.example.accounting.pieChart.PieChartActivity
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDate

class MainDrawerActivity : AppCompatActivity() {

    private lateinit var factory: MainDrawerVIewModelFactory
    private lateinit var viewModel: MainDrawerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        //設回原本之 theme
        setTheme(R.style.ExpenseTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_drawer_activity)
        //view model
        factory= MainDrawerVIewModelFactory(application)
        viewModel= ViewModelProvider(this, factory).get(MainDrawerViewModel::class.java)

        //fragment
        val frg= MainPagerFragment()
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_fragment, frg)
        transaction.commit()

        //navigation drawer
        val drawerLayout= findViewById<DrawerLayout>(R.id.layout_drawer)
        val drawer: NavigationView= findViewById(R.id.navigation_drawer)
        drawer.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.item_main_expense -> {
                    setTheme(R.style.ExpenseTheme)
                    val frgExpense= MainPagerFragment()
                    transaction.replace(R.id.main_fragment, frgExpense)
                    transaction.commit()
                }
                R.id.item_main_income -> {
                    setTheme(R.style.IncomeTheme)
                    val frgIncome= MainPagerFragment()
                    transaction.replace(R.id.main_fragment, frgIncome)
                    transaction.commit()
                }
                R.id.item_main_analysis -> {
                    startActivity(Intent(this, PieChartActivity::class.java))
                }
                else -> {}
            }
            return@setNavigationItemSelectedListener true
        }

        //tool bar
        val toolbar= findViewById<Toolbar>(R.id.toolbar_main)
        toolbar.overflowIcon = AppCompatResources.getDrawable(
            application.applicationContext,
            R.drawable.ic_baseline_more_vert_24_white
        )
        toolbar.inflateMenu(R.menu.main_toolbar)
        toolbar.setNavigationOnClickListener {
            if (drawerLayout.isDrawerOpen(drawer)) {
                drawerLayout.closeDrawer(drawer)
            } else {
                drawerLayout.openDrawer(drawer)
            }
        }
        toolbar.setOnMenuItemClickListener{
            when(it.itemId){
                R.id.menu_add -> {
                    val intent= Intent(this, AddNewActivity::class.java)
                    startActivityForResult(intent, 1)
                    return@setOnMenuItemClickListener true
                }
                R.id.menu_date -> {
                    DatePickerDialog(
                        this, { _, year, month, day ->
                            run {
                                viewModel.selectedDate.value= LocalDate.parse(
                                    "$year-" +
                                            String.format("%02d", month+ 1)+ "-" +
                                            String.format("%02d", day))

//                            changeFrg(application)
                            }
                        }, viewModel.selectedDate.value!!.year
                        , String.format("%02d", viewModel.selectedDate.value!!.monthValue.plus(-1)).toInt()
                        , String.format("%02d", viewModel.selectedDate.value!!.dayOfMonth).toInt())
                        .show()

                    return@setOnMenuItemClickListener true
                }

                else -> {
                    Snackbar.make(findViewById(R.id.layout_main), "Unknown Issue", Snackbar.LENGTH_SHORT)
                        .show()
                    return@setOnMenuItemClickListener true
                }
            }
        }
        toolbar.title= viewModel.selectedDate.value.toString()
        viewModel.selectedDate.observe(this, Observer {
            toolbar.title= it.toString()
        })
    }
}
package com.example.accounting.main

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.accounting.R
import com.example.accounting.main.listFragment.mainFragment.MainViewModel
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDate

class MainDrawerActivity : AppCompatActivity() {

    private lateinit var factory: MainDrawerVIewModelFactory
    private lateinit var viewModel: MainDrawerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_drawer_activity)

        //view model
        factory= MainDrawerVIewModelFactory(application)
        viewModel= ViewModelProvider(this, factory).get(MainDrawerViewModel::class.java)

        //fragment
        val frg= MainFragment(application)
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_fragment, frg)
        transaction.commit()



    }
}
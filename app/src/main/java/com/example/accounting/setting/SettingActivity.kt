package com.example.accounting.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import com.example.accounting.R
import com.example.accounting.setting.budget.BudgetActivity
import com.example.accounting.setting.category.CategoryActivity

class SettingActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var layoutBudget: LinearLayout
    private lateinit var layoutAccount: LinearLayout
    private lateinit var layoutCurrency: LinearLayout
    private lateinit var layoutPasscode: LinearLayout
    private lateinit var layoutCategory: LinearLayout
    private lateinit var layoutBackup: LinearLayout
    private lateinit var layoutInfo: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting_activity)

        layoutBudget= findViewById(R.id.setting_layout_budget)
        layoutAccount= findViewById(R.id.setting_layout_account)
        layoutCurrency= findViewById(R.id.setting_layout_currency)
        layoutPasscode= findViewById(R.id.setting_layout_passcode)
        layoutCategory= findViewById(R.id.setting_layout_category)
        layoutBackup= findViewById(R.id.setting_layout_backup)
        layoutInfo= findViewById(R.id.setting_layout_info)
        layoutBudget.setOnClickListener(this)
        layoutAccount.setOnClickListener(this)
        layoutCurrency.setOnClickListener(this)
        layoutPasscode.setOnClickListener(this)
        layoutCategory.setOnClickListener(this)
        layoutBackup.setOnClickListener(this)
        layoutInfo.setOnClickListener(this)


        //tool bar
        val toolbar= findViewById<Toolbar>(R.id.toolbar_setting)
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.setting_layout_budget -> startActivity(Intent(this, BudgetActivity::class.java))
            R.id.setting_layout_account -> startActivity(Intent(this, BudgetActivity::class.java))
            R.id.setting_layout_currency -> startActivity(Intent(this, BudgetActivity::class.java))
            R.id.setting_layout_passcode -> startActivity(Intent(this, BudgetActivity::class.java))
            R.id.setting_layout_category -> startActivity(Intent(this, CategoryActivity::class.java))
            R.id.setting_layout_backup -> startActivity(Intent(this, BudgetActivity::class.java))
            R.id.setting_layout_info -> startActivity(Intent(this, BudgetActivity::class.java))
        }
    }
}
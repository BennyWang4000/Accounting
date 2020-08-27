package com.example.accounting.setting.budget


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.accounting.R
import com.google.android.material.bottomsheet.BottomSheetBehavior

class BudgetActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private lateinit var tvBudget: TextView

    private lateinit var viewModel: BudgetViewModel

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.budget_activity)
        tvBudget= findViewById(R.id.budget_tv_budget)
        //view model
        val factory= BudgetViewModelFactory(application)
        viewModel= ViewModelProvider(this, factory).get(BudgetViewModel::class.java)

        //init switch
        val switchIsShow= findViewById<Switch>(R.id.budget_sw_is_show)
        switchIsShow.isChecked= true
        //TODO("viewModel.settings.value!![0].budgetShow")

        //tool bar
        val toolbar= findViewById<Toolbar>(R.id.toolbar_budget)
        toolbar.overflowIcon = AppCompatResources.getDrawable(
            application.applicationContext,
            R.drawable.ic_baseline_more_vert_24_white
        )
        toolbar.inflateMenu(R.menu.save_tool_bar)
        toolbar.setNavigationOnClickListener {
            finish()
        }
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.item_save -> {
                    viewModel.updateBudgetShow(switchIsShow.isChecked)
                    viewModel.updateBudgetAmount()
                }else -> {}
            }
            finish()
            return@setOnMenuItemClickListener true
        }

//        // switch
//        val switchIsShow= findViewById<Switch>(R.id.budget_sw_is_show)
//        switchIsShow.setOnCheckedChangeListener { buttonView, isChecked ->
//            viewModel.settings.value!![0].budgetShow= isChecked
//            if(isChecked){
//                // 選
//            }else{
//                // 不選
//            }
//        }


        //calculator
        //bottom behavior
        val calculatorLayout= findViewById<LinearLayout>(R.id.layout_calculator)
        bottomSheetBehavior= BottomSheetBehavior.from(calculatorLayout)
        val layoutBudget= findViewById<LinearLayout>(R.id.budget_layout_budget)
        tvBudget= findViewById<TextView>(R.id.budget_tv_budget)
        layoutBudget.setOnClickListener {
            if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
//                tvCost.text = "Close sheet"
            } else {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
//                tvCost.text = "Expand sheet"
            }
        }
        //calculate
        //button
        val bt1= findViewById<Button>(R.id.bt_1)
        val bt2= findViewById<Button>(R.id.bt_2)
        val bt3= findViewById<Button>(R.id.bt_3)
        val bt4= findViewById<Button>(R.id.bt_4)
        val bt5= findViewById<Button>(R.id.bt_5)
        val bt6= findViewById<Button>(R.id.bt_6)
        val bt7= findViewById<Button>(R.id.bt_7)
        val bt8= findViewById<Button>(R.id.bt_8)
        val bt9= findViewById<Button>(R.id.bt_9)
        val bt0= findViewById<Button>(R.id.bt_0)
        val btAC= findViewById<Button>(R.id.bt_ac)
        val btOK= findViewById<Button>(R.id.bt_ok)
        val btAdd= findViewById<Button>(R.id.bt_plus)
        val btDot= findViewById<Button>(R.id.bt_point)
        val btMinus= findViewById<Button>(R.id.bt_minus)
        val btDiverse= findViewById<Button>(R.id.bt_divided)
        val btProduct= findViewById<Button>(R.id.bt_multiplied)
        val btBackspace= findViewById<Button>(R.id.bt_backspace)



        bt1.setOnClickListener(this)
        bt1.setOnClickListener(this)
        bt2.setOnClickListener(this)
        bt3.setOnClickListener(this)
        bt4.setOnClickListener(this)
        bt5.setOnClickListener(this)
        bt6.setOnClickListener(this)
        bt7.setOnClickListener(this)
        bt8.setOnClickListener(this)
        bt9.setOnClickListener(this)
        bt0.setOnClickListener(this)
        btAC.setOnClickListener(this)
        btOK.setOnClickListener(this)
        btDot.setOnClickListener(this)
        btAdd.setOnClickListener(this)
        btMinus.setOnClickListener(this)
        btDiverse.setOnClickListener(this)
        btProduct.setOnClickListener(this)
        btBackspace.setOnClickListener(this)

        //init tv content
        viewModel.settings.observe(this, Observer {
            tvBudget.text= it[0].budgetAmount.toString()
        })

        viewModel.operand2.observe(this, Observer {
            if (viewModel.isOperating.value!!) {
                Log.e("operand2: ", "${viewModel.operand2.value}")
                tvBudget.text= viewModel.operand2.value
            }
        })
        viewModel.operand1.observe(this, Observer {
            if (!viewModel.isOperating.value!!) {
                Log.e("operand1: ", "${viewModel.operand1.value}")
                tvBudget.text= viewModel.operand1.value
            }
        })

        viewModel.isOperating.observe(this, Observer{
            Log.e("isOperating: ", "${viewModel.isOperating.value}")
        })
    }

    override fun onClick(v: View?) {
        when (v!!.id){
            R.id.bt_1 -> {viewModel.clickNum("1")}
            R.id.bt_2 -> {viewModel.clickNum("2")}
            R.id.bt_3 -> {viewModel.clickNum("3")}
            R.id.bt_4 -> {viewModel.clickNum("4")}
            R.id.bt_5 -> {viewModel.clickNum("5")}
            R.id.bt_6 -> {viewModel.clickNum("6")}
            R.id.bt_7 -> {viewModel.clickNum("7")}
            R.id.bt_8 -> {viewModel.clickNum("8")}
            R.id.bt_9 -> {viewModel.clickNum("9")}
            R.id.bt_0 -> {viewModel.clickNum("0")}
            R.id.bt_ac -> {viewModel.clickAC()}
            R.id.bt_point -> {viewModel.clickNum(".")}
            R.id.bt_plus -> {viewModel.clickOperator(1)}
            R.id.bt_minus -> {viewModel.clickOperator(2)}
            R.id.bt_multiplied -> {viewModel.clickOperator(3)}
            R.id.bt_divided -> {viewModel.clickOperator(4)}
            R.id.bt_backspace -> {viewModel.clickBackspace()}
            R.id.bt_ok -> {
                tvBudget.text= viewModel.clickOK()
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }
    }
}
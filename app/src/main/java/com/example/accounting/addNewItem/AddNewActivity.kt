package com.example.accounting.addNewItem

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.accounting.R
import com.example.accounting.addNewItem.addNewFragment.AddNewViewModel
import com.example.accounting.addNewItem.addNewFragment.AddNewViewModelFactory
import com.example.accounting.addNewItem.addNewFragment.adapter.TypePagerAdapter
import com.example.accounting.database.model.ExpenseEntity
import com.example.accounting.editText.EditTextActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar

class AddNewActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var pagerType: ViewPager2
    private lateinit var pagerTypeAdapter: TypePagerAdapter
    private lateinit var tvCost: TextView
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    private lateinit var layoutTitle: LinearLayout
    private lateinit var layoutNote: LinearLayout
    private lateinit var layoutExpense: LinearLayout

    private lateinit var tvTitle: TextView
    private lateinit var tvNote: TextView
    private lateinit var tvExpense: TextView

    private val REQUEST_CODE_TITLE= 1
    private val REQUEST_CODE_NOTE= 2
    private val REQUEST_CODE_EXPENSE= 3

    private lateinit var viewModel: AddNewViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_new_activity)

        val tvToday: TextView= findViewById(R.id.tv_today)
        tvTitle= findViewById(R.id.tv_title)
        tvNote= findViewById(R.id.tv_note)
        tvExpense= findViewById(R.id.tv_expense)

        layoutTitle= findViewById(R.id.layout_edit_title_container)
        layoutNote= findViewById(R.id.layout_edit_note_container)
        layoutExpense= findViewById(R.id.layout_edit_expense_container)
        layoutTitle.setOnClickListener(this)
        layoutNote.setOnClickListener(this)
        layoutExpense.setOnClickListener(this)

        //view model
        val factory= AddNewViewModelFactory(application)
        viewModel= ViewModelProvider(this, factory).get(AddNewViewModel::class.java)

        //view pager
        pagerType= findViewById(R.id.pager_type)
//        val viewList= listOf(R.layout.add_new_pager_type, R.layout.add_new_pager_type_2)
//        pagerTypeAdapter= AddNewViewPagerAdapter()
//        pagerType.adapter= pagerTypeAdapter
        val typePagerAdapter= TypePagerAdapter()
        pagerType.adapter= typePagerAdapter

        tvToday.text= viewModel.selectedDate.value.toString()

        //observe
        viewModel.selectedDate.observe(this, androidx.lifecycle.Observer {
            try {
                tvToday.text= viewModel.selectedDate.value.toString()
            } catch (e: Exception) {
                Snackbar.make(this.findViewById(R.id.layout_main), "$e", Snackbar.LENGTH_SHORT)
                    .show()
            }
        })

        //tool bar
        val toolbar= findViewById<Toolbar>(R.id.toolbar_add)
        toolbar.inflateMenu(R.menu.save_tool_bar)
        toolbar.setNavigationOnClickListener {
            finish()
        }
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.item_save -> {
                    val newItem = ExpenseEntity(
                        0,
                        viewModel.operand1.value!!.toDouble(),
                        viewModel.selectedDate.value!!.toString(),
                        viewModel.descr.value!!,
                        1,
                        1,
                        1
                    )
                    Log.d(
                        ContentValues.TAG,
                        "Add New Item Date: " + viewModel.selectedDate.value.toString()
                    )
                    viewModel.insertItem(newItem)


                }else -> {}
            }
            finish()
            return@setOnMenuItemClickListener true
        }


        //calculator
        //bottom behavior
        val calculatorLayout= findViewById<LinearLayout>(R.id.layout_calculator)
        bottomSheetBehavior= BottomSheetBehavior.from(calculatorLayout)
        val layoutCost= findViewById<LinearLayout>(R.id.layout_costs)
        tvCost= findViewById<TextView>(R.id.tv_add_cost)
        layoutCost.setOnClickListener {
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
        val btDot= findViewById<Button>(R.id.bt_point)
        val btAdd= findViewById<Button>(R.id.bt_plus)
        val btMinus= findViewById<Button>(R.id.bt_minus)
        val btDiverse= findViewById<Button>(R.id.bt_divided)
        val btProduct= findViewById<Button>(R.id.bt_multiplied)
        val btBackspace= findViewById<ImageButton>(R.id.bt_backspace)

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

        viewModel.operand2.observe(this, Observer {
            if (viewModel.isOperating.value!!) {
                Log.e("operand2: ", "${viewModel.operand2.value}")
                tvCost.text= viewModel.operand2.value
            }
        })
        viewModel.operand1.observe(this, Observer {
            if (!viewModel.isOperating.value!!) {
                Log.e("operand1: ", "${viewModel.operand1.value}")
                tvCost.text= viewModel.operand1.value
            }
        })

        viewModel.isOperating.observe(this, Observer{
            Log.e("isOperating: ", "${viewModel.isOperating.value}")
        })
    }

//    val calculator= Calculator()

    override fun onClick(v: View?) {
        when (v!!.id){
            R.id.layout_edit_title_container -> {
                Log.d("layout press", "title")
                startActivityForResult(Intent(this, EditTextActivity::class.java), REQUEST_CODE_TITLE)
            }
            R.id.layout_edit_note_container -> {
                Log.d("layout press", "note")
                startActivityForResult(Intent(this, EditTextActivity::class.java), REQUEST_CODE_NOTE)
            }
            R.id.layout_edit_expense_container -> {
                Log.d("layout press", "expense")
                startActivityForResult(Intent(this, EditTextActivity::class.java), REQUEST_CODE_EXPENSE)

            }
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
                tvCost.text= viewModel.clickOK()
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            REQUEST_CODE_TITLE -> {
                viewModel.descr.value= data!!.getStringExtra("content")
                tvTitle.text= viewModel.descr.value
            }
            REQUEST_CODE_NOTE -> {
                viewModel.account.value= 0
                tvNote.text= viewModel.account.value.toString()
            }
            REQUEST_CODE_EXPENSE -> {
                Snackbar.make(this.findViewById<ConstraintLayout>(R.id.layout_add_new), ":D", Snackbar.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }
}


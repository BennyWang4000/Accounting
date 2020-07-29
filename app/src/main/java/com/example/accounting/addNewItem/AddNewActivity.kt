package com.example.accounting.addNewItem

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.accounting.R
import com.example.accounting.room.ItemEntity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class AddNewActivity : AppCompatActivity() {

    private lateinit var pagerType: ViewPager2
    private lateinit var pagerAdapter: AddNewViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_new_activity)
        val etPrice: EditText= findViewById(R.id.et_cost)
        val etName: EditText= findViewById(R.id.et_name)
        val etNote: EditText= findViewById(R.id.et_note)
        val btAdd: FloatingActionButton = findViewById(R.id.flt_bt_save)

        //view model
        val factory= AddNewViewModelFactory(application)
        val viewModel= ViewModelProvider(this, factory).get(AddNewViewModel::class.java)
//        viewModel= ViewModelProvider(this).get(MainViewModel::class.java)

        //view pager
        val viewList= listOf(R.layout.add_new_pager_type_1, R.layout.add_new_pager_type_2)
        pagerAdapter= AddNewViewPagerAdapter()


        //button is clicked
        btAdd.setOnClickListener{
            var newItem= ItemEntity(
                0,
                ""+ Calendar.getInstance().get(Calendar.YEAR)+
                        (Calendar.getInstance().get(Calendar.MONTH)+ 1)+
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
                0,
                "type",
                etName.text.toString(),
                etNote.text.toString(),
                etPrice.text.toString().toInt()
            )

            viewModel.insertItem(newItem)
            //貌似不符合 MVVM 原則 ：view 之間相互碰觸
//            val intent= Intent()
//            intent.putExtra("price", etPrice.text)
//            intent.putExtra("body", etBody.text)
//            setResult(1, intent)
//
//            Log.e("test", "Save Button Clicked!")
            finish()
        }


    }
}
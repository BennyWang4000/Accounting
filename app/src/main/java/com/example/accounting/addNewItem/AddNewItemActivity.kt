package com.example.accounting.addNewItem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.accounting.R

class AddNewItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_new_item_activity)

        val btAdd: Button= findViewById(R.id.flt_bt_save)
        btAdd.setOnClickListener{
finish()
}
}
}
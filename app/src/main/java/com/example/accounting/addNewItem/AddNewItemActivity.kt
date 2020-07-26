package com.example.accounting.addNewItem

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import com.example.accounting.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AddNewItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_new_item_activity)
        val etPrice: EditText= findViewById(R.id.et_cost)
        val etBody: EditText= findViewById(R.id.et_body)
        val btAdd: FloatingActionButton = findViewById(R.id.flt_bt_save)
        btAdd.setOnClickListener{
            val intent= Intent()
            intent.putExtra("price", etPrice.text)
            intent.putExtra("body", etBody.text)
            setResult(Activity.RESULT_OK, intent)

            Log.e("test", "Button Clicked!")
            finish()
        }
    }
}
package com.example.accounting.editArea

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.Toolbar
import com.example.accounting.R

/**
 *  use startActivityForResult()
 * */
class EditAreaActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_text_activity)

        val editArea= EditArea()

        val RESULT_CODE_OK= 1
        val RESULT_CODE_CANCELED = -1

        Log.d("test", "${callingActivity}")

        val etEditArea= findViewById<EditText>(R.id.et_edit_area)


        //toolbar
        //tool bar
        val toolbar= findViewById<Toolbar>(R.id.toolbar_edit)
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
                    setResult(
                        RESULT_CODE_OK,
                        Intent().putExtra("content", editArea.isValid(etEditArea.text.toString()))
                    )
                }else -> {}
            }
            finish()
            return@setOnMenuItemClickListener true
        }
    }
}
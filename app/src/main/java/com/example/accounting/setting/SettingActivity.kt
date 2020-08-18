package com.example.accounting.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.Toolbar
import com.example.accounting.R

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting_activity)

        //tool bar
        val toolbar= findViewById<Toolbar>(R.id.toolbar_setting)
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }
}
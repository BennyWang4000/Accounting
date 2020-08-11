package com.example.accounting.itemInfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.accounting.R

/**
 * 理想：可以用 recycler view 中的 position 選擇 table 中的 id
 * */

class ItemInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_info_activity)
    }
}
package com.example.accounting.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.accounting.R


class YesNoDialog(context: Context): Dialog(context){

//    private lateinit var btPositive
//    private lateinit var btNegative: Button
//    private lateinit var tvContent
    private var message: String?= null

    private var cancelListener: IOnCancelListener? = null
    private var confirmListener: IOnConfirmListener? = null

//    private val clickListener: IClickListener? = null
//
//    interface IClickListener {
//
//    }
    fun setMessage(message: String?): YesNoDialog {
        this.message = message
        return this
    }

    fun setConfirm(Listener: IOnConfirmListener): YesNoDialog {
        this.confirmListener = Listener
        return this
    }

    fun setCancel(Listener: IOnCancelListener): YesNoDialog {
        this.cancelListener = Listener
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_yes_no);

        val btNegative: Button= findViewById(R.id.dialog_negative)
        val btPositive: Button= findViewById(R.id.dialog_positive)
        val tvContent: TextView= findViewById(R.id.dialog_yes_no_message)

        message?.let {
            tvContent.text= it
        }


        btPositive.setOnClickListener(this::clickListener)
        btNegative.setOnClickListener(this::clickListener)
    }

    private fun clickListener(v: View){
        when(v.id){
            R.id.dialog_positive -> {
                confirmListener?.let {
                    it.onConfirm(this)
                }
            }
            R.id.dialog_negative -> {
                cancelListener?.let {
                    it.onCancel(this)
                }
            }
        }
    }


    interface IOnCancelListener {
        fun onCancel(dialog: YesNoDialog?)
    }

    interface IOnConfirmListener {
        fun onConfirm(dialog: YesNoDialog?)
    }
}

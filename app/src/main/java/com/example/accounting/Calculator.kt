package com.example.accounting

import androidx.lifecycle.MutableLiveData

class Calculator{

    var operand1= MutableLiveData<String>("0")
    var operand2= MutableLiveData<String>("0")
    var operator= MutableLiveData<Int>(0)
    var isOperating= MutableLiveData<Boolean>(false)

    fun clickNumber(num: String){
        if(!isOperating.value!!){
            if(operand1.value== "0"|| operand1.value== ""){
                operand1.value= num
            }else{
                operand1.value= (operand1.value!!+ num)
            }
        }else{
            if(operand2.value== "0"|| operand2.value== ""){
                operand2.value= num
            }else{
                operand2.value= (operand2.value!!+ num)
            }
        }
    }

    fun clickOperator(operator: Int){
        this.operator.value= operator
        isOperating.value= true
    }

    fun clickAC(){
        operand1.value= "0"
        operand2.value= ""
        operator.value= 0
        isOperating.value= false
    }

    fun clickOK(): String{
        isOperating.value= false
        operand1.value=  when (operator.value){
            1 -> (operand1.value!!.toDouble()+ operand2.value!!.toDouble()).toString()
            2 -> (operand1.value!!.toDouble()- operand2.value!!.toDouble()).toString()
            3 -> (operand1.value!!.toDouble()* operand2.value!!.toDouble()).toString()
            4 -> (operand1.value!!.toDouble()/ operand2.value!!.toDouble()).toString()
            else -> operand1.value!!
        }
        operator.value= 0
        operand2.value= "0"
        return operand1.value.toString()
    }

    fun clickBackspace() {
        if(!isOperating.value!!){
            if(operand1.value== "0"|| operand1.value== ""){
                operand1.value= "0"
            }else{
                operand1.value= operand1.value!!.substring(0, operand1.value!!.length- 1)
            }
        }else{
            if(operand2.value== "0"|| operand2.value== ""){
                operand2.value= "0"
            }else{
                operand2.value= operand2.value!!.substring(0, operand2.value!!.length- 1)
            }
        }
    }
}
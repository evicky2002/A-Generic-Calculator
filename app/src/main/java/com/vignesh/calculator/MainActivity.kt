package com.vignesh.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var btnOne: Button? = null
    private var btnTwo: Button? = null
    private var btnThree: Button? = null
    private var btnFour: Button? = null
    private var btnFive: Button? = null
    private var btnSix: Button? = null
    private var btnSeven: Button? = null
    private var btnEight: Button? = null
    private var btnNine: Button? = null
    private var btnZero: Button? = null
    private var btnAdd: Button? = null
    private var btnSubtract: Button? = null
    private var btnMultiply: Button? = null
    private var btnDivide: Button? = null
    private var btnClear: Button? = null
    private var btnEqual: Button? = null

    private var tvDisplay: TextView? = null

    private var isNumeric = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvDisplay = findViewById(R.id.tvDisplay)
        loadingButtons()
        btnOne?.setOnClickListener(this)
        btnTwo?.setOnClickListener(this)
        btnThree?.setOnClickListener(this)
        btnFour?.setOnClickListener(this)
        btnFive?.setOnClickListener(this)
        btnSix?.setOnClickListener(this)
        btnSeven?.setOnClickListener(this)
        btnEight?.setOnClickListener(this)
        btnNine?.setOnClickListener(this)
        btnZero?.setOnClickListener(this)
        btnClear?.setOnClickListener(this)
        btnAdd?.setOnClickListener(this)
        btnSubtract?.setOnClickListener(this)
        btnMultiply?.setOnClickListener(this)
        btnDivide?.setOnClickListener(this)
        btnClear?.setOnClickListener(this)
        btnEqual?.setOnClickListener(this)
    }
    private fun loadingButtons(){
        btnOne = findViewById(R.id.btnOne)
        btnTwo = findViewById(R.id.btnTwo)
        btnThree = findViewById(R.id.btnThree)
        btnFour = findViewById(R.id.btnFour)
        btnFive = findViewById(R.id.btnFive)
        btnSix = findViewById(R.id.btnSix)
        btnSeven = findViewById(R.id.btnSeven)
        btnEight = findViewById(R.id.btnEight)
        btnNine = findViewById(R.id.btnNine)
        btnZero = findViewById(R.id.btnZero)
        btnAdd = findViewById(R.id.btnAdd)
        btnSubtract = findViewById(R.id.btnSubtract)
        btnMultiply = findViewById(R.id.btnMultiply)
        btnDivide = findViewById(R.id.btnDivide)
        btnClear = findViewById(R.id.btnClear)
        btnEqual = findViewById(R.id.btnEqual)
    }

    override fun onClick(view: View?) {
        if(view?.id == R.id.btnAdd || view?.id == R.id.btnSubtract || view?.id == R.id.btnMultiply || view?.id == R.id.btnDivide){
            operatorHelper(view)
        }else if(view?.id == R.id.btnClear){
            tvDisplay?.text = ""
        }else if(view?.id == R.id.btnEqual){
            if(isNumeric){
                var tvValue = tvDisplay?.text.toString()
                var prefix = ""

                try{
                    if(tvValue.startsWith("-")){
                        prefix = "-"
                        tvValue = tvValue.substring(1)
                    }
                    if(tvValue.contains("-")) {
                        val splitValue = tvValue.split("-")
                        var one = splitValue[0]
                        var two = splitValue[1]
                        if (prefix.isNotEmpty()) {
                            one = prefix + one
                        }
                        var result = (one.toDouble() - two.toDouble()).toString()
                        if(result.contains(".0")){
                            result = result.substring(0,result.length-2)
                        }
                        tvDisplay?.text = result
                    }else if(tvValue.contains("+")){
                        val splitValue = tvValue.split("+")
                        var one = splitValue[0]
                        var two = splitValue[1]
                        if(prefix.isNotEmpty()){
                            one = prefix+one
                        }
                        var result = (one.toDouble() + two.toDouble()).toString()
                        if(result.contains(".0")){
                            result = result.substring(0,result.length-2)
                        }
                        tvDisplay?.text = result
                    }else if(tvValue.contains("*")){
                        println("multiply : herer")
                        val splitValue = tvValue.split("*")
                        var one = splitValue[0]
                        var two = splitValue[1]
                        println("multiply : $one")
                        println("multiply : $two")

                        if(prefix.isNotEmpty()){
                            one = prefix+one
                        }
                        var result = (one.toDouble() * two.toDouble()).toString()
                        if(result.contains(".0")){
                            result = result.substring(0,result.length-2)
                        }
                        tvDisplay?.text = result
                        println("multiply : $result")
                    }else if(tvValue.contains("/")){
                        val splitValue = tvValue.split("/")
                        var one = splitValue[0]
                        var two = splitValue[1]
                        if(prefix.isNotEmpty()){
                            one = prefix+one
                        }
                        var result = (one.toDouble() / two.toDouble()).toString()
                        if(result.contains(".0")){
                            result = result.substring(0,result.length-2)
                        }
                        tvDisplay?.text = result
                    }

                }catch (e: ArithmeticException){
                    e.printStackTrace()
                }
            }

        }else{
            tvDisplay?.append((view as Button).text)
            isNumeric = true
        }
    }

    private fun operatorHelper(view: View){
        tvDisplay?.text.let {
            if(isNumeric && !isOperatorAdded(it.toString())){
                tvDisplay?.append((view as Button).text)
                isNumeric = false
            }
        }
    }
    private fun isOperatorAdded(value: String):Boolean{
        return if(value.startsWith("-")){
            false
            }else{
                value.contains("/") || value.contains("*") || value.contains("-") || value.contains("+")
        }
    }
}
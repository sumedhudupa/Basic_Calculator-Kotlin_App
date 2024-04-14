package com.vitacc.vit

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.time.times

class MainActivity : AppCompatActivity() {
    private var type: Int = 5
    private var num2:Int = 0
    private var ans: Int = 1
    private var canaddition = false
    private var decimal = true
    private lateinit var tv: TextView
    private lateinit var wv: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        tv= findViewById(R.id.result)
        wv= findViewById(R.id.working)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }
    fun erase(view:View){
        wv.append("Erasing..")
        Thread.sleep(200)
        tv.text = ""
        wv.text=""
    }
    fun addition(view:View){

        if(view is Button){
            if(view.text == "."){
                if(decimal){
                    wv.append(view.text)
                    decimal = false
                }
            }
            else{
                wv.append(view.text)
            }
            canaddition = true
        }
    }
    fun operation(view:View){

        if(view is Button && canaddition){
            canaddition = false
            decimal = true
            wv.append(view.text)
        }
    }
    fun backspace(view:View){
        val length = wv.length()
        if(length>0){
//            wv.text = ""
            wv.text = wv.text.subSequence(0,length-1)
        }
    }
    fun multiply(view: View){
        try {
            ans = wv.text.toString().toIntOrNull() ?: 0
//            tv.append(ans.toString())
            wv.append("x")
            type = 3
        } catch (nfe: NumberFormatException) {
            // not a valid int
        }
        wv.text = ""

    }
    fun add(view: View){
        try {
            ans = wv.text.toString().toIntOrNull() ?: 0
            type = 1
            wv.append("+")
        } catch (nfe: NumberFormatException) {
            // not a valid int
        }
        wv.text = ""
    }
    fun subtract(view: View){
        try {
            ans = wv.text.toString().toIntOrNull() ?: 0
            type = 2
            wv.append("-")
        } catch (nfe: NumberFormatException) {
            // not a valid int
        }
        wv.text = ""
    }
    fun divide(view: View){
        try {
            ans = wv.text.toString().toIntOrNull() ?: 0
            type = 4
            wv.append("/")
        } catch (nfe: NumberFormatException) {
            // not a valid int
        }
        wv.text = ""
    }
    fun percentage(view: View){
        ans = wv.text.toString().toIntOrNull() ?: 0
        wv.text =""
        tv.text=""
        tv.append((ans.toFloat()/100).toString())
        type = 5
    }
    fun equalsAction(view: View)
    {
        num2 = wv.text.toString().toIntOrNull() ?: 0
        wv.text = ""
        tv.text=""
        if(type == 1){
            tv.append((ans + num2).toString())
        }
        else if(type == 2){
            tv.append((ans - num2).toString())
        }
        else if(type == 3){
            tv.append((ans * num2).toString())
        }
        else if(type == 4){
            if(num2==0){
                tv.append("Invalid")
            }
            else tv.append((ans / num2).toString())
        }
    }


}
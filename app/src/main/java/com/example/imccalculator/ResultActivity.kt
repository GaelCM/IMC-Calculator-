package com.example.imccalculator

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {

    private lateinit var ImcResult:TextView
    private lateinit var ImcInfo:TextView
    private lateinit var backButton:AppCompatButton
    private var IMC= 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        IMC= intent.extras?.getDouble("data")!!
        initDisplay()
        var df=String.format("%.2f",IMC).toDouble()
        ImcResult.text= df.toString()
        saberPeso(df)

    }


    fun initDisplay(){
        ImcResult=findViewById(R.id.IMCResult)
        ImcInfo=findViewById(R.id.IMCInfo)
        backButton=findViewById(R.id.back)

        backButton.setOnClickListener {
            onBackPressed()
        }
    }

    fun saberPeso(imc:Double){
        when(imc){
           in 0.0..18.4 -> {
               ImcInfo.text="Peso inferior al normal"
               ImcInfo.setTextColor(ContextCompat.getColor(this,R.color.red))
           }
           in 18.5..24.8-> {
               ImcInfo.text="Peso normal"
               ImcInfo.setTextColor(ContextCompat.getColor(this,R.color.Tec))
           }
           in 24.9..29.9-> {
               ImcInfo.text="Peso superior al normal"
               ImcInfo.setTextColor(ContextCompat.getColor(this,R.color.red))
           }
           in 30.0..Double.MAX_VALUE->{
               ImcInfo.text="Obesidad"
               ImcInfo.setTextColor(ContextCompat.getColor(this,R.color.red))
           }
        }
    }



}
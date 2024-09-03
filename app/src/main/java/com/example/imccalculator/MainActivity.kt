package com.example.imccalculator

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider

class MainActivity : AppCompatActivity() {

    private lateinit var viewMale:CardView
    private lateinit var viewFemale:CardView

    private var isSelectedMale:Boolean = true
    private var isSelectedFemale:Boolean = false
    private var alturaValue=0
    private var pesoValue=50
    private var edadValue=50

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        initReferences()


    }

    fun initReferences(){
        viewMale=findViewById(R.id.cardView)
        viewFemale=findViewById(R.id.cardView2)
        var sliderH=findViewById<RangeSlider>(R.id.sliderH)
        var Height=findViewById<TextView>(R.id.altura)
        var peso=findViewById<TextView>(R.id.pesoData)
        var addPeso=findViewById<FloatingActionButton>(R.id.addPeso)
        var lessPeso=findViewById<FloatingActionButton>(R.id.lessPeso)
        var edad=findViewById<TextView>(R.id.edadData)
        var addEdad=findViewById<FloatingActionButton>(R.id.addEdad)
        var lessEdad=findViewById<FloatingActionButton>(R.id.lessEdad)
        var calcular=findViewById<AppCompatButton>(R.id.calcular)




        peso.text=pesoValue.toString()
        edad.text=edadValue.toString()

        viewMale.setOnClickListener {
            if (isSelectedMale==true){
                setBgColor()
                return@setOnClickListener
            }
            setSelected()
            setBgColor()
            Log.i("d","Hombre ${isSelectedMale} ${isSelectedFemale}")

        }
        viewFemale.setOnClickListener {
            if (isSelectedFemale==true){
                setBgColor()
                return@setOnClickListener
            }
            setSelected()
            setBgColor()
            Log.i("d","Mujer ${isSelectedMale} ${isSelectedFemale}")
        }
        
        sliderH.addOnChangeListener { slider, value, fromUser ->
            var formateado=java.text.DecimalFormat("#.##")
            alturaValue=formateado.format(value).toInt()
            Height.text="$alturaValue cm"
        }

        addPeso.setOnClickListener {
            var pesoData=AddPeso(true)
            peso.text=pesoData.toString()
        }
        lessPeso.setOnClickListener {
            var pesoData=AddPeso(false)
            peso.text=pesoData.toString()
        }
        addEdad.setOnClickListener {
            var edadData=AddEdad(true)
            edad.text=edadData.toString()
        }
        lessEdad.setOnClickListener {
            var edadData=AddEdad(false)
            edad.text=edadData.toString()
        }

        calcular.setOnClickListener {
            var IMC=calcularIMC()
            intent= Intent(this,ResultActivity::class.java)
            intent.putExtra("data",IMC)
            startActivity(intent)
            Log.i("total","el imc es de ${IMC}")
        }
        
    }

    fun setBgColor(){
        viewMale.setCardBackgroundColor(backgroundColorChange(isSelectedMale))
        viewFemale.setCardBackgroundColor(backgroundColorChange(isSelectedFemale))
    }

    fun backgroundColorChange(selected:Boolean):Int{
        var colorReference=if(selected){
            R.color.isSelected
        }else{
            R.color.Tec
        }

        return ContextCompat.getColor(this,colorReference)
    }

    fun setSelected(){
        isSelectedMale=!isSelectedMale
        isSelectedFemale=!isSelectedFemale
    }

    fun AddPeso(flag:Boolean):Int{
        if (flag){
            pesoValue=pesoValue+1
        }else{
            if (pesoValue==0){

            }else{
                pesoValue=pesoValue-1
            }
        }
        return pesoValue
    }

    fun AddEdad(flag:Boolean):Int{
        if (flag){
            edadValue=edadValue+1
        }else{
            if (edadValue==0){

            }else{
                edadValue=edadValue-1
            }
        }
        return edadValue
    }

    fun calcularIMC():Double{
        var alturaMetros=alturaValue*0.01
        var IMC=pesoValue/(alturaMetros*alturaMetros)
        return IMC
    }



}
package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn=findViewById<Button>(R.id.btnDatePicker)
        btn.setOnClickListener { view->
            clickDatePicker(view)
//            Toast.makeText(this,"Button works!",Toast.LENGTH_LONG).show()
        }
    }
    private fun clickDatePicker(view:View){
        val myCalendar=Calendar.getInstance()
        val year=myCalendar.get(Calendar.YEAR)
        val month=myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd=DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener {
                    view, selectedYear, selectedMonth, selectedDayOfMonth ->
//                Toast.makeText(this,"The year chosen is $selectedYear, month is $selectedMonth and date is $selectedDayOfMonth",Toast.LENGTH_LONG).show()
                val selectedDate="$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                val tvSelectedDate=findViewById<TextView>(R.id.tvSelectedDate)
                tvSelectedDate.setText(selectedDate)
                val sdf=SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
                val theDate=sdf.parse(selectedDate)
                val selectedDateinMins=theDate!!.time/(60000) //for mins

                val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateinMins=currentDate!!.time/(60000)      //(60000*1440) for days
                val differenceinMins=currentDateinMins-selectedDateinMins

                val tvSelectedDateinMins=findViewById<TextView>(R.id.tvSelectedDateinMins)
                tvSelectedDateinMins.setText(differenceinMins.toString())
            },
            year,month,day)

        dpd.datePicker.setMaxDate(Date().time-86400000)      //dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
        dpd.show()
    }
}
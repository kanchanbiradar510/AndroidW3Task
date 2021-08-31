package com.example.myweek3task

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_registration_form.*
import kotlinx.android.synthetic.main.card_list.*
import java.util.*

class RegistrationForm : AppCompatActivity() {

    lateinit var personName: TextView
    lateinit var emailAddress: TextView
    lateinit var maleRadioBtn: RadioButton
    lateinit var femaleRadioBtn: RadioButton
    lateinit var okBtn: Button
    lateinit var checkBox: CheckBox
    lateinit var radioGroup: RadioGroup

    lateinit var dobBtn : Button
    lateinit var timeBtn : Button

    lateinit var dobTxt : TextView
    lateinit var timeTxt: TextView

    lateinit var age: TextView

    var isAllFieldsChecked: Boolean = false

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_form)

        personName = findViewById(R.id.personName)
        emailAddress = findViewById(R.id.emailAddress)
        maleRadioBtn = findViewById(R.id.maleRadioBtn)
        femaleRadioBtn = findViewById(R.id.femaleRadioBtn)
        okBtn = findViewById(R.id.okBtn)
        checkBox = findViewById(R.id.checkBox)
        radioGroup = findViewById(R.id.radioGroup)

        dobBtn = findViewById(R.id.dobBtn)
        dobTxt = findViewById(R.id.dobTxt)

        age = findViewById(R.id.age)

        timeBtn = findViewById(R.id.timeBtn)
        timeTxt = findViewById(R.id.timeTxt)

        val formatdate= android.icu.text.SimpleDateFormat("dd MMMM yyyy", Locale.US)
        val formattime= android.icu.text.SimpleDateFormat("HH:mm aa")


        dobBtn.setOnClickListener(View.OnClickListener {
            val getdate = Calendar.getInstance()
            val datePicker=DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                val selectdate= Calendar.getInstance()
                selectdate.set(Calendar.YEAR,year)
                selectdate.set(Calendar.MONTH,month)
                selectdate.set(Calendar.DAY_OF_MONTH,day)
                dobTxt.text=formatdate.format(selectdate.time)
            },getdate.get(Calendar.YEAR),getdate.get(Calendar.MONTH),getdate.get(Calendar.DAY_OF_MONTH))
            datePicker.show()

        })

        timeBtn.setOnClickListener(View.OnClickListener {
            val gettime= Calendar.getInstance()
            val timepicker= TimePickerDialog(this,TimePickerDialog.OnTimeSetListener { time, i, i2 ->
                val selecttime= Calendar.getInstance()
                selecttime.set(Calendar.HOUR_OF_DAY,i)
                selecttime.set(Calendar.MINUTE,i2)
                timeTxt.text=formattime.format(selecttime.time)
            },gettime.get(Calendar.HOUR_OF_DAY),gettime.get(Calendar.MINUTE),true)
            timepicker.show()
        })

        okBtn.setOnClickListener(View.OnClickListener {

            val radioGroup = radioGroup.checkedRadioButtonId
            val gender = findViewById<RadioButton>(radioGroup)

            if ((PersonNameCheckField() and validateEmail() and FirstCheckAllField() and SecondCheckAllField() and CheckDOB() and CheckTime() and Checkage())) {
                val intent = Intent(this,ProfileActivity::class.java)

                intent.putExtra("Name",personName.text.toString())
                intent.putExtra("Email",emailAddress.text.toString())
                intent.putExtra("Gender",gender.text.toString())
                intent.putExtra("DOB",dobTxt.text.toString())
                intent.putExtra("Time",timeTxt.text.toString())
                intent.putExtra("Age",age.text.toString())
                setResult(RESULT_OK,intent);
                //finish()

                startActivity(intent)

                Toast.makeText(this@RegistrationForm, "Registration Successfull ", Toast.LENGTH_LONG).show()
            }
        })
    }
    fun PersonNameCheckField(): Boolean {
        val personInput = personName.text.toString().trim()
        if (personInput.length == 0) {
            personName.error = "Please Enter Valid Name"
            return false
        } else if (personInput.length < 5) {
            personName.error = "minimum length of name is 5 "
            return false
        } else if (personInput.length > 30) {
            personName.error = "maximum length of name is 30"
            return false
        } else {
            personName.error = null
            return true
        }
    }

    fun validateEmail(): Boolean {
        val emailInput1 = emailAddress.text.toString().trim()
        if (emailInput1.isEmpty()) {
            emailAddress.error = "Please enter valid Email"
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput1).matches()) {
            emailAddress.error = "Please enter valid Email"
            return false
        } else {
            emailAddress.error = null
            return true
        }
    }

    fun FirstCheckAllField(): Boolean{
        if (!maleRadioBtn.isChecked() and !femaleRadioBtn.isChecked()) {
            Toast.makeText(this, "Please Select Gender", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    fun SecondCheckAllField(): Boolean{
        when{(!checkBox.isChecked) ->{
            Toast.makeText(this@RegistrationForm,"Please Accept Term and condition ", Toast.LENGTH_LONG).show()
            return false}
        }
        return true
    }

    fun CheckDOB(): Boolean {
        val name: String = dobTxt.text.toString()
        return if (name.isEmpty()) {
            dobTxt.error = "Please Select Date of Birth"
            false
        } else {
            dobTxt.error = null
            true
        }
    }

    fun CheckTime(): Boolean {
        val name: String = timeTxt.text.toString()
        return if (name.isEmpty()) {
            timeTxt.error = "Please select Time"
            false
        } else {
            timeTxt.error = null
            true
        }
    }

    fun Checkage(): Boolean {
        val ageof = age.text.toString().trim()
        val ageval="/^[1-9]?[0-9]{1}\$|^100\$/"
        return if (ageof.isEmpty()) {
            age.error = "Please enter Age"
            false
        }
        else {
            age.error = null
            true
        }
    }
}


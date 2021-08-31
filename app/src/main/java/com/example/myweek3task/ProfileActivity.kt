package com.example.myweek3task

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    lateinit var txName: TextView
    lateinit var txEmail: TextView
    lateinit var txGender: TextView
    lateinit var homeBtn: Button
    lateinit var shareBtn: Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        txName = findViewById(R.id.txName)
        txEmail = findViewById(R.id.txEmail)
        txGender = findViewById(R.id.txGender)
        homeBtn = findViewById(R.id.homeBtn)

        val bundle: Bundle? = intent.extras

        val name = bundle!!.getString("Name")
        val email = bundle!!.getString("Email")
        val gender = bundle!!.getString("Gender")
        val dob= bundle!!.getString("DOB")
        val time= bundle!!.getString("Time")
        val age= bundle!!.getString("Age")

        txName.text = "Name: $name"
        txEmail.text = "Email: $email"
        txGender.text = "Gender: $gender"
        txDob.text = "DOB: $dob"
        txTime.text = "Time: $time"
        txAge.text = "Age: $age"

        homeBtn.setOnClickListener(View.OnClickListener {
            val homeIntent= Intent(this,MainActivity::class.java)
            startActivity(homeIntent)
            Toast.makeText(this@ProfileActivity, "Hame Page", Toast.LENGTH_LONG).show()
        })


    }

}
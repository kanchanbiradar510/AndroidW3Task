package com.example.myweek3task

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_registration_form.*

class MainActivity : AppCompatActivity() {
    var list: List<CardList>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list = ArrayList<CardList>()

        val fab: View = findViewById(R.id.fab)

        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

            if (result.resultCode == Activity.RESULT_OK) {

                val data = result.data
                val name = data?.getStringExtra("Name").toString()
                val email = data?.getStringExtra("Email").toString()
                val gender = data?.getStringExtra("Gender").toString()

                (list as ArrayList<CardList>).add(CardList(email,name,gender))

                val recyclerView = findViewById<View>(R.id.recycleView) as RecyclerView
                val adapter = AdapterRcy(list as ArrayList<CardList>)
                recyclerView.setHasFixedSize(true)
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = adapter

                val insertIndex = 2
                adapter.notifyItemInserted(insertIndex);
            }
        }
        fab.setOnClickListener { view ->
            val intent =Intent(this,RegistrationForm::class.java)
//            startActivity(intent)
            resultLauncher.launch(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.logOut) {
            val builder = android.app.AlertDialog.Builder(this@MainActivity)
            builder.setMessage("Do you want to LogOut?")
            builder.setCancelable(false)
            builder.setPositiveButton("No") { dialog, which -> dialog.cancel() }
            builder.setNegativeButton("yes") {dialog, which -> finishAffinity()}
            val alertDialog = builder.create()
            alertDialog.show()
        }
        return true
    }

}

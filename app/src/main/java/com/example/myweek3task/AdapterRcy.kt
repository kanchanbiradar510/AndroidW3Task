package com.example.myweek3task

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AlertDialog


class AdapterRcy(private val listdata: MutableList<CardList>?)  : RecyclerView.Adapter<AdapterRcy.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //var personimageView: ImageView
        var personmailid: TextView
       // var personage: TextView
        var personname: TextView
        var persongender: TextView
        //var time: TextView
        var delete: ImageButton
        var relativeLayout: LinearLayout

        init {
            delete = itemView.findViewById<View>(R.id.DeleteButton) as ImageButton
            //personimageView = itemView.findViewById<View>(R.id.personImageView) as ImageView
            personname = itemView.findViewById<View>(R.id.personName) as TextView
            personmailid = itemView.findViewById<View>(R.id.emailAddress) as TextView
            //personage = itemView.findViewById<View>(R.id.age) as TextView
            persongender = itemView.findViewById<View>(R.id.personGender) as TextView

           // time = itemView.findViewById<View>(R.id.time) as TextView
            relativeLayout = itemView.findViewById<View>(R.id.linearLayout) as LinearLayout
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.card_list,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listdata!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val myListData = listdata!![position]
        holder.personname.text = listdata[position].description
        //  holder.personimageView.setImageResource(listdata[position].img)
        holder.personmailid.text = listdata[position].email
        //holder.personage.text =listdata[position].age
        holder.persongender.text = listdata[position].radioGroup

        holder.delete.setOnClickListener {view ->
            val builder1 = AlertDialog.Builder(view.context)
            builder1.setMessage("Are you sure want to delete?")
            builder1.setCancelable(true)
            builder1.setPositiveButton("Yes"){dialogInterface, which ->
                listdata.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position,listdata.size)
            }
            //performing cancel action
            builder1.setNeutralButton("Cancel"){dialogInterface , which ->

            }
            //performing negative action

            val alert11 = builder1.create()
            alert11.show()
            true

        }

        holder.relativeLayout.setOnClickListener { view ->
            val intent = Intent(view.context, ProfileActivity::class.java)
            intent.putExtra("Name",myListData.description);
           // intent.putExtra("MyImg",myListData.img);
            intent.putExtra("Email",myListData.email);
           // intent.putExtra("Age",myListData.age)
            intent.putExtra("Gender",myListData.radioGroup);
            intent.putExtra("pos",position)
            view.context.startActivity(intent)

        }
    }
}

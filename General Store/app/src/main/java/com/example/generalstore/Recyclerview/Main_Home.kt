package com.example.generalstore.Recyclerview

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.generalstore.*

class Main_Home(var list: Array<String>,var context: Context): RecyclerView.Adapter<Main_Home.HomeHolder>() {

    class HomeHolder(itemView: View,context: Context) : RecyclerView.ViewHolder(itemView),OnClickListener {

        var heading=itemView.findViewById<TextView>(R.id.tv_handing)
        var layout=itemView.findViewById<ConstraintLayout>(R.id.contlayout)
        var context=itemView.context
        override fun onClick(v: View?) {
            var intent:Intent
            when(adapterPosition){
                1 -> {
                     intent = Intent(context, UserReport::class.java)
                    context.startActivity(intent)
                }
                2 -> {
                    intent = Intent(context, Customer_Product_Order::class.java)
                    context.startActivity(intent)
                }
                3 -> {
                    intent = Intent(context, Payment::class.java)
                    context.startActivity(intent)
                }
                4 -> {
                    intent = Intent(context, Payment_Report::class.java)
                    context.startActivity(intent)
                }
                5 -> {
                    intent = Intent(context, Product_Sale_Report::class.java)
                    context.startActivity(intent)
                }

                6 -> {
                    intent = Intent(context, AddNewUser::class.java)
                    context.startActivity(intent)
                }
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.main_rowdata,parent,false)
        return HomeHolder(view,context)

    }

    override fun getItemCount(): Int {
      return  list.size

    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        var text=list[position]
       holder?.heading?.text=text





    }
}
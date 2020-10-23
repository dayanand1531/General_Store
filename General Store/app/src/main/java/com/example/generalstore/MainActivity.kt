package com.example.generalstore

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.generalstore.Recyclerview.Main_Home
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(),AdapterView.OnItemClickListener {
    var list: Array<String> = arrayOf(
        "Select",
        "User Report",
        "Customer Product Order",
        "Payment",
        "Payment Report",
        "Product Sale Report",
        "Add User"


    )

   var menu:Array<String> = arrayOf("Add Customer","User Report","Customer sale Report",
       "Payment Report","Product sale Report")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var context = this

        signout.setOnClickListener(View.OnClickListener {
            finish()
        })

        val adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,menu)
        lv_menu.adapter=adapter

        var admin_name=intent.getStringExtra("admin")
        tv_Admin_name.text=admin_name

        btnshowuser.setOnClickListener() {
            var intent = Intent(context, UserReport::class.java)
            startActivity(intent)
        }

        lv_menu.setOnItemClickListener(this)



        addnewUser.setOnClickListener() {
            var intent = Intent(context, AddNewUser::class.java)
            startActivity(intent)
        }


        var arrayAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_activity.adapter = arrayAdapter
        spinner_activity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    6 -> {
                        intent = Intent(context, AddNewUser::class.java)
                        startActivity(intent)
                    }
                    1 -> {
                        intent = Intent(context, UserReport::class.java)
                        startActivity(intent)
                    }
                    2 -> {
                        intent = Intent(context, Customer_Product_Order::class.java)
                        startActivity(intent)
                    }
                    3 -> {
                        intent = Intent(context, Payment::class.java)
                        startActivity(intent)
                    }
                    4 -> {
                        intent = Intent(context, Payment_Report::class.java)
                        startActivity(intent)
                    }
                    5 -> {
                        intent = Intent(context, Product_Sale_Report::class.java)
                        startActivity(intent)
                    }

                }
            }
        }

      /*rv_main.layoutManager=LinearLayoutManager(this)
        var homelist:ArrayList<String> = ArrayList<String>()
       // homelist.add(home.toString())
        var adapter=Main_Home(home,this)
        rv_main.adapter=adapter*/
    }

    fun menu(){

    }


    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var intent:Intent
       when(position){
           0 ->{
               intent= Intent(this,AddNewUser::class.java)
               startActivity(intent)

           }
           1 -> {
               intent = Intent(this, UserReport::class.java)
               startActivity(intent)
           }
       2 ->{
           intent= Intent(this,Customer_Sale_Report::class.java)
           startActivity(intent)

       }3 ->{
           intent= Intent(this,Payment_Report::class.java)
           startActivity(intent)

       }4 ->{
           intent= Intent(this,Product_Sale_Report::class.java)
           startActivity(intent)

       }
       }
    }

    override fun onBackPressed() {
      //  super.onBackPressed()
        return
    }


}

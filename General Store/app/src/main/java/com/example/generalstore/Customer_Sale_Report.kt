package com.example.generalstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.generalstore.Datamodal.CustomerDataModal
import com.example.generalstore.Recyclerview.Sale_Report
import com.example.generalstore.SqliteDatabase.MyCustomerDatahelper

class Customer_Sale_Report : AppCompatActivity() {
    var context = this
    var db = MyCustomerDatahelper(context)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer__sale__report)
        customerDetail()
    }

    fun customerDetail(){

        var customerdata = db.readAllData()

        val recyclerView = findViewById<RecyclerView>(R.id.rv_product_list)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val user = ArrayList<CustomerDataModal>()
        // var s:String=""
        for (i in 0..(customerdata.size - 1)) {
//            s= customerdata.get(i).toString()

            user.add(
                CustomerDataModal(
                    customerdata.get(i).id,
                    customerdata.get(i).name,
                    customerdata.get(i).email,
                    customerdata.get(i).fathername,
                    customerdata.get(i).mobileno

                )
            )
        }

        val adapter = Sale_Report(user,this)

        recyclerView.adapter = adapter

    }
    }



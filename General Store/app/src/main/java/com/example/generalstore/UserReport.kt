package com.example.generalstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.generalstore.Datamodal.CustomerDataModal
import com.example.generalstore.Recyclerview.CustomerReport
import com.example.generalstore.SqliteDatabase.MyCustomerDatahelper

class UserReport : AppCompatActivity() {
    var context = this
    var db = MyCustomerDatahelper(context)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_report)
        customerOrder()



    }

    fun customerOrder(){
        var customerdata = db.readAllData()

        val recyclerView = findViewById<RecyclerView>(R.id.rv_customerreport)
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

        val adapter = CustomerReport(user,this)

        recyclerView.adapter = adapter

    }

    fun back(view: View) {
        intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}

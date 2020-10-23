package com.example.generalstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.generalstore.Datamodal.PaymentModal
import com.example.generalstore.Recyclerview.PaymentReport
import com.example.generalstore.SqliteDatabase.MyCustomerDatahelper
import com.example.generalstore.SqliteDatabase.sublotal
import kotlinx.android.synthetic.main.activity_payment__report.*

class Payment_Report : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment__report)
        var context = this
        var db = MyCustomerDatahelper(context)
        var getdata = db.showPayment()

        var recyclerView = findViewById<RecyclerView>(R.id.rv_payment_report)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        var list = ArrayList<PaymentModal>()

        for (i in 0..(getdata.size - 1)) {
            list.add(
                PaymentModal(
                    getdata.get(i).sublotal,
                    getdata.get(i).discount,
                    getdata.get(i).charges,
                    getdata.get(i).payamount,
                    getdata.get(i).grandtoatal,
                    getdata.get(i).remain,
                    getdata.get(i).paymenttypes,
                    getdata.get(i).customer_id,
                    getdata.get(i).privious_payment
                )
            )
        }
        var adapter = PaymentReport(list)
        recyclerView.adapter = adapter
    }
}
package com.example.generalstore

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.generalstore.Datamodal.PaymentModal
import com.example.generalstore.Datamodal.ProductListModal
import com.example.generalstore.Recyclerview.Product_list_customer_wise
import com.example.generalstore.SqliteDatabase.MyCustomerDatahelper
import kotlinx.android.synthetic.main.activity_customer_buy_repoat.*

class Customer_buy_repoat : AppCompatActivity() {
    lateinit var name:String
    lateinit var fathername:String
    lateinit var email:String
    lateinit var mobileno:String
    var customerid:Int=0
    var context=this
    var db= MyCustomerDatahelper(context)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_buy_repoat)
        getCustomerDetail()
        getProductList()

    }

    fun getCustomerDetail(){
        name=intent.getStringExtra("name")
        email=intent.getStringExtra("email")
        mobileno=intent.getStringExtra("mobile_no")
        fathername=intent.getStringExtra("fathername")
        customerid=intent.getStringExtra("id").toInt()
        tv_customer_name.text=name
        tv_email.text=email
        tv_father_name.text=fathername
        tv_mobile_no.text=mobileno

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getProductList(){

        var getdata=db.getPaymentDate(customerid)
        var list=ArrayList<PaymentModal>()
        rv_product_list.layoutManager= LinearLayoutManager(this)
        for(i in 0..(getdata.size-1)){
            list.add(PaymentModal(getdata.get(i).date,getdata.get(i).paymentid,getdata.get(i).sublotal,getdata.get(i).discount,getdata.get(i).charges,getdata.get(i).payamount,
            getdata.get(i).grandtoatal,getdata.get(i).remain,getdata.get(i).paymenttypes,getdata.get(i).customer_id,getdata.get(i).privious_payment))
        }
        var adapter= Product_list_customer_wise(list)
        rv_product_list.adapter=adapter



    }
}
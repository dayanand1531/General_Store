package com.example.generalstore.Recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.generalstore.Datamodal.ProductListModal
import com.example.generalstore.R

class SubProductList(var list:ArrayList<ProductListModal>):RecyclerView.Adapter<SubProductList.Holder>() {
    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productname=itemView.findViewById<TextView>(R.id.tv_productname)
        var product_quantity=itemView.findViewById<TextView>(R.id.tv_productQuality)
        var productPrice=itemView.findViewById<TextView>(R.id.tv_product_price)
        var subtotal=itemView.findViewById<TextView>(R.id.tv_totalPrice)
        var substotal=itemView.findViewById<TextView>(R.id.tv_subtotal_dailog)
        var previousbalance=itemView.findViewById<TextView>(R.id.tv_privous_balance)
        var distcount=itemView.findViewById<TextView>(R.id.tv_dailog_discount)
        var charges=itemView.findViewById<TextView>(R.id.tv_charges_dialog)
        var payamunt=itemView.findViewById<TextView>(R.id.tv_payamount_dailog)
        var paymenttypes=itemView.findViewById<TextView>(R.id.tv_paymenttype_dailog)
        var grantotal=itemView.findViewById<TextView>(R.id.tv_grandtotal_dialog)
        var remain=itemView.findViewById<TextView>(R.id.tv_remain_dialog)
      //  var customerid=itemView.findViewById<TextView>(R.id.t)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        var view=LayoutInflater.from(parent.context).inflate(com.example.generalstore.R.layout.subitem_product_report,null)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
       var data:ProductListModal=list[position]
        holder?.productname?.text=data.productname
        holder?.product_quantity?.text=data.quality
        holder?.productPrice?.text=data.price
        holder?.subtotal?.text=data.totalprice
    }

    override fun getItemCount(): Int {
       return list.size
    }

  /*  fun getPaymentdetail(paymentid:Int,context: Context){
        var context=this
        var db = MyCustomerDatahelper(context)
        var getdata = db.showPaymentOrderwiae(paymentid)


        if (getdata!=null) {

            substotal.text=  getdata.sublotal,
            getdata.discount,
            getdata.charges,
            getdata.payamount,
            getdata.grandtoatal,
            getdata.remain,
            getdata.paymenttypes,
            getdata.customer_id,
            getdata.privious_payment

        }



    }*/
}
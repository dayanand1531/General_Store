package com.example.generalstore.Recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.generalstore.Datamodal.PaymentModal
import com.example.generalstore.R

class PaymentReport(var list:ArrayList<PaymentModal>): RecyclerView.Adapter<PaymentReport.PaymentHolder>() {
    class PaymentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var subtotal=itemView.findViewById<TextView>(R.id.tv_subtoal_rp)
        var discount=itemView.findViewById<TextView>(R.id.tv_discount_rp)
        var charges=itemView.findViewById<TextView>(R.id.tv_charges_rp)
        var payamount=itemView.findViewById<TextView>(R.id.tv_payamount_rp)
        var grandtotal=itemView.findViewById<TextView>(R.id.tv_grandtotal_rp)
        var remain=itemView.findViewById<TextView>(R.id.tv_remain_rp)
        var paytypes=itemView.findViewById<TextView>(R.id.tv_paymenttypes_rp)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.paymenr_report_rowlayout,parent,false)
        return PaymentHolder(view)

    }

    override fun getItemCount(): Int {
        return list.size

    }

    override fun onBindViewHolder(holder: PaymentHolder, position: Int) {
        var data:PaymentModal=list[position]
        holder?.subtotal?.text=data.sublotal
        holder?.discount?.text=data.discount
        holder?.charges?.text=data.charges
        holder?.payamount?.text=data.payamount
        holder?.grandtotal?.text=data.grandtoatal
        holder?.remain?.text=data.remain
        holder?.paytypes?.text=data.paymenttypes

    }
}
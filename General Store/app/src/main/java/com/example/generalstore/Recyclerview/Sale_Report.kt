package com.example.generalstore.Recyclerview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.generalstore.Customer_Product_Order
import com.example.generalstore.Customer_buy_repoat
import com.example.generalstore.Datamodal.CustomerDataModal
import com.example.generalstore.R

class Sale_Report(var list: MutableList<CustomerDataModal>,var context: Context) : RecyclerView.Adapter<Sale_Report.Report>() {
    class Report(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById<TextView>(R.id.tvname)
        var fathername = itemView.findViewById<TextView>(R.id.tvfathername)
        var mobileno = itemView.findViewById<TextView>(R.id.tvmobileno)
        var email = itemView.findViewById<TextView>(R.id.tvemailid)
        var container=itemView.findViewById<ConstraintLayout>(R.id.container)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Sale_Report.Report {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.customer_report_data,null)
        return Report(view)

    }

    override fun onBindViewHolder(holder: Sale_Report.Report, position: Int) {
        var data:CustomerDataModal=list[position]
        holder?.name?.text = data.name
        holder?.fathername?.text = data.fathername
        holder?.mobileno?.text = data.mobileno
        holder?.email?.text = data.email
        // holder?.id?.text=customerdata.id.toString()

        holder?.container.setOnClickListener(View.OnClickListener {
            //  var context:Context
            var intent= Intent(context, Customer_buy_repoat::class.java)
            intent.putExtra("name",data.name)
            intent.putExtra("id",data.id.toString())
            intent.putExtra("fathername",data.fathername)
            intent.putExtra("email",data.email)
            intent.putExtra("mobile_no",data.mobileno)
            context.startActivity(intent)
        })

    }

    override fun getItemCount(): Int {
        return list.size

    }
}
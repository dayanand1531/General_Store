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
import com.example.generalstore.Datamodal.CustomerDataModal
import com.example.generalstore.R

class CustomerReport(var userlist: ArrayList<CustomerDataModal>, var context: Context) :
    RecyclerView.Adapter<CustomerReport.Myholder>() {

    class Myholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById<TextView>(R.id.tvname)
        var fathername = itemView.findViewById<TextView>(R.id.tvfathername)
        var mobileno = itemView.findViewById<TextView>(R.id.tvmobileno)
        var email = itemView.findViewById<TextView>(R.id.tvemailid)
        var container=itemView.findViewById<ConstraintLayout>(R.id.container)
       // var id=itemView.findViewById<TextView>(R.id.id)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerReport.Myholder {
        var inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.customer_report_data, parent, false)
        return Myholder(inflater)

    }

    override fun getItemCount(): Int {
        return userlist.size

    }

    override fun onBindViewHolder(holder: CustomerReport.Myholder, position: Int) {

        var customerdata: CustomerDataModal = userlist[position]
        holder?.name?.text = customerdata.name
        holder?.fathername?.text = customerdata.fathername
        holder?.mobileno?.text = customerdata.mobileno
        holder?.email?.text = customerdata.email
       // holder?.id?.text=customerdata.id.toString()

        holder?.container.setOnClickListener(View.OnClickListener {
          //  var context:Context
            var intent=Intent(context,Customer_Product_Order::class.java)
            intent.putExtra("name",customerdata.name)
            intent.putExtra("id",customerdata.id.toString())
            context.startActivity(intent)
        })

    }


}
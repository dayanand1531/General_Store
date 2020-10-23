package com.example.generalstore.Recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.generalstore.Datamodal.ProductListModal
import com.example.generalstore.R

class ProductSaleReport(var list:ArrayList<ProductListModal>):
    RecyclerView.Adapter<ProductSaleReport
    .HolderProductSale>() {
    class HolderProductSale(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var product_name=itemView.findViewById<TextView>(R.id.tv_product_name_rp)
        var price=itemView.findViewById<TextView>(R.id.tv_product_price_rp)
        var quantity=itemView.findViewById<TextView>(R.id.tv_product_quantity_rp)
        var sum=itemView.findViewById<TextView>(R.id.tv_product_sum_rp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderProductSale {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.product_sale_report_rowlayout,parent,false)
        return HolderProductSale(view)
    }

    override fun getItemCount(): Int {
      return list.size
    }

    override fun onBindViewHolder(holder: HolderProductSale, position: Int) {
        var productlist:ProductListModal=list[position]
        holder?.product_name?.text=productlist.productname
        holder?.price?.text=productlist.price
        holder?.quantity?.text=productlist.quality
        holder?.sum?.text=productlist.totalprice
    }
}
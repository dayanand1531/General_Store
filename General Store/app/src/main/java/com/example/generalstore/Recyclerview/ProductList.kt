package com.example.generalstore.Recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.generalstore.Customer_Product_Order
import com.example.generalstore.Datamodal.ProductListModal
import com.example.generalstore.R
import kotlinx.android.synthetic.main.activity_customer__product__order.*

class ProductList(var productitem:ArrayList<ProductListModal>) :
    RecyclerView.Adapter<ProductList.ProductData>() {

    class ProductData(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productname = itemView.findViewById<TextView>(R.id.tv_productname)
        var productprice = itemView.findViewById<TextView>(R.id.tv_product_price)
        var productquality = itemView.findViewById<TextView>(R.id.tv_productQuality)
        var producttotal = itemView.findViewById<TextView>(R.id.tv_totalPrice)
        var layoutitem = itemView.findViewById<LinearLayout>(R.id.layoutitem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductList.ProductData {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_product_item, parent, false)
        return ProductData(view)
    }

    override fun getItemCount(): Int {
        return productitem.size
    }

    override fun onBindViewHolder(holder: ProductList.ProductData, position: Int) {
        var product: ProductListModal = productitem[position]
        holder?.productname?.setText(product.productname)
        holder?.productprice?.setText(product.price)
        holder?.productquality?.setText(product.quality)
        holder?.producttotal?.setText(product.totalprice)
        holder?.layoutitem.setOnLongClickListener(View.OnLongClickListener {
            removeitem(position)
            return@OnLongClickListener true

        })




    }

    fun removeitem(position: Int){
        productitem.removeAt(position)


        notifyItemRemoved(position)
        notifyItemRangeChanged(position,productitem.size)

    }

   /* fun deleteitemAlertbox() {
        // val context: ProductList =this
        var builder = AlertDialog.Builder(context)
        builder.setTitle("Delete Item")
        builder.setMessage("Are you sure to delete item !")
        builder.setIcon(R.drawable.ic_delete_black_24dp)
        builder.setNegativeButton("No") { dialog, which ->
            dialog.cancel()

        }
        builder.setPositiveButton("Yes") { dialog, which ->

        }
        var dialog = builder.create()
        dialog.setCancelable(false)
        dialog.show()


    }*/

    /*fun deleteItem(position: ProductListModal) {

        productitem.removeAt(position)
        notifyItemRemoved(position)
    }*/
}
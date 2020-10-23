package com.example.generalstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.generalstore.Datamodal.ProductListModal
import com.example.generalstore.Recyclerview.ProductSaleReport
import com.example.generalstore.SqliteDatabase.MyCustomerDatahelper
import kotlinx.android.synthetic.main.activity_product__sale__report.*

class Product_Sale_Report : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product__sale__report)
        var context=this
        var db=MyCustomerDatahelper(context)
        var getdata=db.readproductsale()
        var list=ArrayList<ProductListModal>()
        rv_product_sale_rp.layoutManager=LinearLayoutManager(this)
        for (i in 0..(getdata.size-1)){
            list.add(ProductListModal(getdata.get(i).productname,getdata.get(i).price,getdata.get(i).quality,getdata.get(i).totalprice))
        }
        var adapter=ProductSaleReport(list)
        rv_product_sale_rp.adapter=adapter
    }
}
package com.example.generalstore

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.generalstore.Datamodal.CustomerDataModal
import com.example.generalstore.Datamodal.ProductListModal
import com.example.generalstore.Recyclerview.ProductList
import com.example.generalstore.SqliteDatabase.MyCustomerDatahelper
import kotlinx.android.synthetic.main.activity_customer__product__order.*
import kotlinx.android.synthetic.main.search_customer.view.*
import kotlin.collections.ArrayList

class Customer_Product_Order : AppCompatActivity() {

    var context = this
    var db = MyCustomerDatahelper(context)
    var productList = ArrayList<ProductListModal>()
    lateinit var list:ArrayList<CustomerDataModal>
    lateinit var adapter:ArrayAdapter<CustomerDataModal>
    lateinit var customer_name:String
    lateinit var id:String

    var t= arrayOf(0,0)
    @RequiresApi(Build.VERSION_CODES.N)
   // var date= SimpleDateFormat("MM-DD-YYYY  HH:MM:SS", Locale.getDefault()).format(Date())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer__product__order)
        var grandtotal=findViewById<TextView>(R.id.tvgrandtotal)
        registerForContextMenu(btnrefresh)

        var id=intent.getStringExtra("id")
        var name=intent.getStringExtra("name")
        tv_customername.text=name+" Id "+id


        rv_productlist.layoutManager = LinearLayoutManager(this)
        var productadapter = ProductList(productList)
        rv_productlist.adapter = productadapter

        btn_add_item.setOnClickListener(View.OnClickListener {
            if(!validationProductname() && !validationPrice() && !validationquantity()){
                return@OnClickListener
            }else{
                productList.add(
                    ProductListModal(
                        etProduct_name.text.toString(),
                        et_Price.text.toString(),
                        et_Quality.text.toString(),
                        tv_subtotal_dailog.text.toString(),
                        id.toInt()
                        //date


                    )


                )
                var gtotal: Int = 0
                for (i in 0 until productList.size) {
                    gtotal +=productList[i].totalprice.toInt()

                }
                grandtotal.text = gtotal.toString()

                productadapter.notifyDataSetChanged()
                etProduct_name.setText("")
                et_Price.setText("")
                et_Quality.setText("")
                tv_subtotal_dailog.setText("")
            }


        })
        ic_refresh.setOnClickListener(View.OnClickListener {
            var gtotal: Int = 0
            for (i in 0 until productList.size) {
                gtotal =gtotal+productList[i].totalprice.toInt()
            }
            tvgrandtotal.text = gtotal.toString()

            productadapter.notifyDataSetChanged()
        })

        btn_process.setOnClickListener(View.OnClickListener {
            saveProductlist()
            var gotal:String= grandtotal.text as String
            var intent=Intent(context,Payment::class.java)
            intent.putExtra("grandtotal",gotal)
            intent.putExtra("customer_id",id)
         //   intent.putExtra("list",productList)
            startActivity(intent)

        })

        etProduct_name.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               // validationProductname()
            }
        })

        et_Price.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
               /* var a=et_Price.text.toString().toInt()
                var b=et_Quality.text.toString().toInt()
                if(et_Price.text==null && et_Quality.text==null){
                    et_Quality.setText(0)
                    et_Price.setText(0)
                    t.set(0,a)
                    t.set(1,b)
                    tv_total_dailog.text= t.sum().toString()
                }
                else
                {
                    t.set(0,a)
                    t.set(1,b)
                    tv_total_dailog.text= t.sum().toString()
                }
*/

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               // validationPrice()
            }
        })


        et_Quality.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
               /* var a=et_Price.text.toString().toInt()
                var b=et_Quality.text.toString().toInt()
                if(a==null && b==null){
                    et_Quality.setText(0)
                    et_Price.setText(0)
                    t.set(0,a)
                    t.set(1,b)
                    tv_total_dailog.text= t.sum().toString()
                }
                else
                {
                    t.set(0,a)
                    t.set(1,b)
                    tv_total_dailog.text= t.sum().toString()
                }*/

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!validationquantity()) {
                   return
                } else {
                    tv_subtotal_dailog.text = (et_Price.text.toString().toInt() * et_Quality.text.toString()
                        .toInt()).toString()
                }
            }
        })

        //iv_menu.setOnClickListener(View.OnClickListener {  })

    }



    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.order_menu,menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.search ->{
         searchDialog()
            }
        }
        return super.onContextItemSelected(item)
    }

  fun saveProductlist(){
      var pname:String
      var pprice:String
      var pquantity:String
      var ptotal:String
      var p_customerID:Int
    //  var date:String

      for (i in 0 until productList.size){
          pname=productList[i].productname
           pprice=productList[i].price
           pquantity=productList[i].quality
           ptotal=productList[i].totalprice
           p_customerID=productList[i].customer_id
        //  date=productList[i].date
          var orderlist=ProductListModal(pname,pprice,pquantity,ptotal,p_customerID)
          db.insertProductitemsale(orderlist)
      }


  }

    fun validationProductname():Boolean{
        var productname=etProduct_name.text.toString().trim()
        if (productname.isEmpty()){
            Toast.makeText(context,"*Please enter product name.",Toast.LENGTH_LONG).show()
            return false
        }
        else if (productname.length<3){
            Toast.makeText(context,"*Product name length more then two char.",Toast.LENGTH_LONG).show()
            return false
        }
        else{
            return true

        }

    }

    fun validationPrice():Boolean{
        var price=et_Price.text.toString().trim()
        if (price.isEmpty()){
            Toast.makeText(context,"*Please enter product price.",Toast.LENGTH_LONG).show()
            return false
        }
        else{
            return true

        }

    }

    fun validationquantity():Boolean{
        var quantity=et_Quality.text.toString().trim()
        if (quantity.isEmpty()){
            Toast.makeText(context,"*Please enter product quantity.",Toast.LENGTH_LONG).show()
            return false
        }
        else{
            return true

        }

    }

    fun getUserDetail(mobileno:String){
        var context=this
        var db=MyCustomerDatahelper(context)
        var data=db.searchCustomerByMobileno(mobileno)
         list=ArrayList<CustomerDataModal>()


        if (data!=null){
                   customer_name=data.id.toString()
                   id=data.name
        }
       else {
            Toast.makeText(this, "Not found", Toast.LENGTH_LONG).show()
        }
    }

    fun searchDialog(){
        var layout=LayoutInflater.from(context).inflate(R.layout.search_customer,null)
        var dialog:AlertDialog.Builder=AlertDialog.Builder(this)
            .setView(layout)
        dialog.show()
         layout.btn_search.setOnClickListener(View.OnClickListener {
             getUserDetail(layout.et_search_mobileno.text.toString())

         })

    }
}

package com.example.generalstore.SqliteDatabase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.generalstore.Datamodal.Admin
import com.example.generalstore.Datamodal.CustomerDataModal
import com.example.generalstore.Datamodal.PaymentModal
import com.example.generalstore.Datamodal.ProductListModal
import kotlinx.android.synthetic.main.dialoggrandtotal.view.*
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

var version: Int = 1
var databasename: String = "General_Stroe"

var tablename: String = "customer_information" //Customer data Detail Table
var id: String = "ID"
var Name: String = "Name"
var email: String = "Email"
var fathername: String = "FatherName"
var mobileno: String = "Mobileno"
var customer_date="Customer_Date"

var productorder: String = "Product_sale" //Product Order sale List Table
var productname: String = "Productname"
var productprice: String = "productprice"
var productquantity: String = "productquantity"
var producttotal: String = "producttotal"
var productid: String = "Product_ID"
var customer_id = "Customer_ID"
var productbuydatetime="Product_buy_date"
var payment_id="Payment_ID"


var paymentid: String = "Payment_ID" //Payment Table Data Column
var payment: String = "Payment"
var sublotal: String = "Subtotal"
var discount: String = "Discount"
var charges: String = "Charges"
var payamount: String = "Payamount"
var grandtoatal: String = "GrandTotal"
var remain: String = "Remain"
var paymenttypes: String = "PaymentTypes"
var payment_date="Payment_Date"
var paycustome_id="Customer_ID"
var priviewspayment="PreviousRemain"

var admin_table: String = "Admin_table"  //Admin table for login
var adminid: String = "Admin_ID"
var Admin_name = "Admin_name"
var Password = "password"

class MyCustomerDatahelper(var context: Context) :
    SQLiteOpenHelper(context, databasename, null, version) {

    override fun onCreate(db: SQLiteDatabase?) {

        var Create_Table =
            "Create Table " + tablename + "(" + id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Name + " text," + email + " text," +
                    fathername + " text," + mobileno + " text, " + customer_date+ " default CURRENT_DATE)"

        var productsale =
            "Create table " + productorder + "(" + productid + " INTEGER PRIMARY KEY AUTOINCREMENT, " + productname + " text," + productprice + " text, " +
                    productquantity + " text, " + producttotal + " text, " + customer_id + " INTEGER, "+ payment_id+ " INTEGER, " + productbuydatetime+ " default current_date , CONSTRAINT fk_" + tablename + " FOREIGN KEY (" + customer_id + ") REFERENCES " + tablename + "(" + id + "))"

        var paybill =
            "Create table " + payment + "(" + paymentid + " INTEGER PRIMARY KEY AUTOINCREMENT, " + sublotal + " text," + discount + " text, " +
                    charges + " text, " + payamount + " text, " + grandtoatal + " text, " + remain + " text, "+ priviewspayment+" text, " + paymenttypes + " text," + paycustome_id+ " INTEGER, " + payment_date+ " default CURRENT_DATE, CONSTRAINT fk_"+ tablename + " FOREIGN KEY (" + paycustome_id + ") REFERENCES " + tablename + "(" + id + "))"

        var admin =
            "Create table " + admin_table + "(" + adminid + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Admin_name + " text," + Password + " text)"

        db?.execSQL(Create_Table)  //table for customer detail
        db?.execSQL(productsale)   //table for product sale
        db?.execSQL(paybill)       //paymentbill
        db?.execSQL(admin)           //admin table

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        var Drop_table = " DROP TABLE IF EXISTS " + tablename
        var Drop_tableproductsale = " DROP TABLE IF EXISTS " + productorder
        var Drop_Tablepaybill = " DROP TABLE IF EXISTS " + payment
        var Drop_admin_table = "DROP TABLE IF EXISTS " + admin_table


        db?.execSQL(Drop_table)
        db?.execSQL(Drop_tableproductsale)
        db?.execSQL(Drop_Tablepaybill)
        db?.execSQL(Drop_admin_table)
        onCreate(db)

    }

    fun insertData(customerDataModal: CustomerDataModal) {
        var db = this.writableDatabase
        var cv = ContentValues()
        cv.put(Name, customerDataModal.name)
        cv.put(email, customerDataModal.email)
        cv.put(fathername, customerDataModal.fathername)
        cv.put(mobileno, customerDataModal.mobileno)
        var result = db.insert(tablename, null, cv)
        if (result == -1.toLong()) {
            Toast.makeText(context, "Fail to insert", Toast.LENGTH_LONG).show()

        } else {
            Toast.makeText(context, "insert suceessfully", Toast.LENGTH_LONG).show()

        }

    }

    fun insertProductitemsale(productListModal: ProductListModal) {
        var db = this.writableDatabase
        var cv = ContentValues()
        cv.put(productname, productListModal.productname)
        cv.put(productprice, productListModal.price)
        cv.put(productquantity, productListModal.quality)
        cv.put(producttotal, productListModal.totalprice)
        cv.put(customer_id,productListModal.customer_id)
      //  cv.put(productbuydatetime, productListModal.date)
        var result = db.insert(productorder, null, cv)
        if (result == -1.toLong()) {
            Toast.makeText(context, "Fail to insert", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Insert sucessfully", Toast.LENGTH_LONG).show()

        }
    }

    fun insertPayment(paymentModal: PaymentModal) {
        var db = this.writableDatabase
        var cv = ContentValues()
        cv.put(sublotal, paymentModal.sublotal)
        cv.put(discount, paymentModal.discount)
        cv.put(charges, paymentModal.charges)
        cv.put(payamount, paymentModal.payamount)
        cv.put(grandtoatal, paymentModal.grandtoatal)
        cv.put(remain, paymentModal.remain)
        cv.put(paymenttypes, paymentModal.paymenttypes)
        cv.put(paycustome_id,paymentModal.customer_id)
        cv.put(priviewspayment,paymentModal.privious_payment)
        var result = db.insert(payment, null, cv)
        if (result == (-1).toLong()) {
            Toast.makeText(context, "Fail to insert", Toast.LENGTH_LONG).show()

        } else {
            Toast.makeText(context, "Insert sucessfully", Toast.LENGTH_LONG).show()
            Log.d("insertpayment", "paymentSlip: "+paymentModal.sublotal)

        }
    }

    fun insertAdmin(admin: Admin) {
        var db = this.writableDatabase
        var cv = ContentValues()
        cv.put(Admin_name, admin.admin_name)
        cv.put(Password, admin.password)
        var result = db.insert(admin_table, null, cv)
        if (result == -1.toLong()) {
            Toast.makeText(context, "Unavaible to sign up.", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Sign Up sucessful", Toast.LENGTH_LONG).show()
        }
    }

    fun readAllData(): MutableList<CustomerDataModal> {
        var mutableList: MutableList<CustomerDataModal> = ArrayList()
        var quary = "select * from " + tablename
        var db = this.readableDatabase
        var result = db.rawQuery(quary, null)
        if (result.moveToFirst()) {
            do {
                var customerdatamodal = CustomerDataModal()
                customerdatamodal.id = result.getString(result.getColumnIndex(id)).toInt()
                customerdatamodal.name = result.getString(result.getColumnIndex(Name))
                customerdatamodal.email = result.getString(result.getColumnIndex(email))
                customerdatamodal.fathername = result.getString(result.getColumnIndex(fathername))
                customerdatamodal.mobileno = result.getString(result.getColumnIndex(mobileno))

                mutableList.add(customerdatamodal)
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return mutableList

    }

    fun getPriviousBalnace(customer_id:Int):PaymentModal?{
        var db=this.readableDatabase
        var quary="Select Remain from  Payment where  Customer_ID="+customer_id+" order by Payment_ID desc limit 1 "
        var paymentModal:PaymentModal?=null
        var result=db.rawQuery(quary,null)
            if (result.moveToFirst()) {

                    var prm = result.getString(result.getColumnIndex("Remain"))
                    paymentModal = PaymentModal(prm)

            }

        result.close()
        db.close()
        return paymentModal


    }

    fun readAdmin(password: String): Admin? {
        // var mutableList:MutableList<Admin> = ArrayList()
        var queue = "select * from " + admin_table
        var db = this.readableDatabase
        var admin: Admin? = null
        var result = db.rawQuery(queue, null)
        if (result.moveToFirst()) {


            var name = result.getString(result.getColumnIndex(Admin_name))
            var password = result.getString(result.getColumnIndex(Password))
            admin = Admin(name, password)
            result.close()
        }

        db.close()
        return admin
    }

    fun checkadminvalidation():Boolean{
        var queue = "select * from " + admin_table
        var db = this.readableDatabase
        var result = db.rawQuery(queue, null)
        if (result.count<=0){
            result.close()
            return false
        }
        result.close()
        return true
    }

    fun updatePassword(oldpassword: String, newpassword: String): Int {
        var db = this.writableDatabase
        var cv = ContentValues()
        cv.put(Password, newpassword)
        var whereclase: Array<String> = arrayOf(oldpassword)
        val result = db.update(admin_table, cv, Password + " = ?", whereclase)
        db.close()
        return result
    }


    @RequiresApi(Build.VERSION_CODES.N)
    fun getProductlistCustomerWise(customer_id:Int):MutableList<ProductListModal>{
        var date= SimpleDateFormat("MM-DD-YYYY", Locale.getDefault()).format(Date())
        var mutableList:MutableList<ProductListModal> = ArrayList()
        var db=this.readableDatabase
        var queue = "select * from " + productorder+" where Customer_ID="+customer_id

        var result = db.rawQuery(queue, null)
        if (result.moveToFirst()) {
            do {
                var productListModal=ProductListModal()
                productListModal.date=date
                    date=result.getString(6)
              productListModal. productname = result.getString(result.getColumnIndex(productname))
              productListModal. price = result.getString(result.getColumnIndex(productprice))
              productListModal. quality=result.getString(result.getColumnIndex(productquantity))
              productListModal. totalprice=result.getString(result.getColumnIndex(producttotal))
                mutableList.add(productListModal)

            }while (result.moveToNext())


        }
        result.close()
        db.close()
        return mutableList

    }

    fun readproductsale(): MutableList<ProductListModal> {
        var mutableList: MutableList<ProductListModal> = ArrayList()
        var quary = "select * from " + productorder//+" where "+ payment_id+"="+order_id
        var db = this.readableDatabase
        var result = db.rawQuery(quary, null)
        if (result.moveToFirst()) {
            do {
                var productListModal = ProductListModal()
                productListModal.productname = result.getString(result.getColumnIndex(productname))
                productListModal.price = result.getString(result.getColumnIndex(productprice))
                productListModal.quality = result.getString(result.getColumnIndex(productquantity))
                productListModal.totalprice = result.getString(result.getColumnIndex(producttotal))
                mutableList.add(productListModal)
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return mutableList
    }

    fun showPayment(): MutableList<PaymentModal> {
        var mutableList: MutableList<PaymentModal> = ArrayList()
        var quary = "select * from " + payment+" where Customer_ID="+customer_id
        var db = this.readableDatabase
        var result = db.rawQuery(quary, null)
        if (result.moveToFirst()) {
            do {
                var paymentModal = PaymentModal()
                paymentModal.sublotal = result.getString(result.getColumnIndex(sublotal))
                paymentModal.discount = result.getString(result.getColumnIndex(discount))
                paymentModal.charges = result.getString(result.getColumnIndex(charges))
                paymentModal.payamount = result.getString(result.getColumnIndex(payamount))
                paymentModal.grandtoatal = result.getString(result.getColumnIndex(grandtoatal))
                paymentModal.remain = result.getString(result.getColumnIndex(remain))
                paymentModal.paymenttypes = result.getString(result.getColumnIndex(paymenttypes))
                mutableList.add(paymentModal)

            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return mutableList
    }

    fun searchCustomerByMobileno(mobileno: String): CustomerDataModal? {
        var db = this.readableDatabase
        var customerDataModal: CustomerDataModal? = null
        var quary = "Select * from " + tablename + " where Mobileno=" + mobileno
        var result = db.rawQuery(quary, null)
        if (result.moveToFirst()) {
            result.moveToFirst()
            val id = Integer.parseInt(result.getString(0))
            val name = result.getString(result.getColumnIndex(Name))
            customerDataModal = CustomerDataModal(id, name)

        }
        db.close()
        return customerDataModal

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun getPaymentDate(customer_id:Int):MutableList<PaymentModal>{
        var date= SimpleDateFormat("MM-DD-YYYY", Locale.getDefault()).format(Date())
        var mutableList:MutableList<PaymentModal> = ArrayList()
        var db=this.readableDatabase
        var queue = "select * from " + payment+" where Customer_ID="+customer_id

        var result = db.rawQuery(queue, null)
        if (result.moveToFirst()) {
            do {
                var productListModal=PaymentModal()
                productListModal.date=date
                date=result.getString(result.getColumnIndex(payment_date))
                productListModal.paymentid = result.getString(result.getColumnIndex(paymentid))
               productListModal.sublotal = result.getString(result.getColumnIndex(sublotal))
               productListModal.discount= result.getString(result.getColumnIndex(discount))
               productListModal.charges = result.getString(result.getColumnIndex(charges))
               productListModal.payamount = result.getString(result.getColumnIndex(payamount))
               productListModal.grandtoatal = result.getString(result.getColumnIndex(grandtoatal))
               productListModal.remain = result.getString(result.getColumnIndex(remain))
               productListModal.paymenttypes = result.getString(result.getColumnIndex(paymenttypes))
                mutableList.add(productListModal)

            }while (result.moveToNext())


        }
        result.close()
        db.close()
        return mutableList

    }

    fun showPaymentOrderwiae(Paymentid:Int):PaymentModal?{
       // var mutableList: MutableList<PaymentModal> = ArrayList()
        var quary = "select * from " + payment+" where Payment_ID="+Paymentid
        var db = this.readableDatabase
        var paymentModal: PaymentModal? =null
        var result = db.rawQuery(quary, null)
        if (result.moveToFirst()) {
            do {

               var sublotal = result.getString(result.getColumnIndex(sublotal))
               var discount = result.getString(result.getColumnIndex(discount))
               var charges = result.getString(result.getColumnIndex(charges))
               var payamount = result.getString(result.getColumnIndex(payamount))
               var grandtoatal = result.getString(result.getColumnIndex(grandtoatal))
               var remain = result.getString(result.getColumnIndex(remain))
               var paymenttypes = result.getString(result.getColumnIndex(paymenttypes))
                 paymentModal = PaymentModal(sublotal,discount,charges,payamount,grandtoatal,remain,paymenttypes)

            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return paymentModal
    }

}


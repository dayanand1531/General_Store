package com.example.generalstore.Datamodal

import android.os.Build
import androidx.annotation.RequiresApi
import java.sql.Date
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ProductListModal {

    var id:Int=0
     var productname:String=""
     var price:String=""
     var quality:String=""
     var totalprice:String=""
     var expadable:Boolean = false
    var date:String=""
     var customer_id:Int=0
    constructor(productname:String,price:String,quality:String,totalprice:String,customer_id:Int){
        this.productname=productname
        this.price=price
        this.quality=quality
        this.totalprice=totalprice
        this.customer_id=customer_id
        //this.date=date
    }
    constructor()
    constructor(productname:String,price:String,quality:String,totalprice:String){
        this.productname=productname
        this.price=price
        this.quality=quality
        this.totalprice=totalprice

    }
    constructor(date:String,productname:String,price:String,quality:String,totalprice:String){
        this.productname=productname
        this.price=price
        this.quality=quality
        this.totalprice=totalprice
        this.date=date
        this.expadable=false
    }

    constructor(date: String,id:Int) {
        this.date = date
        this.id=id
    }
}
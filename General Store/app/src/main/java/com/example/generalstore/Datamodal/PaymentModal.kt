package com.example.generalstore.Datamodal

class PaymentModal {
    var paymentid:String=""
    var sublotal:String=""
    var discount:String=""
    var charges:String=""
    var payamount:String=""
    var grandtoatal:String=""
    var remain:String=""
    var paymenttypes:String=""
    var customer_id:String=""
    var privious_payment:String=""
    var date:String=""
    var expadable:Boolean = false
    constructor(
        date:String,
        paymentid:String,
        sublotal: String,
        discount: String,
        charges: String,
        payamount: String,
        grandtoatal: String,
        remain: String,
        paymenttypes: String,
        customer_id:String,
        privious_payment:String
    ) {
        this.date=date
        this.paymentid=paymentid
        this.sublotal = sublotal
        this.discount = discount
        this.charges = charges
        this.payamount = payamount
        this.grandtoatal = grandtoatal
        this.remain = remain
        this.paymenttypes = paymenttypes
        this.customer_id=customer_id
        this.privious_payment=privious_payment
        this.expadable=false
    }
    constructor()
    constructor(privious_payment: String) {
        this.privious_payment = privious_payment
    }

    constructor(sublotal: String, discount: String, charges: String, payamount: String, grandtoatal: String, remain: String, paymenttypes: String){
        this.sublotal = sublotal
        this.discount = discount
        this.charges = charges
        this.payamount = payamount
        this.grandtoatal = grandtoatal
        this.remain = remain
        this.paymenttypes = paymenttypes
    }

    constructor(toString: String, toString1: String, toString2: String, toString3: String, toString4: String, toString5: String, toString6: String, customerId: String, toString7: String)


}
package com.example.generalstore.Datamodal

class CustomerDataModal {
          var id:Int = 0
          var name:String=""
          var email:String=""
          var mobileno:String=""
          var fathername:String=""

    constructor(id: Int,name:String,email:String,fathername:String,mobileno:String){
       this.id=id
        this.name=name
        this.email=email
        this.fathername=fathername
        this.mobileno=mobileno

    }

    constructor()
    constructor(id: Int, name: String?){
        this.id=id
        if (name != null) {
            this.name=name
        }
    }
    constructor(name:String,email:String,fathername:String,mobileno:String){
       // this.id=id
        this.name=name
        this.email=email
        this.fathername=fathername
        this.mobileno=mobileno

    }
}
package com.example.generalstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.generalstore.SqliteDatabase.MyCustomerDatahelper

class Splesh : AppCompatActivity() {
    var context=this
    var db= MyCustomerDatahelper(context)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splesh)
        Handler().postDelayed({
           gotonext()
        },5000)
    }

    fun gotonext(){
        var data=db.checkadminvalidation()
        if (data==true){
            intent= Intent(this,Login::class.java)
            startActivity(intent)
            finish()
        }
        else{
            intent= Intent(this,SignUp::class.java)
            startActivity(intent)
            finish()

        }

    }
}
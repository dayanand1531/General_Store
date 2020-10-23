package com.example.generalstore

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.generalstore.Datamodal.Admin
import com.example.generalstore.SqliteDatabase.MyCustomerDatahelper
import kotlinx.android.synthetic.main.activity_change__password.*
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*

class Login : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        changePassword()
        tbn_login.setOnClickListener(View.OnClickListener {
            if (!validationLoginPassword()){
                return@OnClickListener
            }
            else{

               readAdminCR()
                et_loginpassword.setText("")
            }
        })
        et_loginpassword.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validationLoginPassword()
            }
        })
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun validationLoginPassword():Boolean{
        var loginpassword=et_loginpassword.text.toString().trim()

        if (loginpassword.isEmpty()){
            tv_error_password.visibility= View.VISIBLE
            tv_error_password.text="*Please enter valid password."
           // getdate()
            return false
        }
        else if (loginpassword.length<5){
            tv_error_password.visibility= View.VISIBLE
            tv_error_password.text="*Password must be four char."

            return false
        }
        else{
           tv_error_password.visibility= View.GONE
           tv_error_password.text=""
            return true

        }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun readAdminCR(){
        var name=""
        var password=""
        var context=this
        var db=MyCustomerDatahelper(context)
        var data=db.readAdmin(et_loginpassword.text.toString())
      if (data!=null){
          name=data.admin_name
          password=data.password
      }
        if (password.equals(et_loginpassword.text.toString())){
            intent = Intent(this, MainActivity::class.java)
            intent.putExtra("admin",name)
            startActivity(intent)
        }
        else{
          //  getdate()
            Toast.makeText(this,"Password don't match ",Toast.LENGTH_LONG).show()
        }



    }

  /*  @RequiresApi(Build.VERSION_CODES.N)
    fun getdate(){
        var date=SimpleDateFormat("MM-DD-YYYY  HH:MM:SS", Locale.getDefault()).format(Date())
        tv_error_password.text=date
    }*/

    fun changePassword(){
        tv_change_password.setOnClickListener(View.OnClickListener {
            intent= Intent(this,Change_password::class.java)
            startActivity(intent)
        })
    }
}
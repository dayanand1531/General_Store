package com.example.generalstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View

import com.example.generalstore.Datamodal.CustomerDataModal
import com.example.generalstore.SqliteDatabase.MyCustomerDatahelper
import kotlinx.android.synthetic.main.activity_add_new_user.*
import kotlinx.android.synthetic.main.customer_report_data.*
import java.util.regex.Pattern

class AddNewUser : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_user)
        etUsername.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validationUsername()
            }

        })
        etmobileno.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
             validationMobileno()
            }
        })

        etemailid.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validationEmail()
            }
        })
        etfathername.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validationFatherName()
            }
        })
        btnaddnewuser.setOnClickListener(View.OnClickListener {
            if (!validationUsername() && !validationFatherName() && !validationMobileno() && !validationEmail()) {
                return@OnClickListener
            } else {
                datastore()
                etUsername.setText(null)
                etemailid.setText(null)
                etmobileno.setText(null)
                etfathername.setText(null)
                intent = Intent(this, UserReport::class.java)
                startActivity(intent)
            }
        })

    }

    fun datastore(){
        var user=CustomerDataModal(etUsername.text.toString(),etemailid.text.toString(),etfathername.text.toString(),etmobileno.text.toString())
        var context=this
        var db=MyCustomerDatahelper(context)
        db.insertData(user)
    }

    fun validationUsername():Boolean{
        if (etUsername.text.toString().trim().isEmpty()){
            tv_error_name.visibility=View.VISIBLE
            tv_error_name.text="*Please enter full name."
            return false
        }
        else if (etUsername.text.toString().trim().length<3){
            tv_error_name.visibility=View.VISIBLE
            tv_error_name.text="*Name length more then two\n char."
            return false
        }
        else{
            tv_error_name.visibility=View.GONE
            tv_error_name.text=""
            return true

        }

    }

    fun validationFatherName():Boolean{
        var fathername:String=etfathername.text.toString().trim()
        if (fathername.isEmpty()){
            tv_error_farher_name.visibility=View.VISIBLE
            tv_error_farher_name.text="*Please enter father name."
            return false
        }
        else if (fathername.length<3){
            tv_error_farher_name.visibility=View.VISIBLE
            tv_error_farher_name.text="*Name length more then two\n char."
            return false
        }
        else{
            tv_error_farher_name.visibility=View.GONE
            tv_error_farher_name.text=""
            return true

        }


    }


    fun validationEmail():Boolean{
        var email:String=etemailid.text.toString().trim()
        if (email.isEmpty()){
            tv_error_email.visibility=View.VISIBLE
            tv_error_email.text="*Please enter Email ID"
            return false
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
           tv_error_email.visibility=View.VISIBLE
           tv_error_email.text="*Please enter valid Email\n ID"
            return false
        }
        else{
            tv_error_email.visibility=View.GONE
            tv_error_email.text=""
            return true

        }


    }

    fun validationMobileno():Boolean{
        var mobile:String=etmobileno.text.toString().trim()
        var p: Pattern =Pattern.compile("^[6-9]\\d{9}\$")

        if (mobile.isEmpty()){
            tv_error_mobile_no.visibility=View.VISIBLE
            tv_error_mobile_no.text="*Please enter Mobile no."
            return false
        }
        else if (!p.matcher(mobile).matches()){
           tv_error_mobile_no.visibility=View.VISIBLE
           tv_error_mobile_no.text="*Please enter valid Mobile\n no."
            return false
        }
        else{
            tv_error_mobile_no.visibility=View.GONE
            tv_error_mobile_no.text=""
            return true

        }

    }
}

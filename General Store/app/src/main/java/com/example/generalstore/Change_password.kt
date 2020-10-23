package com.example.generalstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.example.generalstore.SqliteDatabase.MyCustomerDatahelper
import kotlinx.android.synthetic.main.activity_change__password.*

class Change_password : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change__password)
        btn_set_password.setOnClickListener(View.OnClickListener {
            if (!validationOldPassword()&&!validationNewPassword()&&!validationConfPassword()){
                return@OnClickListener
            }
            else{
               updatePassword()

            }
        })

        et_old_password.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validationOldPassword()
            }
        })

        et_new_password.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               validationNewPassword()
            }
        })

        et_confirm_password.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validationConfPassword()
            }
        })
    }



    fun validationOldPassword():Boolean{
        var oldpassword=et_old_password.text.toString().trim()
        if (oldpassword.isEmpty()){
            tv_error_old_password.visibility=View.VISIBLE
            tv_error_old_password.text="*Please enter valid password."
            return false
        }
        else if (oldpassword.length<5){
            tv_error_old_password.visibility=View.VISIBLE
            tv_error_old_password.text="*Password length atlest four\n char."

            return false
        }
        else{
            tv_error_old_password.visibility=View.GONE
            tv_error_old_password.text=""
            return true

        }

    }
    fun validationNewPassword():Boolean{
        var newpassword=et_new_password.text.toString().trim()
        if (newpassword.isEmpty()){
            tv_error_new_password.visibility=View.VISIBLE
            tv_error_new_password.text="*Please enter valid password."
            return false
        }
        else if (newpassword.length<5){
            tv_error_new_password.visibility=View.VISIBLE
            tv_error_new_password.text="*Password length atlest four \n char."

            return false
        }
        else{
            tv_error_new_password.visibility=View.GONE
            tv_error_new_password.text=""
            return true

        }

    }
    fun validationConfPassword():Boolean{
        var confpassword=et_confirm_password.text.toString().trim()
        var newpassword=et_new_password.text.toString().trim()
        if (confpassword.isEmpty()){
            tv_error_conf_password.visibility=View.VISIBLE
            tv_error_conf_password.text="*Please enter valid password."
            return false
        }
        else if (!confpassword.equals(newpassword)){
           tv_error_conf_password.visibility=View.VISIBLE
           tv_error_conf_password.text="*Password must be same as \n new password."

            return false
        }
        else{
           tv_error_conf_password.visibility=View.GONE
           tv_error_conf_password.text=""
            return true

        }

    }

    fun updatePassword(){
        var oldpassword=et_old_password.text.toString()
        var newpassword=et_new_password.text.toString()
        var context=this
        var db=MyCustomerDatahelper(context)

        var update=db.updatePassword(oldpassword,newpassword)
        if (update<=0){
            Toast.makeText(context,"Password not update !",Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(context,"Password update sucessfully !",Toast.LENGTH_LONG).show()
            intent= Intent(this,Login::class.java)
            startActivity(intent)

        }

    }


}
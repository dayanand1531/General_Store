package com.example.generalstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.generalstore.Datamodal.Admin
import com.example.generalstore.SqliteDatabase.MyCustomerDatahelper
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        et_old_password.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            validationUserName()
            }
        })

        et_new_password.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            validatioOldPassword()
            }
        })

        et_confirm_password.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
             validationConfirmPassword()
            }
        })

        btn_set_password.setOnClickListener(View.OnClickListener {
            if (!validationUserName()&&!validatioOldPassword()&&!validationConfirmPassword()){
                return@OnClickListener
            }
            else{
                insertSignupDetail()
                intent= Intent(this,Login::class.java)
                startActivity(intent)
            }
        })
    }

    fun validationUserName():Boolean{
        var username=et_old_password.text.toString().trim()

        if (username.isEmpty()){
            tv_fullname_error.visibility= View.VISIBLE
            tv_fullname_error.text="*Please enter your name."
            return false
        }
        else if (username.length<3){
            tv_fullname_error.visibility= View.VISIBLE
            tv_fullname_error.text="*Username atlest two char."

            return false
        }
        else{
           tv_fullname_error.visibility= View.GONE
           tv_fullname_error.text=""
            return true

        }

    }
    fun validatioOldPassword():Boolean{
        var newpassword=et_new_password.text.toString().trim()

        if (newpassword.isEmpty()){
            tv_newpassword_error.visibility= View.VISIBLE
            tv_newpassword_error.text="*Please enter valid password."
            return false
        }
        else if (newpassword.length<5){
           tv_newpassword_error.visibility= View.VISIBLE
           tv_newpassword_error.text="*Password must be more then \nfour char."

            return false
        }
        else{
            tv_newpassword_error.visibility= View.GONE
            tv_newpassword_error.text=""
            return true

        }

    }
    fun validationConfirmPassword():Boolean{
        var confpassword=et_confirm_password.text.toString().trim()
        var newpassword=et_new_password.text.toString().trim()

        if (confpassword.isEmpty()){
            tv_confirmpassword_error.visibility= View.VISIBLE
            tv_confirmpassword_error.text="*Please enter valid password."
            return false
        }
        else if (!confpassword.equals(newpassword)){
             tv_confirmpassword_error.visibility= View.VISIBLE
             tv_confirmpassword_error.text="*Confirm password should be \nsame as new password."

            return false
        }
        else{
             tv_confirmpassword_error.visibility= View.GONE
             tv_confirmpassword_error.text=""
            return true

        }

    }

    fun insertSignupDetail(){
        var context=this
        var db=MyCustomerDatahelper(context)
        var admininfo=Admin(
            et_old_password.text.toString(),
            et_new_password.text.toString()
        )
        db.insertAdmin(admininfo)
    }
}
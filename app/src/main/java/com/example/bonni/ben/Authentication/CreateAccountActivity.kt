package com.example.bonni.ben.Authentication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.bonni.ben.R
import kotlinx.android.synthetic.main.activity_create_account.*


class CreateAccountActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

      var create_btn = findViewById<Button>(R.id.create_btn) as Button
      var create_arrow_back = findViewById<ImageView>(R.id.create_arrow_back) as ImageView

      var firstname = create_editText_firstname.text
      var lastname= create_editText_lastname.text
      var email = create_editText_email.text
      var password = create_editText_password.text
//      var confirm_password = create_editText_confirm_password.text


      create_btn.setOnClickListener {
        BenAPI.register("{ \"user\":{ \"firstname\": \"$firstname\"," +"\"lastname\": \"$lastname\","+"\"email\": \"$email\","+"\"password\": \"$password\"} }")
      }


      create_arrow_back.setOnClickListener {
        super.onBackPressed()
      }




    }
}

package com.example.bonni.ben.CreateAccount

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.beust.klaxon.Parser
import com.example.bonni.ben.R
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.google.gson.JsonObject
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



      create_btn.setOnClickListener {
        Toast.makeText(this, "{ \"user\":{ \"firstname\": \"$firstname\"," +"\"lastname\": \"$lastname\","+"\"email\": \"$email\","+"\"password\": \"$password\", } }", Toast.LENGTH_LONG).show()

        //val create_user = Gson().toJson("{ \"user\":{ \"firstname\": \"$firstname\"," +"\"lastname\": \"$lastname\","+"\"email\": \"$email\","+"\"password\": \"$password\", } }")
        BenAPI.register("{ \"user\":{ \"firstname\": \"$firstname\"," +"\"lastname\": \"$lastname\","+"\"email\": \"$email\","+"\"password\": \"$password\"} }")
        //Toast.makeText(this, json.toString(), Toast.LENGTH_SHORT).show()


      }


      create_arrow_back.setOnClickListener {
        finish()
      }




    }
}

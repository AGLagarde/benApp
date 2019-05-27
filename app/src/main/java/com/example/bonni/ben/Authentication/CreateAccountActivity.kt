package com.example.bonni.ben.Authentication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.example.bonni.ben.House.HouseActivity
import com.example.bonni.ben.R
import com.github.kittinunf.fuel.Fuel
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_create_account.*
import java.nio.charset.Charset


public open class CreateAccountActivity : AppCompatActivity() {

  var user_token = ""
  var response : Int = 0

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



      // ***** Création de compte : firstname, lastname, email, password *****


      fun register(userRegister: String, callback: ((token: String, response : Int) -> Unit)) {

        Fuel.post("${BenAPI.base_url}" + "${BenAPI.api_user_create}")
          .header("Content-Type" to "application/json")
          .body(userRegister, Charset.defaultCharset())
          .responseString { request, response, result ->
            when (result) {
              is com.github.kittinunf.result.Result.Success -> {

                val data = response.data.toString(Charsets.UTF_8)
                val jelement = JsonParser().parse(data)
                val jobject = jelement.asJsonObject
                val success = response.statusCode
                val user_string = (jobject.get("data")as JsonObject).get("user").toString()
                var token_string = ((jobject.get("data")as JsonObject).get("token")).toString()
                token_string = token_string.substring(1, token_string.length-1)
                callback(token_string, success)

                val intent_house_page = Intent(applicationContext, HouseActivity::class.java)
                intent_house_page.putExtra("token", token_string)
                startActivity(intent_house_page)


              }
              is com.github.kittinunf.result.Result.Failure -> {
                Log.d("okay", "failure")
              }
            }
          }


      }



      create_btn.setOnClickListener {
        register("{ \"user\":{ \"firstname\": \"$firstname\"," +"\"lastname\": \"$lastname\","+"\"email\": \"$email\","+"\"password\": \"$password\"} }",
          { token: String, success: Int ->
            user_token = token
            response = success
          })

      }


      create_arrow_back.setOnClickListener {
        super.onBackPressed()
      }




    }
}

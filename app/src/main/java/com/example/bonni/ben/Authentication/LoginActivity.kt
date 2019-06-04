package com.example.bonni.ben.Authentication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.util.Log
import android.widget.*
import com.example.bonni.ben.DataClass.User
import com.example.bonni.ben.MainActivity
import com.example.bonni.ben.NavigationBottomBar.Fragment.HomeFragment
import com.example.bonni.ben.R
import com.github.kittinunf.fuel.Fuel
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_login.*
import java.nio.charset.Charset

class LoginActivity : AppCompatActivity() {



  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)

    var email = login_editText_username.text
    var password = login_editText_password.text

    var login_btn_connexion = findViewById(R.id.login_btn_connexion) as Button
    var login_btn_creation_account = findViewById(R.id.login_btn_creation_account) as Button

    var userList = ArrayList<User>()



    // ***** Login : email, password *****


    fun login(userLogin: String) {
      Fuel.post("${BenAPI.base_url}" + "${BenAPI.api_user_login}")
        .header("Content-Type" to "application/json")
        .body(userLogin, Charset.defaultCharset())
        .responseString { request, response, result ->
          when (result) {
            is com.github.kittinunf.result.Result.Success -> {


              val data = response.data.toString(Charsets.UTF_8)
              val jelement = JsonParser().parse(data)
              val jobject = jelement.asJsonObject
              val success = response.statusCode
              var user_string = ((jobject.get("data")as JsonObject).get("user")as JsonObject).get("firstname").toString()
              var token_string = (jobject.get("data")as JsonObject).get("token").toString()
              token_string = token_string.substring(1, token_string.length-1)
              user_string = user_string.substring(1, user_string.length-1)

              val sharedPreferences_name = this.getSharedPreferences("user_name", MODE_PRIVATE);
              sharedPreferences_name.edit()
                .putString("user_name", user_string)
                .apply()



              val intent_home_page = Intent(applicationContext, MainActivity::class.java)
              intent_home_page.putExtra("token", token_string)
              intent_home_page.putExtra("user", user_string)
              startActivity(intent_home_page)

              Log.d("okay", token_string)
            }
            is com.github.kittinunf.result.Result.Failure -> {
              Log.d("okay", "failure")
            }
          }

        }

    }


    var user_string : String = ""
    var user_token : String = ""
    var response : Int = 0


    login_btn_connexion.setOnClickListener {
      login("{ \"user\":{ \"email\": \"$email\","+"\"password\": \"$password\"} }")
    }



    login_btn_creation_account.setOnClickListener {
      val intent_create_account = Intent(applicationContext, CreateAccountActivity::class.java)
      startActivity(intent_create_account)
    }




  }
}

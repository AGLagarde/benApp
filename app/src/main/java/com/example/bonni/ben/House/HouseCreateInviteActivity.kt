package com.example.bonni.ben.House

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.bonni.ben.Authentication.BenAPI
import com.example.bonni.ben.MainActivity
import com.example.bonni.ben.NavigationBottomBar.Fragment.HomeFragment
import com.example.bonni.ben.R
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.authentication
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.activity_house_create_invite.*
import java.nio.charset.Charset

class HouseCreateInviteActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_house_create_invite)



    var house_create_invite_editText = house_create_invite_editText.text
    var house_create_invite_btn = findViewById(R.id.house_create_invite_btn) as Button

    var response : Int = 0
    val token=intent.getStringExtra("token")


    // ***** HouseCreateInvite : email *****
    fun houseCreateInvite(houseCreateInvite: String, callback: ((response : Int) -> Unit)) {

      Fuel.post("${BenAPI.base_url}" + "${BenAPI.api_house_create_invite}")
        .header("Content-Type" to "application/json")
        .authentication()
        .bearer(token)
        .body(houseCreateInvite, Charset.defaultCharset())
        .responseString { request, response, result ->
          when (result) {
            is com.github.kittinunf.result.Result.Success -> {
              val success = response.statusCode
              callback(success)

              val intent_home_page = Intent(applicationContext, MainActivity::class.java)
              intent_home_page.putExtra("token", token)
              startActivity(intent_home_page)

            }
            is com.github.kittinunf.result.Result.Failure -> {
              Log.d("okay", "failure")
            }
          }
        }


    }





    house_create_invite_btn.setOnClickListener {

      houseCreateInvite("{ \"email\": \"$house_create_invite_editText\"}", { success: Int ->
        response = success
      })


    }



  }
}

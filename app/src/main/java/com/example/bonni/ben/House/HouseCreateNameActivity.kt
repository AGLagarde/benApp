package com.example.bonni.ben.House

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.bonni.ben.Authentication.BenAPI
import com.example.bonni.ben.Authentication.CreateAccountActivity
import com.example.bonni.ben.R
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.authentication
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.activity_house_create_name.*
import java.nio.charset.Charset

class HouseCreateNameActivity : HouseActivity() {


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_house_create_name)

    val token=intent.getStringExtra("token")

    var house_create_name_btn = findViewById(R.id.house_create_name_btn) as Button

    var house_create_name = house_create_name_editText.text




    // ***** HouseCreateName : name *****

    fun houseCreateName(houseCreateName: String, callback: ((response : Int) -> Unit)) {

      Fuel.post("${BenAPI.base_url}" + "${BenAPI.api_house_create_name}")
        .header("Content-Type" to "application/json")
        .body(houseCreateName, Charset.defaultCharset())
        .authentication()
        .bearer(token)
        .responseString { request, response, result ->
          when (result) {
            is com.github.kittinunf.result.Result.Success -> {

              val intent_house_create_invite = Intent(applicationContext, HouseCreateInviteActivity::class.java)
              intent_house_create_invite.putExtra("token", token)
              startActivity(intent_house_create_invite)

              val success = response.statusCode
              callback(success)
            }
            is com.github.kittinunf.result.Result.Failure -> {
              Log.d("okay", "failure")
            }
          }
        }


    }




    var response : Int = 0

    house_create_name_btn.setOnClickListener {
      houseCreateName("{ \"house\":{ \"name\": \"$house_create_name\"} }", { success: Int ->

        response = success
      })



    }


  }
}


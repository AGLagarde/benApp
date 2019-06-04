package com.example.bonni.ben.House.JoinHouse

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.bonni.ben.Authentication.BenAPI
import com.example.bonni.ben.Authentication.LoginActivity
import com.example.bonni.ben.House.CreateHouse.HouseCreateInviteActivity
import com.example.bonni.ben.R
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.authentication
import kotlinx.android.synthetic.main.activity_house_join.*
import java.nio.charset.Charset

class HouseJoinActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_house_join)

    val token=intent.getStringExtra("token")
    val house_join_editText = house_join_editText.text


    fun houseJoin(houseJoinCode: String) {

      Fuel.post("${BenAPI.base_url}" + "${BenAPI.api_house_join}")
        .header("Content-Type" to "application/json")
        .body(houseJoinCode, Charset.defaultCharset())
        .authentication()
        .bearer(token)
        .responseString { request, response, result ->
          when (result) {
            is com.github.kittinunf.result.Result.Success -> {

              this.runOnUiThread {
                Toast.makeText(this, "Tu as bien rejoint la coloc ! Connecte toi pour commencer l'aventure ;)", Toast.LENGTH_LONG).show()
                val intent_login = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intent_login)
              }




            }
            is com.github.kittinunf.result.Result.Failure -> {
              Log.d("okay", "failure")
            }
          }
        }
    }


    house_join_btn.setOnClickListener {
      houseJoin("{ \"code\": \"$house_join_editText\"}")
    }



  }
}

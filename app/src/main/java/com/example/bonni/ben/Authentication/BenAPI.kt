package com.example.bonni.ben.Authentication

import android.util.Log
import com.example.bonni.ben.DataClass.User
import com.github.kittinunf.fuel.Fuel
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.nio.charset.Charset


class BenAPI {

  companion object {

    var base_url: String = "http://ulysse.idequanet.com/ben/web"
    var api_user_create: String = "/api/user/create"
    var api_user_login: String = "/api/user/login"



    // ***** Création de compte : firstname, lastname, email, password *****


    fun register(userRegister: String) {

      Fuel.post("$base_url" + "$api_user_create")
        .header("Content-Type" to "application/json")
        .body(userRegister, Charset.defaultCharset())
        .responseString { result ->
          when (result) {
            is com.github.kittinunf.result.Result.Success -> {
              Log.d("okay", "success")
              val resultat = result.value
              Log.d("result", resultat)
            }
            is com.github.kittinunf.result.Result.Failure -> {
              Log.d("okay", "failure")
            }
          }
        }


    }



    // ***** Login : email, password *****


    fun login(userLogin: String, callback: ((token: String, user: String, response : Int) -> Unit)) {
      Fuel.post("$base_url" + "$api_user_login")
        .header("Content-Type" to "application/json")
        .body(userLogin, Charset.defaultCharset())
        .responseString { request, response, result ->
          when (result) {
            is com.github.kittinunf.result.Result.Success -> {
              val data = response.data.toString(Charsets.UTF_8)
              val jelement = JsonParser().parse(data)
              val jobject = jelement.asJsonObject
              val success = response.statusCode
              val user_string = (jobject.get("data")as JsonObject).get("user").toString()
              val token_string = (jobject.get("data")as JsonObject).get("token").toString()
              callback(token_string, user_string, success)

              Log.d("okay", token_string)
            }
            is com.github.kittinunf.result.Result.Failure -> {
              Log.d("okay", "failure")
            }
          }

        }

    }


  }
}

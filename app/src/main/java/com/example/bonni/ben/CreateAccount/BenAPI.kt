package com.example.bonni.ben.CreateAccount

import android.util.Log
import android.widget.Toast
import com.beust.klaxon.Json
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import java.io.UTFDataFormatException
import java.nio.charset.Charset
import javax.xml.transform.Result
import kotlin.reflect.jvm.internal.impl.util.CheckResult


class BenAPI {

  companion object {

    var base_url : String = "http://ulysse.idequanet.com/ben/web"
    var api_user_create : String = "/api/user/create"

    fun register(userRegister : String){

      Fuel.post("$base_url"+"$api_user_create")
        .header("Content-Type" to "application/json")
        .body(userRegister, Charset.defaultCharset())
        .responseString { result ->
        when (result) {
        is com.github.kittinunf.result.Result.Success -> {
          Log.d("okay", "success")

        }
        is com.github.kittinunf.result.Result.Failure -> {
          Log.d("okay", "failure")
        }
      }
      }


    }


  }


}

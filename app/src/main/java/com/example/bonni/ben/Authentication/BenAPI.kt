package com.example.bonni.ben.Authentication

import android.app.Application
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.util.Log
import android.view.ContextMenu
import com.example.bonni.ben.DataClass.User
import com.example.bonni.ben.MainActivity
import com.github.kittinunf.fuel.Fuel
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.nio.charset.Charset
import kotlin.coroutines.coroutineContext
import android.support.v4.content.ContextCompat.startActivity
import com.example.bonni.ben.NavigationBottomBar.Fragment.HomeFragment


class BenAPI {

  companion object {

    var base_url: String = "http://ulysse.idequanet.com/ben/web"
    var api_user_create: String = "/api/user/create"
    var api_user_login: String = "/api/user/login"
    var api_house_create_name: String = "/api/house/create"
    var api_house_create_invite: String = "/api/house/send-invitation"





  }
}

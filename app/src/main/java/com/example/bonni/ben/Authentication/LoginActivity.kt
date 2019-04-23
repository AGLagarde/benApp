package com.example.bonni.ben.Authentication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.widget.*
import com.example.bonni.ben.DataClass.User
import com.example.bonni.ben.MainActivity
import com.example.bonni.ben.NavigationBottomBar.Fragment.HomeFragment
import com.example.bonni.ben.R
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {



  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)

    var email = login_editText_username.text
    var password = login_editText_password.text

    var login_btn_connexion = findViewById(R.id.login_btn_connexion) as Button
    var login_btn_creation_account = findViewById(R.id.login_btn_creation_account) as Button

    var userList = ArrayList<User>()

    val user1 = User("de", "de", "e", "ed", 2, "d")

    var user_string : String = ""
    var user_token : String = ""
    var response : Int = 0

    login_btn_connexion.setOnClickListener {


      BenAPI.login("{ \"user\":{ \"email\": \"$email\","+"\"password\": \"$password\"} }", { token: String, user: String, success: Int ->
        user_token = token
        user_string = user
        response = success

      })
      if(response == 200){
        val intent_home_page = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent_home_page)
      }
      else Toast.makeText(this, "Identifiant ou mot de passe incorrect", Toast.LENGTH_SHORT).show()
    }



    login_btn_creation_account.setOnClickListener {
      val intent_create_account = Intent(applicationContext, CreateAccountActivity::class.java)
      startActivity(intent_create_account)
    }




  }
}

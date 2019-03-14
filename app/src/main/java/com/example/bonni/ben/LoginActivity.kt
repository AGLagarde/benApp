package com.example.bonni.ben

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.bonni.ben.CreateAccount.CreateAccountActivity

class LoginActivity : AppCompatActivity() {



  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)

    var login_editText_username = findViewById(R.id.login_editText_username) as EditText
    var login_editText_password = findViewById(R.id.login_editText_password) as EditText
    var login_btn_connexion = findViewById(R.id.login_btn_connexion) as Button
    var login_btn_creation_account = findViewById(R.id.login_btn_creation_account) as Button


    login_btn_creation_account.setOnClickListener {
      val intent = Intent(applicationContext, CreateAccountActivity::class.java)
      startActivity(intent)
    }




  }
}

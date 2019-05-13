package com.example.bonni.ben.House

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.bonni.ben.R
import android.content.Intent

public open class HouseActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_house)

    var house_create = findViewById(R.id.house_create) as Button
    var house_join = findViewById(R.id.house_join) as Button


    val token=intent.getStringExtra("token")




    house_create.setOnClickListener {
      val intent_house_create_name = Intent (applicationContext, HouseCreateNameActivity::class.java)
      intent_house_create_name.putExtra("token", token)
      startActivity(intent_house_create_name)
    }



  }


}

package com.example.bonni.ben.House

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.bonni.ben.R
import android.content.Intent
import com.example.bonni.ben.House.CreateHouse.HouseCreateNameActivity
import com.example.bonni.ben.House.JoinHouse.HouseJoinActivity
import kotlinx.android.synthetic.main.activity_house_join.*

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

    house_join.setOnClickListener {
      val intent_house_join = Intent (applicationContext, HouseJoinActivity::class.java)
      intent_house_join.putExtra("token", token)
      startActivity(intent_house_join)
    }




  }


}

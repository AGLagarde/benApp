package com.example.bonni.ben.NavigationBottomBar.Fragment.Task

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.bonni.ben.Authentication.BenAPI
import com.example.bonni.ben.R
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.authentication
import kotlinx.android.synthetic.main.activity_task_create_name.*
import kotlinx.android.synthetic.main.activity_task_list_create_name.*
import java.nio.charset.Charset

class TaskCreateNameActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_task_create_name)

    val sharedPreferences = this.getSharedPreferences("users_token", MODE_PRIVATE)
    val myToken = sharedPreferences?.getString("token", "token")

    val mission_id = this.getSharedPreferences("mission_id", MODE_PRIVATE)

    val my_mission_id = mission_id?.getString("mission_id", "mission_id")

    var task_create_name_editText_task = task_create_name_editText_task.text

    fun create_task_name(task_name: String) {

      if (myToken != null) {
        Fuel.post("${BenAPI.base_url}" + "${BenAPI.api_create_task}" + "$my_mission_id")
          .header("Content-Type" to "application/json")
          .authentication()
          .bearer(myToken)
          .body(task_name, Charset.defaultCharset())
          .responseString { request, response, result ->
            when (result) {
              is com.github.kittinunf.result.Result.Success -> {
                this.runOnUiThread {
                  this.finish()
                }

              }
              is com.github.kittinunf.result.Result.Failure -> {
                Log.d("okay", "failure")
              }
            }
          }
      }


    }

    task_create_name_btn_task.setOnClickListener {
      create_task_name("{ \"task\":{ \"name\": \"$task_create_name_editText_task\"} }")

    }

    create_task_arrow_back.setOnClickListener {
      super.onBackPressed()
    }



  }
}

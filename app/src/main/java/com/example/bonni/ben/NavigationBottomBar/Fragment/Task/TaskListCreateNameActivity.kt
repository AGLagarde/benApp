package com.example.bonni.ben.NavigationBottomBar.Fragment.Task


import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.bonni.ben.Authentication.BenAPI
import com.example.bonni.ben.House.HouseActivity
import com.example.bonni.ben.MainActivity
import com.example.bonni.ben.R
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.authentication
import kotlinx.android.synthetic.main.activity_task_list_create_name.*
import java.nio.charset.Charset



class TaskListCreateNameActivity : AppCompatActivity() {



  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_task_list_create_name)



    val sharedPreferences = this.getSharedPreferences("users_token", AppCompatActivity.MODE_PRIVATE)
    val myToken = sharedPreferences?.getString("token", "token")


    var task_create_name_editText = task_create_name_editText.text
    var task_create_name_btn = findViewById(R.id.task_create_name_btn) as Button
    var create_taskList_arrow_back = findViewById(R.id.create_taskList_arrow_back) as ImageView

    fun create_task_list_name(task_list_name: String) {

      if (myToken != null) {
        Fuel.post("${BenAPI.base_url}" + "${BenAPI.api_create_task_lists}")
          .header("Content-Type" to "application/json")
          .authentication()
          .bearer(myToken)
          .body(task_list_name, Charset.defaultCharset())
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




    task_create_name_btn.setOnClickListener {
      create_task_list_name("{ \"taskList\":{ \"name\": \"$task_create_name_editText\"} }")

    }

    create_taskList_arrow_back.setOnClickListener {
      super.onBackPressed()
    }






  }
}

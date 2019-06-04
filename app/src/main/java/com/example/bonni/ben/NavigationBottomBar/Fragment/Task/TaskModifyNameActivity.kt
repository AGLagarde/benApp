package com.example.bonni.ben.NavigationBottomBar.Fragment.Task

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.example.bonni.ben.Authentication.BenAPI
import com.example.bonni.ben.R
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.core.extensions.authentication
import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.result.Result
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_task_modify_name.*
import java.nio.charset.Charset

class TaskModifyNameActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_task_modify_name)

    val task_id = this.getSharedPreferences("task_id", MODE_PRIVATE)

    val myTask_id = task_id?.getString("task_id", "task_id")


    var task_modify_name_editText_task = task_modify_name_editText_task.text

    val task_modify_arrow_back = findViewById(R.id.task_modify_arrow_back) as ImageView


    fun modifyTask(modified : String) {
      val sharedPreferences = this.getSharedPreferences("users_token", MODE_PRIVATE)
      val myToken = sharedPreferences?.getString("token", "token")

      if (myToken != null) {
        Fuel.put("${BenAPI.base_url}" + "${BenAPI.api_modify_task}"+ "$myTask_id")
          .header("Content-Type" to "application/json")
          .authentication()
          .bearer(myToken)
          .body(modified, Charset.defaultCharset())
          .responseObject { request: Request, response: Response, result: Result<JsonObject, FuelError> ->
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


    task_modify_name_btn_task.setOnClickListener {
      modifyTask("{ \"task\":{ \"name\": \"$task_modify_name_editText_task\"} }")
    }



    task_modify_arrow_back.setOnClickListener {
      super.onBackPressed()
    }
  }

}

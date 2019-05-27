package com.example.bonni.ben.NavigationBottomBar.Fragment.Task

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ImageView
import com.example.bonni.ben.Authentication.BenAPI
import com.example.bonni.ben.DataClass.Task
import com.example.bonni.ben.DataClass.TaskLists
import com.example.bonni.ben.NavigationBottomBar.Fragment.Adapter.ItemMissionAdapter
import com.example.bonni.ben.NavigationBottomBar.Fragment.Adapter.ItemTaskAdapter
import com.example.bonni.ben.R
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.core.extensions.authentication
import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.result.Result
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_tasks_of_task_list.*

class TasksOfTaskListActivity : AppCompatActivity() {


  var task_list_tasks = ArrayList<Task>()





  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_tasks_of_task_list)


    var task_floating_btn_add_task = findViewById(R.id.task_floating_btn_add_task) as ImageView

    getAllTasks()

  }
  fun getAllTasks() {

    val sharedPreferences = this.getSharedPreferences("users_token", MODE_PRIVATE)

    val myToken = sharedPreferences?.getString("token", "token")

    val mission_id = this.getSharedPreferences("mission_id", MODE_PRIVATE)

    val my_mission_id = mission_id?.getString("mission_id", "mission_id")



    if (myToken != null) {
      Fuel.get("${BenAPI.base_url}" + "${BenAPI.api_tasks_of_taskList}"+ "$my_mission_id")
        .header("Content-Type" to "application/json")
        .authentication()
        .bearer(myToken)
        .responseObject { request: Request, response: Response, result: Result<JsonObject, FuelError> ->
          when (result) {
            is com.github.kittinunf.result.Result.Success -> {

              val myResult = result

              val tasks = (((myResult.value).get("data")as JsonObject).get("taskList")as JsonObject).get("tasks").toString()
              val gson = GsonBuilder().setPrettyPrinting().create()
              val packagesArray = gson.fromJson(tasks , Array<Task>::class.java).toList()
              this.runOnUiThread(Runnable {

                task_list_tasks.clear()

                packagesArray?.forEach { myTasks : Task ->
                  task_list_tasks.add(myTasks)

                  task_rv_tasks.layoutManager = LinearLayoutManager(this)

                  val adapter = ItemTaskAdapter(task_list_tasks)

                  task_rv_tasks.adapter = adapter

                }
              })
              task_floating_btn_add_task.setOnClickListener {
                val intent_task_create_name = Intent(this, TaskCreateNameActivity::class.java)
                startActivity(intent_task_create_name)

              }

            }
            is com.github.kittinunf.result.Result.Failure -> {
              Log.d("okay", "failure")
            }
          }
        }
    }


  }

  // Méthode appelée à chaque fois que le fragment ré-apparaît
  override fun onResume() {
    super.onResume()

    getAllTasks()
  }


}

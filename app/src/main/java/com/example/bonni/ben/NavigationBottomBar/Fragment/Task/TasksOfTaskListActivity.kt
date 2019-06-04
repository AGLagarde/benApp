package com.example.bonni.ben.NavigationBottomBar.Fragment.Task

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.example.bonni.ben.Authentication.BenAPI
import com.example.bonni.ben.DataClass.Task
import com.example.bonni.ben.DataClass.TaskLists
import com.example.bonni.ben.NavigationBottomBar.Fragment.Adapter.ItemMissionAdapter
import com.example.bonni.ben.NavigationBottomBar.Fragment.Adapter.ItemTaskAdapter
import com.example.bonni.ben.NavigationBottomBar.Fragment.Adapter.ItemTaskAdapterListener
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
import kotlinx.android.synthetic.main.task_list_item.*

class TasksOfTaskListActivity : AppCompatActivity(), ItemTaskAdapterListener {
  override fun onDelete(position: Int) {
    // delete remotely (call api DELETE)
    // si succès => refaire l'adaptateur (our que le recyclerview se mette à jour) refreshUI { getAllTasks }

    val task = task_list_tasks[position]
    deleteTask(task.id)
  }


  var task_list_tasks = ArrayList<Task>()

  lateinit var adapter: ItemTaskAdapter


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_tasks_of_task_list)

    adapter = ItemTaskAdapter(task_list_tasks, this)


    var task_floating_btn_add_task = findViewById(R.id.task_floating_btn_add_task) as ImageView

    getAllTasks()

    task_floating_btn_add_task.setOnClickListener {
      val intent_task_create_name = Intent(this, TaskCreateNameActivity::class.java)
      startActivity(intent_task_create_name)

    }


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

                packagesArray?.forEach { myTask : Task ->
                  task_list_tasks.add(myTask)

                  task_rv_tasks.layoutManager = LinearLayoutManager(this)

                  task_rv_tasks.adapter = adapter

                }
              })

            }
            is com.github.kittinunf.result.Result.Failure -> {
              Log.d("okay", "failure")
            }
          }
        }
    }


  }

  fun deleteTask(task_id : Int) {
    val sharedPreferences = this.getSharedPreferences("users_token", MODE_PRIVATE)
    val myToken = sharedPreferences?.getString("token", "token")

    if (myToken != null) {
      Fuel.delete("${BenAPI.base_url}" + "${BenAPI.api_delete_task}"+ task_id)
        .header("Content-Type" to "application/json")
        .authentication()
        .bearer(myToken)
        .responseObject { request: Request, response: Response, result: Result<JsonObject, FuelError> ->
          when (result) {
            is com.github.kittinunf.result.Result.Success -> {
              this.runOnUiThread {
                getAllTasks()
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

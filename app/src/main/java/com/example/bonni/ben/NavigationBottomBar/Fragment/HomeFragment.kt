package com.example.bonni.ben.NavigationBottomBar.Fragment


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.bonni.ben.Authentication.BenAPI
import com.example.bonni.ben.DataClass.Task
import com.example.bonni.ben.DataClass.TaskLists
import com.example.bonni.ben.R
import com.example.bonni.ben.DataClass.User
import com.example.bonni.ben.NavigationBottomBar.Fragment.Adapter.ItemHomeAdapter
import com.example.bonni.ben.NavigationBottomBar.Fragment.Adapter.ItemMissionAdapter
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.core.extensions.authentication
import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.result.Result
import com.google.gson.*
import kotlinx.android.synthetic.main.activity_tasks_of_task_list.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_task.*
import kotlinx.android.synthetic.main.home_list_item.*
import org.json.JSONArray
import org.json.JSONObject


class HomeFragment : Fragment() {



  companion object {


    fun newInstance(): HomeFragment {
      val homeFragment = HomeFragment()

      return homeFragment
    }
  }

  var list_task = ArrayList<Task>()

  val adapter = ItemHomeAdapter(list_task)

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view : View = inflater.inflate(R.layout.fragment_home, container, false)

    getAllMyTasks()

    val sharedPreferences_name = activity?.getSharedPreferences("user_name", AppCompatActivity.MODE_PRIVATE)

    val myName = sharedPreferences_name?.getString("user_name", "user_name")


    val id_firstname = view.findViewById(R.id.id_firstname) as TextView

    id_firstname.text = "Salut $myName"




    return view
  }

  fun getAllMyTasks() {

    val sharedPreferences = activity?.getSharedPreferences("users_token", AppCompatActivity.MODE_PRIVATE)

    val myToken = sharedPreferences?.getString("token", "token")


    if (myToken != null) {
      Fuel.get("${BenAPI.base_url}" + "${BenAPI.api_my_task_lists}")
        .header("Content-Type" to "application/json")
        .authentication()
        .bearer(myToken)
        .responseObject { request: Request, response: Response, result: Result<JsonObject, FuelError> ->
          when (result) {
            is Result.Success -> {

              val myResult = result
              var task_string = ""
              val tasks = ((myResult.value).get("data")as JsonObject)
              val tasksArray = tasks.getAsJsonArray("taskLists")
              for(myTasks in tasksArray)
              {
                val obj = myTasks.asJsonObject
                task_string = obj.get("tasks").toString()
              }

              val gson = GsonBuilder().setPrettyPrinting().create()
              val packagesArray = gson.fromJson(task_string , Array<Task>::class.java).toList()
              activity?.runOnUiThread(Runnable {

                list_task.clear()
                
                packagesArray?.forEach { myTask : Task ->
                  list_task.add(myTask)

                  home_rv_task.layoutManager = LinearLayoutManager(activity)

                  home_rv_task.adapter = adapter

                }
              })
            }
            is Result.Failure -> {
              Log.d("okay", "failure")
            }
          }
        }
    }


  }

}

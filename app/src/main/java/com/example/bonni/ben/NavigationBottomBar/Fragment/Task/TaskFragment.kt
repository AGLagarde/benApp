package com.example.bonni.ben.NavigationBottomBar.Fragment.Task


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bonni.ben.Authentication.BenAPI
import com.example.bonni.ben.MainActivity
import com.example.bonni.ben.R
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.authentication
import kotlinx.android.synthetic.main.fragment_task.*
import java.nio.charset.Charset
import android.preference.PreferenceManager
import android.content.SharedPreferences
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.widget.Adapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import com.beust.klaxon.Json
import com.example.bonni.ben.DataClass.TaskLists
import com.example.bonni.ben.House.HouseActivity
import com.example.bonni.ben.NavigationBottomBar.Fragment.Adapter.ItemMissionAdapter
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.core.response
import com.github.kittinunf.fuel.gson.gsonDeserializer
import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.result.Result
import com.google.gson.*
import org.json.JSONStringer
import kotlin.math.log




open class TaskFragment : Fragment()  {

  companion object {

    fun newInstance(): TaskFragment {

      return TaskFragment()

    }
  }


  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      val view : View = inflater.inflate(R.layout.fragment_task, container, false)

    val sharedPreferences = activity?.getSharedPreferences("users_token", AppCompatActivity.MODE_PRIVATE)
    val myToken = sharedPreferences?.getString("token", "token")


    var task_rv_mission = view.findViewById(com.example.bonni.ben.R.id.task_rv_mission) as RecyclerView

    var task_floating_btn_add_mission = view.findViewById(com.example.bonni.ben.R.id.task_floating_btn_add_mission) as FloatingActionButton



    var task_lists = ArrayList<TaskLists>()

    fun getAllMissions() {

      if (myToken != null) {
        Fuel.get("${BenAPI.base_url}" + "${BenAPI.api_task_lists}")
          .header("Content-Type" to "application/json")
          .authentication()
          .bearer(myToken)
          .responseObject { request: Request, response: Response, result: Result<JsonObject, FuelError> ->
            when (result) {
              is com.github.kittinunf.result.Result.Success -> {

                val myResponse = response
                val myResult = result

                val tasks = ((myResult.value).get("data")as JsonObject).get("taskLists").toString()
                val gson = GsonBuilder().setPrettyPrinting().create()
                val packagesArray = gson.fromJson(tasks , Array<TaskLists>::class.java).toList()
                activity?.runOnUiThread(Runnable {
                  packagesArray?.forEach { myTasks : TaskLists ->
                    task_lists.add(myTasks)
                    task_rv_mission.layoutManager = LinearLayoutManager(activity)

                    val adapter = ItemMissionAdapter(task_lists)

                    task_rv_mission.adapter = adapter

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


    getAllMissions()



    task_floating_btn_add_mission.setOnClickListener {

      val intent_task_create_name = Intent(activity, TaskListCreateNameActivity::class.java)
        startActivity(intent_task_create_name)

    }




    return view


  }



}



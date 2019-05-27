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
import android.widget.*
import com.beust.klaxon.Json
import com.example.bonni.ben.Authentication.LoginActivity
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




class TaskFragment : Fragment()  {

  companion object {

    fun newInstance(): TaskFragment {

      return TaskFragment()

    }
  }

  var task_lists = ArrayList<TaskLists>()

  val adapter = ItemMissionAdapter(task_lists, { item : TaskLists -> itemClicked(item)})

  var mission_id = 0


  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view : View = inflater.inflate(R.layout.fragment_task, container, false)


    val task_floating_btn_add_mission = view.findViewById(R.id.task_floating_btn_add_mission) as ImageView
    getAllMissions()

    task_floating_btn_add_mission.setOnClickListener {
      val intent_create_name_taskList = Intent(activity, TaskListCreateNameActivity::class.java)
      startActivity(intent_create_name_taskList)
    }




    return view
  }


  // Laisser cette méthode en dehors de la méthode onCreateView

  fun getAllMissions() {

    val sharedPreferences = activity?.getSharedPreferences("users_token", AppCompatActivity.MODE_PRIVATE)

    val myToken = sharedPreferences?.getString("token", "token")


    if (myToken != null) {
      Fuel.get("${BenAPI.base_url}" + "${BenAPI.api_task_lists}")
        .header("Content-Type" to "application/json")
        .authentication()
        .bearer(myToken)
        .responseObject { request: Request, response: Response, result: Result<JsonObject, FuelError> ->
          when (result) {
            is com.github.kittinunf.result.Result.Success -> {

              val myResult = result

              val tasks = ((myResult.value).get("data")as JsonObject).get("taskLists").toString()
              val gson = GsonBuilder().setPrettyPrinting().create()
              val packagesArray = gson.fromJson(tasks , Array<TaskLists>::class.java).toList()
              activity?.runOnUiThread(Runnable {

                // nettoyage de la liste locale pour ne pas avoir de doublons
                task_lists.clear()

                packagesArray?.forEach { myTasks  ->
                  task_lists.add(myTasks)

                  task_rv_mission.layoutManager = LinearLayoutManager(activity)


                  task_rv_mission.adapter = adapter


                  mission_id = myTasks.id
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

  fun itemClicked(item : TaskLists) {
    // Launch second activity, pass part ID as string parameter
    val showDetailActivityIntent = Intent(activity, TasksOfTaskListActivity::class.java)
    startActivity(showDetailActivityIntent)

    val mission_id = activity?.getSharedPreferences("mission_id", AppCompatActivity.MODE_PRIVATE);
    mission_id?.edit()
      ?.putString("mission_id", item.id.toString())
      ?.apply()
  }


  // Méthode appelée à chaque fois que le fragment ré-apparaît
  override fun onResume() {
    super.onResume()

    getAllMissions()
  }

}



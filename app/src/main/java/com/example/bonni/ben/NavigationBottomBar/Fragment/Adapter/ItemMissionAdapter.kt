package com.example.bonni.ben.NavigationBottomBar.Fragment.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.bonni.ben.DataClass.TaskLists
import com.example.bonni.ben.R
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.mission_list_item.view.*

class ItemMissionAdapter(val itemsMissions : ArrayList<TaskLists>, val clickListener: (TaskLists) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


  // Gets the number of animals in the list
  override fun getItemCount(): Int {
    return itemsMissions.size
  }

  // Inflates the item views
  override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return TaskListViewHolder(LayoutInflater.from(p0?.context).inflate(R.layout.mission_list_item, p0,false))
  }

  // Binds each animal in the ArrayList to a view
  override fun onBindViewHolder(p0: RecyclerView.ViewHolder, position: Int) {
    (p0 as TaskListViewHolder).bind(itemsMissions[position], clickListener)
  }

  class TaskListViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    // Holds the TextView that will add each animal to
    fun bind(list: TaskLists, clickListener: (TaskLists) -> Unit) {
      itemView.task_tv_mission.text = list.name
      itemView.setOnClickListener { clickListener(list)}
    }
  }
}




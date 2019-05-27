package com.example.bonni.ben.NavigationBottomBar.Fragment.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.bonni.ben.DataClass.Task
import com.example.bonni.ben.DataClass.TaskLists
import com.example.bonni.ben.R
import kotlinx.android.synthetic.main.mission_list_item.view.*
import kotlinx.android.synthetic.main.task_list_item.view.*

class ItemTaskAdapter(val itemTask : ArrayList<Task>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  override fun getItemCount(): Int {
    return itemTask.size
  }

  override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
    return TaskViewHolder(LayoutInflater.from(p0?.context).inflate(R.layout.task_list_item, p0,false))
  }

  override fun onBindViewHolder(p0: RecyclerView.ViewHolder, position: Int) {
    (p0 as TaskViewHolder).bind(itemTask[position])
  }

  class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(task: Task) {
      itemView.task_tv_task.text = task.name
    }
  }

}



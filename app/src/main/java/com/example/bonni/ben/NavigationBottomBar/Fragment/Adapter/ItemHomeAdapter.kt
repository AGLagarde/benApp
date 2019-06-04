package com.example.bonni.ben.NavigationBottomBar.Fragment.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bonni.ben.DataClass.Task
import com.example.bonni.ben.DataClass.TaskLists
import com.example.bonni.ben.R
import kotlinx.android.synthetic.main.home_list_item.view.*
import kotlinx.android.synthetic.main.mission_list_item.view.*

class ItemHomeAdapter(val itemsTask : ArrayList<Task>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


  override fun getItemCount(): Int {
    return itemsTask.size
  }

  // Inflates the item views
  override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return HomeViewHolder(LayoutInflater.from(p0?.context).inflate(R.layout.home_list_item, p0,false))
  }

  override fun onBindViewHolder(p0: RecyclerView.ViewHolder, position: Int) {
    (p0 as HomeViewHolder).bind(itemsTask[position])
  }

  class HomeViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(task: Task) {
      itemView.home_tv_task.text = task.name
    }
  }
}

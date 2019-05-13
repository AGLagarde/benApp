package com.example.bonni.ben.NavigationBottomBar.Fragment.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bonni.ben.R
import kotlinx.android.synthetic.main.mission_list_item.view.*

class AnimalAdapter(val items : ArrayList<String>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

  // Gets the number of animals in the list
  override fun getItemCount(): Int {
    return items.size
  }

  // Inflates the item views
  override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(LayoutInflater.from(context).inflate(R.layout.mission_list_item, p0,false))
  }

  // Binds each animal in the ArrayList to a view
  override fun onBindViewHolder(p0: ViewHolder, position: Int) {
    p0?.tvTask?.text = items.get(position)
  }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
  // Holds the TextView that will add each animal to
  val tvTask = view.task_tv_mission
}

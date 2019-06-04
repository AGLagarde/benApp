package com.example.bonni.ben.NavigationBottomBar.Fragment.Adapter

import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.example.bonni.ben.DataClass.Task
import com.example.bonni.ben.NavigationBottomBar.Fragment.Task.TaskModifyNameActivity
import com.example.bonni.ben.NavigationBottomBar.Fragment.Task.TasksOfTaskListActivity
import com.example.bonni.ben.R
import kotlinx.android.synthetic.main.task_list_item.view.*

interface ItemTaskAdapterListener {
  fun onDelete(position: Int)
}

class ItemTaskAdapter(val itemTask : ArrayList<Task>, val listener: ItemTaskAdapterListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  override fun getItemCount(): Int {
    return itemTask.size
  }

  override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
    return TaskViewHolder(LayoutInflater.from(p0?.context).inflate(R.layout.task_list_item, p0,false), itemTask[p1], listener)
  }

  override fun onBindViewHolder(p0: RecyclerView.ViewHolder, position: Int) {
    (p0 as TaskViewHolder).bind(itemTask[position])
  }

  class TaskViewHolder(itemView: View, val task : Task, val listener: ItemTaskAdapterListener) : RecyclerView.ViewHolder(itemView) {
    fun bind(list: Task) {
      itemView.task_tv_task.text = list.name
      itemView.point_task.setOnClickListener {
        val context = itemView.context

        var popup: PopupMenu? = null;
        popup = PopupMenu(context, itemView.point_task)
        popup.inflate(R.menu.option_menu)

        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->

          when (item!!.itemId) {
            R.id.action_modify -> {
              val modifyActivity = Intent(context, TaskModifyNameActivity::class.java)
              val task_id = task.id
              val task_id_shared = context?.getSharedPreferences("task_id", AppCompatActivity.MODE_PRIVATE);
              task_id_shared?.edit()
                ?.putString("task_id", task_id.toString())
                ?.apply()
              startActivity(context, modifyActivity, null)
            }
            R.id.action_delete -> {
              listener.onDelete(this.adapterPosition)
            }
          }

          true
        })

        popup.show()
      }
    }
  }

}



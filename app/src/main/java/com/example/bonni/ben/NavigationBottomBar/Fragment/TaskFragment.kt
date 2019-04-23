package com.example.bonni.ben.NavigationBottomBar.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bonni.ben.R


class TaskFragment : Fragment() {
  companion object {

    fun newInstance(): TaskFragment {
      return TaskFragment()
    }
  }


  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater?.inflate(R.layout.fragment_task, container, false)
  }
}

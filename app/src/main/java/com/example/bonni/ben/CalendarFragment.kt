package com.example.bonni.ben


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class CalendarFragment : Fragment() {
  companion object {

    fun newInstance(): CalendarFragment {
      return CalendarFragment()
    }
  }


  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater?.inflate(R.layout.fragment_calendar, container, false)
  }
}

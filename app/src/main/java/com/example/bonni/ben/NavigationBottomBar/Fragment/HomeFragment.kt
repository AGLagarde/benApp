package com.example.bonni.ben.NavigationBottomBar.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.bonni.ben.R
import com.example.bonni.ben.DataClass.User


class HomeFragment : Fragment() {
  companion object {


    fun newInstance(): HomeFragment {
      val homeFragment = HomeFragment()

      return homeFragment
    }
  }


  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater?.inflate(R.layout.fragment_home, container, false)


  }

}

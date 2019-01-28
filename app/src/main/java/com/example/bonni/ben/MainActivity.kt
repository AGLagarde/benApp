package com.example.bonni.ben

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.TextView
import android.support.design.internal.BottomNavigationItemView
import java.lang.reflect.AccessibleObject.setAccessible
import java.lang.reflect.Array.setBoolean
import android.support.design.internal.BottomNavigationMenuView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log
import android.widget.Toolbar


class MainActivity : AppCompatActivity() {

  val homeFragment = HomeFragment.newInstance()

  override fun onCreate(savedInstanceState: Bundle?) {

    //lateinit var toolbar: ActionBar
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    openFragment(homeFragment)

    //toolbar = supportActionBar!!
    val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationView)

    bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
  }



  private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

    when (item.itemId) {
      R.id.navigation_home -> {
        openFragment(homeFragment)
        return@OnNavigationItemSelectedListener true
      }
      R.id.navigation_task -> {
        val taskFragment = TaskFragment.newInstance()
        openFragment(taskFragment)
        return@OnNavigationItemSelectedListener true
      }
      R.id.navigation_calendar -> {
        val calendarFragment = CalendarFragment.newInstance()
        openFragment(calendarFragment)
        return@OnNavigationItemSelectedListener true
      }
      R.id.navigation_notification -> {
        val notificationFragment = NotificationFragment.newInstance()
        openFragment(notificationFragment)
        return@OnNavigationItemSelectedListener true
      }
      R.id.navigation_profil -> {
        val profileFragment = ProfileFragment.newInstance()
        openFragment(profileFragment)
        return@OnNavigationItemSelectedListener true
      }
    }
    false
  }

  private fun openFragment(fragment: Fragment) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.replace(R.id.container, fragment)
    transaction.addToBackStack(null)
    transaction.commit()
  }


}

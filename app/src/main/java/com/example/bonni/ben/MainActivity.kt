package com.example.bonni.ben

import android.os.Bundle
import android.os.Parcel
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v4.app.Fragment
import com.example.bonni.ben.DataClass.User
import com.example.bonni.ben.NavigationBottomBar.Fragment.*


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
      R.id.navigation_like -> {
        val likeFragment = LikeFragment.newInstance()
        openFragment(likeFragment)
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

package com.example.bonni.ben

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v4.app.Fragment
import com.example.bonni.ben.NavigationBottomBar.Fragment.*
import com.example.bonni.ben.NavigationBottomBar.Fragment.Task.TaskFragment


open class MainActivity : AppCompatActivity() {

  val homeFragment = HomeFragment.newInstance()

  override fun onCreate(savedInstanceState: Bundle?) {

    //lateinit var toolbar: ActionBar
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    openFragment(homeFragment)

    val token_string=intent.getStringExtra("token")
    val user_name=intent.getStringExtra("user")



    val sharedPreferences = this.getSharedPreferences("users_token", MODE_PRIVATE);
    sharedPreferences.edit()
      .putString("token", token_string)
      .apply()


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

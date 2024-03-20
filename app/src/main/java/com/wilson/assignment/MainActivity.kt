package com.wilson.assignment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    val fragments = arrayOf(QuizzesFragment(), NotificationsFragment(), UserProfileFragment())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tabs = findViewById<ViewPager2>(R.id.tabs)
        val menu = findViewById<BottomNavigationView>(R.id.menu)

        tabs.adapter = object: FragmentStateAdapter(this) {
            override fun createFragment(position: Int) = fragments[position]
            override fun getItemCount() = 3
        }

        tabs.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                menu.menu.getItem(position).setChecked(true)
            }
        })

        menu.setOnItemSelectedListener {
            tabs.setCurrentItem(when (it.itemId) {
                R.id.quizzes -> 0
                R.id.notifications -> 1
                R.id.user_profile -> 2
                else -> 0
            })
            true
        }

        tabs.setCurrentItem(0)
    }
}
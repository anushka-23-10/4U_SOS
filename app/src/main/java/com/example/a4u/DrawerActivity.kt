package com.example.a4u

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.a4u.databinding.ActivityDrawerBinding
import com.google.android.material.navigation.NavigationView

class DrawerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDrawerBinding
    private lateinit var drawerButton: ImageButton
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = findViewById(R.id.DrawerLayout)
        drawerButton = findViewById(R.id.imgBtnDrawer)
        navigationView = findViewById(R.id.navigationView)

        drawerButton.setOnClickListener {
            drawerLayout.open()
        }


        navigationView.setNavigationItemSelectedListener { item: MenuItem ->
            var itemId = item.itemId
            item.isChecked = true

            when (itemId) {
                R.id.itContacts -> {
                    replaceFragment(EmergencyConatctsFragment())
                }
                R.id.itDashboard -> {
                    TODO("How to navigate back to dashboard")
//                    replaceFragment()
                }
                R.id.itHelpline -> {
                    replaceFragment(HelplinesFragment())
                }
                R.id.itLiveLocation -> {
                    replaceFragment(LiveLocationFragment())
                }
                R.id.itAbout -> {
                    replaceFragment(AboutAppFragment())
                }
                R.id.itAreaAlerts -> {
                    replaceFragment(AreaAlertsFragment())
                }
                R.id.itLogout -> {
                    TODO("implement logout facility")
                }
            }

            drawerLayout.close()
            true
        }
    }

    private fun replaceFragment(fragment : Fragment){
        var fragmentManager = supportFragmentManager
        var fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout, fragment)
        fragmentTransaction.commit()
    }
}
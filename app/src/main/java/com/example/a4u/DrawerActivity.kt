package com.example.a4u

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
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

            when (itemId) {
                R.id.itContacts -> {
                    Toast.makeText(this, "Contacts", Toast.LENGTH_SHORT).show()
                }
                R.id.itDashboard -> {
                    Toast.makeText(this, "Dashboard", Toast.LENGTH_SHORT).show()
                }
                R.id.itLiveLocation -> {
                    Toast.makeText(this, "Live Location", Toast.LENGTH_SHORT).show()
                }
            }

            drawerLayout.close()
            true
        }
    }
}
package com.example.a4u

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.a4u.databinding.ActivityLoginPageBinding

class LoginPage : AppCompatActivity() {
    private lateinit var binding: ActivityLoginPageBinding
    private lateinit var btnLogin: Button
    private lateinit var btnCreateAccount: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        btnLogin = findViewById(R.id.btnLogin)
        btnCreateAccount = findViewById(R.id.btnCreateAccount)


        btnLogin.setOnClickListener {
            var intent = Intent(this, DrawerActivity::class.java)
            startActivity(intent)
        }

        btnCreateAccount.setOnClickListener {
            var intent = Intent(this, PhoneAuthentication::class.java)
            startActivity(intent)
        }

    }
}
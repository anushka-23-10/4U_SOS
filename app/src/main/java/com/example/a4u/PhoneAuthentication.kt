package com.example.a4u

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a4u.databinding.ActivityPhoneAuthenticationBinding

class PhoneAuthentication : AppCompatActivity() {
    private lateinit var binding : ActivityPhoneAuthenticationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
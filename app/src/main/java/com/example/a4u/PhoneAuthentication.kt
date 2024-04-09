package com.example.a4u

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.example.a4u.databinding.ActivityPhoneAuthenticationBinding
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.safetynet.SafetyNet
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.hbb20.CountryCodePicker
import java.util.concurrent.TimeUnit

class PhoneAuthentication : AppCompatActivity() {
    private lateinit var binding: ActivityPhoneAuthenticationBinding
    private lateinit var mAuth : FirebaseAuth
    private lateinit var combinedPhoneNumber: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        binding = ActivityPhoneAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var nextButton = findViewById<Button>(R.id.btnNextPhone)
        var mobileNumber = findViewById<EditText>(R.id.etEnterPhoneNumber)
        var sendOTPBtn = findViewById<Button>(R.id.btnSendOTP)
        var countryCode = findViewById<CountryCodePicker>(R.id.countryCodePicker)
        var otp = findViewById<EditText>(R.id.etEnterOTP)
        var verificationCode: String = "000000"
        var resendingToken: PhoneAuthProvider.ForceResendingToken = PhoneAuthProvider.ForceResendingToken.zza()
        mAuth = FirebaseAuth.getInstance()
        val timeOut = 60L

        nextButton.setOnClickListener {
//            goToNextActivity()
            var enteredOTP = otp.text.toString()
            var phoneAuthCredential = PhoneAuthProvider.getCredential(verificationCode ,enteredOTP)
            signIn(phoneAuthCredential)
        }

        countryCode.registerCarrierNumberEditText(mobileNumber)

        fun sendOTP(phoneNumber: String, isResend: Boolean) {

            val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    goToNextActivity()
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    showToast(this@PhoneAuthentication, "Invalid OTP", false)
                }

                override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                    super.onCodeSent(p0, p1)
                    verificationCode = p0
                    resendingToken = p1
                    showToast(this@PhoneAuthentication, "OTP sent successfully", false)
                }

            }
            var builder = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(timeOut, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(callbacks)

            if(isResend){
                PhoneAuthProvider.verifyPhoneNumber(builder.setForceResendingToken(resendingToken).build())
            }else{
                PhoneAuthProvider.verifyPhoneNumber(builder.build())
            }
        }

        sendOTPBtn.setOnClickListener {
            combinedPhoneNumber = countryCode.selectedCountryCodeWithPlus + mobileNumber.text.toString()
            if (!countryCode.isValidFullNumber) {
                mobileNumber.error = "Invalid Mobile number"
            } else{
                onClick()
                sendOTP(combinedPhoneNumber, false)
            }
        }
    }

    private fun goToNextActivity() {
        combinedPhoneNumber = "123456789"
        val intent = Intent(this@PhoneAuthentication, CreateAccountPage::class.java)
        intent.putExtra("phone", combinedPhoneNumber)
        startActivity(intent)
    }

    private fun showToast(context: Context, s: String, length: Boolean) {
        if (length) {
            Toast.makeText(context, s, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
        }
    }

    private fun signIn(phoneAuthCredential: PhoneAuthCredential) {
        mAuth.signInWithCredential(phoneAuthCredential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    goToNextActivity()
                } else {
                    // Sign-in failed, display a message to the user.
                    showToast(this@PhoneAuthentication, "Authentication failed: ${task.exception?.message}", false)
                }
            }
    }


    private fun onClick() {
        SafetyNet.getClient(this).verifyWithRecaptcha("6LeuxbQpAAAAAOiDjpWYXLhA6vih6E5FsulDqlfJ")
            .addOnSuccessListener(ContextCompat.getMainExecutor(this), OnSuccessListener { response ->
                // Indicates communication with reCAPTCHA service was
                // successful.
//                val userResponseToken = response.tokenResult
                if (response.tokenResult?.isNotEmpty() == true) {
                    // Validate the user response token using the
                    // reCAPTCHA site verify API.
                }
            })
            .addOnFailureListener(ContextCompat.getMainExecutor(this), OnFailureListener { e ->
                if (e is ApiException) {
                    // An error occurred when communicating with the
                    // reCAPTCHA service. Refer to the status code to
                    // handle the error appropriately.
                    Log.d(ContentValues.TAG, "Error: ${CommonStatusCodes.getStatusCodeString(e.statusCode)}")
                } else {
                    // A different, unknown type of error occurred.
                    Log.d(ContentValues.TAG, "Error: ${e.message}")
                }
            })
    }
}
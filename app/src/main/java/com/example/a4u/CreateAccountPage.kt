package com.example.a4u

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.a4u.databinding.ActivityCreateAccountPageBinding
import java.util.Calendar

class CreateAccountPage : AppCompatActivity() {
    private lateinit var binding: ActivityCreateAccountPageBinding
    private lateinit var createAccountBtn : Button
    private lateinit var calendar : Calendar // Corrected variable name
    private var day : Int = 0
    private var month : Int = 0
    private var year : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createAccountBtn = findViewById(R.id.btnCreateAccountPage)
        calendar = Calendar.getInstance() // Corrected variable name

        createAccountBtn.setOnClickListener {

            val datePickerDialog = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    this.year = year
                    month = monthOfYear
                    day = dayOfMonth
                },
                calendar.get(Calendar.YEAR), // Corrected variable name
                calendar.get(Calendar.MONTH), // Corrected variable name
                calendar.get(Calendar.DAY_OF_MONTH) // Corrected variable name
            )

            datePickerDialog.show()
        }
    }
}

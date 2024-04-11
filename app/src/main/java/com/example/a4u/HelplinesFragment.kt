package com.example.a4u

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class HelplinesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_helplines, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        view?.findViewById<View>(R.id.btnDistress)?.setOnClickListener {
            callNumber("1091")
        }

        view?.findViewById<View>(R.id.btnAbuse)?.setOnClickListener {
            callNumber("181")
        }

        view?.findViewById<View>(R.id.btnPolice)?.setOnClickListener {
            callNumber("100")
        }

        view?.findViewById<View>(R.id.btnHelpline)?.setOnClickListener {
            callNumber("1098")
        }

        view?.findViewById<View>(R.id.btnAmbulance)?.setOnClickListener {
            callNumber("102")
        }
    }

    private fun callNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phoneNumber")
        startActivity(intent)
    }
}
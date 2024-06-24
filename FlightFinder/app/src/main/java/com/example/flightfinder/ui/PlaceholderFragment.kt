package com.example.flightfinder.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.flightfinder.R

class PlaceholderFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_placeholder, container, false)

        val goBackBtn = view.findViewById<ImageButton>(R.id.goBackBtn)
        goBackBtn.setOnClickListener {
            findNavController().navigateUp()
        }

        return view
    }
}

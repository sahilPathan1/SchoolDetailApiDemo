package com.example.schooldetailapidemo.ui.activity

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import com.example.schooldetailapidemo.R
import com.example.schooldetailapidemo.databinding.ActivitySelectRoalBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class SelectRoleActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectRoalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_select_roal)

        var chip1 = binding.chip1
        var chip2 = binding.chip2
        var chip3 = binding.chip3

        val selectedColor = ContextCompat.getColor(this, R.color.red)
        val unselectedColor = ContextCompat.getColor(this, R.color.black)

        chip1.setOnClickListener {
            Toast.makeText(this, "chip1", Toast.LENGTH_SHORT).show()

        }

        chip2.setOnClickListener {
            Toast.makeText(this, "chip2", Toast.LENGTH_SHORT).show()

        }

        chip3.setOnClickListener {
            Toast.makeText(this, "chip3", Toast.LENGTH_SHORT).show()
        }
    }
}
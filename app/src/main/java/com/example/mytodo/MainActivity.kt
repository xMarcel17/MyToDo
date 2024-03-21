package com.example.mytodo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mytodo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // Variable that connects the activity_main.xml with MainActivity class.
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the activity_main.xml and attach it to binding variable
        binding = ActivityMainBinding.inflate(layoutInflater)
        // set the contents on the screen with the binding variable
        setContentView(binding.root)
    }
}
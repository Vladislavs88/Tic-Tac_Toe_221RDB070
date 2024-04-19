package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.startactivity)

        val startButton = findViewById<Button>(R.id.startButton)
        startButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}

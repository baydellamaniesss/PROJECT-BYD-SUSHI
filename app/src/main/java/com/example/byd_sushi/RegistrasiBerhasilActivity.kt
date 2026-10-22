package com.example.byd_sushi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.byd_sushi.databinding.ActivityRegistrasiBerhasilBinding

class RegistrasiBerhasilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrasiBerhasilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrasiBerhasilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnKeLogin.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}
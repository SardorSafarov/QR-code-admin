package com.example.admin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.admin.R
import com.example.admin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        domenQush()
        userQush()
        userQayerda()
        userChat()
    }

    private fun userChat() {
        binding.userChatting.setOnClickListener {
            startActivity(Intent(this,UserChat::class.java))
        }

    }

    private fun userQayerda() {
        binding.userQayerda.setOnClickListener {
            startActivity(Intent(this,UserQayerda::class.java))
        }
    }

    private fun userQush() {
        binding.userQush.setOnClickListener {
            startActivity(Intent(this,UserAdd::class.java))
        }

    }

    private fun domenQush() {
        binding.domenQush.setOnClickListener {
            startActivity(Intent(this,DomenAdd::class.java))
        }

    }
}
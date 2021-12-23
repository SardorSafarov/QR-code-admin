package com.example.admin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.admin.databinding.ActivityUserChatBinding
import com.example.admin.databinding.ActivityUserQayerdaBinding

class UserChat : AppCompatActivity() {
    lateinit var binding: ActivityUserChatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        title="Foydalanuvchi bilan Chat"
        super.onCreate(savedInstanceState)
        binding= ActivityUserChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
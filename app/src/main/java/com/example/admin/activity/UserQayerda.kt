package com.example.admin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.admin.databinding.ActivityUserAddBinding
import com.example.admin.databinding.ActivityUserQayerdaBinding

class UserQayerda : AppCompatActivity() {
    lateinit var binding: ActivityUserQayerdaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        title="Foydalanuvchi qayerda?"
        super.onCreate(savedInstanceState)
        binding= ActivityUserQayerdaBinding.inflate(layoutInflater)
        setContentView(binding.root)
            //Sardor
    }
}
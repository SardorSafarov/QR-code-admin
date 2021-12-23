package com.example.admin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.admin.databinding.ActivityUserAddBinding
import com.example.admin.databinding.ActivityUserQayerdaBinding
import com.example.admin.viewModels.LocationViewModel

class UserQayerda : AppCompatActivity() {
    lateinit var binding: ActivityUserQayerdaBinding
    private val locationViewModel:LocationViewModel by lazy { ViewModelProviders.of(this).get(LocationViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        title="Foydalanuvchi qayerda?"
        super.onCreate(savedInstanceState)
        binding= ActivityUserQayerdaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        locationViewModel.
    }
}
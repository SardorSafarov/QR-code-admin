package com.example.admin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admin.adapter.LocationAdapter
import com.example.admin.databinding.ActivityUserAddBinding
import com.example.admin.databinding.ActivityUserQayerdaBinding
import com.example.admin.entity.Locationentity
import com.example.admin.viewModels.LocationViewModel

class UserQayerda : AppCompatActivity(),LocationAdapter.locationClick {

    lateinit var binding: ActivityUserQayerdaBinding

    private val locationViewModel:LocationViewModel by lazy { ViewModelProviders.of(this).get(LocationViewModel::class.java) }

    private val locationAdapter:LocationAdapter by lazy { LocationAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        title="Foydalanuvchi qayerda?"
        super.onCreate(savedInstanceState)
        binding= ActivityUserQayerdaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        locationViewModel.readLocation()
        showUser()
    }

    private fun showUser() {
        binding.locationUser.adapter=locationAdapter
        binding.locationUser.layoutManager=LinearLayoutManager(this)
        locationViewModel.location.observe(this, Observer {
            locationAdapter.setdata(it as MutableList<Locationentity>)
        })
    }

    override fun onclik(user: Locationentity) {
        d("sardor","keldii ${user.login}")
    }
}
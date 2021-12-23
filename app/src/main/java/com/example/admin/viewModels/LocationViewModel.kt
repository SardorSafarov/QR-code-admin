package com.example.admin.viewModels

import android.util.Log.d
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.admin.constants.Constants
import com.example.admin.entity.DomenEntity
import com.example.admin.entity.Locationentity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LocationViewModel:ViewModel() {

    private  val locationDb = FirebaseDatabase.getInstance().getReference(Constants.LOCATION)

    private val _Location = MutableLiveData<List<Locationentity>>()
    val location: LiveData<List<Locationentity>>
        get() = _Location

    fun insertLocation(locationentity: Locationentity) {

        locationDb.child(locationentity.login!!).setValue(locationentity)
            .addOnCompleteListener {
                if(it.isSuccessful)
                {
                   d("sardor","qo`shildi")
                }else
                {
                  d("sardor","nimadir bo`ldiii")
                }
            }
    }

    fun readLocation(){
        locationDb.addValueEventListener(object : ValueEventListener
        {
            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists())
                {
                    val items= mutableListOf<Locationentity>()
                    p0.children.forEach {
                        val item=it.getValue(Locationentity::class.java)
                        item?.login=it.key
                        item?.let { items.add(it) }
                    }
                    _Location.value = items
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }



}
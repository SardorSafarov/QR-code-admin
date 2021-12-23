package com.example.admin.viewModels

import android.util.Log.d
import androidx.lifecycle.ViewModel
import com.example.admin.constants.Constants
import com.example.admin.entity.Locationentity
import com.google.firebase.database.FirebaseDatabase

class LocationViewModel:ViewModel() {

    private  val locationDb = FirebaseDatabase.getInstance().getReference(Constants.LOCATION)


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

}
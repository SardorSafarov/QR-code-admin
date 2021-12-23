package com.example.admin.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.admin.constants.Constants
import com.example.admin.entity.User
import com.google.firebase.database.*

class UserViewModel : ViewModel() {

    private  val userDb = FirebaseDatabase.getInstance().getReference(Constants.USERS)

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _users




    private val _result = MutableLiveData<Exception?>()
    val result: LiveData<Exception?>
        get() = _result

    fun insertUser(user: User) {
        userDb.child(user.login!!).setValue(user)
            .addOnCompleteListener {
                if(it.isSuccessful)
                {
                    _result.value=null
                }else
                {
                    _result.value=it.exception
                }
            }
    }
    fun readUser(){
        userDb.addValueEventListener(object : ValueEventListener
        {
            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists())
                {
                    val items= mutableListOf<User>()
                    p0.children.forEach {
                        val item=it.getValue(User::class.java)
                        item?.login=it.key
                        item?.let { items.add(it) }
                    }
                    _users.value = items
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    fun deleteUser(user: User) {
        userDb.child(user.login!!).setValue(null)
            .addOnCompleteListener {
                if(it.isSuccessful)
                {
                    _result.value=null
                }else
                {
                    _result.value=it.exception
                }
            }
    }

    private val chEventListener= object : ChildEventListener
    {
        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {}

        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

        override fun onChildRemoved(snapshot: DataSnapshot) {}

        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

        override fun onCancelled(error: DatabaseError) {}
    }
    override fun onCleared() {
        super.onCleared()
        userDb.removeEventListener(chEventListener)
    }
}
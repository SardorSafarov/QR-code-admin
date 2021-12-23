package com.example.admin.viewModels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.admin.constants.Constants.DOMEN
import com.example.admin.entity.DomenEntity
import com.google.firebase.database.*


class DomenViewModel : ViewModel() {

    private  val domenDb = FirebaseDatabase.getInstance().getReference(DOMEN)

    private val _domens = MutableLiveData<List<DomenEntity>>()
    val domens: LiveData<List<DomenEntity>>
        get() = _domens




    private val _result = MutableLiveData<Exception?>()
    val result: LiveData<Exception?>
        get() = _result

    fun insertDomen(domenEntity: DomenEntity) {
        domenEntity.id = domenDb.push().key
        domenDb.child(domenEntity.id!!).setValue(domenEntity)
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
    fun readDomen(){
        domenDb.addValueEventListener(object : ValueEventListener
        {
            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists())
                {
                    val items= mutableListOf<DomenEntity>()
                    p0.children.forEach {
                        val item=it.getValue(DomenEntity::class.java)
                        item?.id=it.key
                        item?.let { items.add(it) }
                    }
                    _domens.value = items
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    fun deleteDomen(domen: DomenEntity) {
        domenDb.child(domen.id!!).setValue(null)
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
    private val chEventListener= object :ChildEventListener
    {
        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {}

        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

        override fun onChildRemoved(snapshot: DataSnapshot) {}

        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

        override fun onCancelled(error: DatabaseError) {}
    }
    override fun onCleared() {
        super.onCleared()
        domenDb.removeEventListener(chEventListener)
    }
}
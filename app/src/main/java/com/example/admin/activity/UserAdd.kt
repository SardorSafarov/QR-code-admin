package com.example.admin.activity

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admin.R
import com.example.admin.adapter.UserAdapter
import com.example.admin.databinding.ActivityUserAddBinding
import com.example.admin.databinding.AddUserDialogBinding
import com.example.admin.entity.Locationentity
import com.example.admin.entity.User
import com.example.admin.viewModels.LocationViewModel
import com.example.admin.viewModels.UserViewModel

class UserAdd : AppCompatActivity(),UserAdapter.RecyclerOnClikListener {

    lateinit var binding: ActivityUserAddBinding

    private  val userViewModel: UserViewModel by lazy { ViewModelProviders.of(this).get(UserViewModel::class.java) }

    private val adapter:UserAdapter by lazy { UserAdapter(this) }

    private val locationViewModel:LocationViewModel by lazy { ViewModelProviders.of(this).get(LocationViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        title = "Foydalanuvchi Qo`shish"
        super.onCreate(savedInstanceState)
        binding = ActivityUserAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userViewModel.readUser()

        addUser()

        readUsers()
    }
    // bazadagi foydalanuvchilarni olib keladi
    private fun readUsers() {
        binding.listUser.adapter=adapter
        binding.listUser.layoutManager=LinearLayoutManager(this)
        userViewModel.users.observe(this, Observer {
            adapter.setdata(it)
        })
    }
    // bazaga foydalanuvchilarni qo`shadi
    private fun addUser() {
        binding.fab.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            val view = LayoutInflater.from(this).inflate(R.layout.add_user_dialog, null)
            alertDialog.setView(view)
            alertDialog.setTitle("Foydalanuvchi qo`shish")
            val bind = AddUserDialogBinding.bind(view)
            alertDialog.setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
                if (bind.addLogin.text.toString() != "" && bind.addParol.text.toString() != "") {
                    userViewModel.insertUser(
                        User(
                            login = bind.addLogin.text.toString(),
                            password = bind.addParol.text.toString()
                        )
                    )
                    locationViewModel.insertLocation(Locationentity(login = bind.addLogin.text.toString()))
                }
            }
            alertDialog.show()
        }
    }

    //foydalanuvchini o`chiradi
    override fun onclik(user: User) {
            val alertDialog=AlertDialog.Builder(this)
        alertDialog.setTitle(user.login)
        alertDialog.setMessage("O`chirasizmi?")
        alertDialog.setPositiveButton("Ha"){ dialogInterface: DialogInterface, i: Int ->
            userViewModel.deleteUser(user)
        }
        alertDialog.setNegativeButton("Yo`q"){ dialogInterface: DialogInterface, i: Int -> }
        alertDialog.show()
    }
}
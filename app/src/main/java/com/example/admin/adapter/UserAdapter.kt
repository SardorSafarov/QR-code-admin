package com.example.admin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.admin.R
import com.example.admin.databinding.ItemUserBinding
import com.example.admin.entity.User


class UserAdapter(private val listener:RecyclerOnClikListener) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {


    interface RecyclerOnClikListener{
        fun onclik(user: User)
    }
    private var userList = emptyList<User>()

    fun setdata(user: List<User>) {
        this.userList = user
        notifyDataSetChanged()
    }
    inner  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding = ItemUserBinding.bind(itemView)
        fun bind(user: User) {
            binding.login.text="Login:${user.login}"
            binding.parol.text="Parol:${user.password}"
            itemView.setOnClickListener {
                listener.onclik(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int = userList.size

}
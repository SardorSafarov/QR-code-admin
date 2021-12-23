package com.example.admin.activity

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admin.adapter.DomenAdapterer
import com.example.admin.databinding.ActivityDomenAddBinding
import com.example.admin.entity.DomenEntity
import com.example.admin.viewModels.DomenViewModel

class DomenAdd : AppCompatActivity(),DomenAdapterer.onclikListener {

    lateinit var binding: ActivityDomenAddBinding

    private lateinit var viewModel: DomenViewModel

    private  val adapter:DomenAdapterer by lazy { DomenAdapterer(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        title="Domen Qo`shish"
        super.onCreate(savedInstanceState)
        binding= ActivityDomenAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProviders.of(this).get(DomenViewModel::class.java)
        addDomen()
        readDomen()
    }

    private fun readDomen() {
        viewModel.readDomen()
        binding.domenList.adapter=adapter
        binding.domenList.layoutManager=LinearLayoutManager(this)
        viewModel.domens.observe(this, Observer {
                adapter.setdata(it as MutableList<DomenEntity>)
        })
    }

    private fun addDomen() {
        binding.domenBtn.setOnClickListener {
            if(binding.addDomen.text.toString()!="")
            {
                viewModel.insertDomen(DomenEntity(domenName = binding.addDomen.text.toString()))
                binding.addDomen.text?.clear()
                Toast.makeText(this, "Domen qo`shildi!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onclik(domen: DomenEntity) {
        val alertDialog=AlertDialog.Builder(this)
        alertDialog.setTitle(domen.domenName)
        alertDialog.setMessage("O`chirmoqchimisiz?")
        alertDialog.setPositiveButton("ha"){
                dialogInterface: DialogInterface, i: Int ->
            viewModel.deleteDomen(domen)
            viewModel.readDomen()
        }
            alertDialog.setNegativeButton("Yo`q"){ dialogInterface: DialogInterface, i: Int -> }
        alertDialog.show()
    }
}
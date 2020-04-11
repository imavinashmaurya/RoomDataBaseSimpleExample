package com.avinash.roomdbdemo

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.avinash.roomdbdemo.room.dao.NameDao
import com.avinash.roomdbdemo.room.database.NameDataBase
import com.avinash.roomdbdemo.room.entity.NameEntity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btName
import kotlinx.android.synthetic.main.activity_main.etName
import kotlinx.android.synthetic.main.layout_edit_dialog.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


class MainActivity : AppCompatActivity(), ClickListener {

    var nameDao: NameDao? = null
    var nameList: ArrayList<NameEntity> = ArrayList()
    var adapter: NameRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        setupRecyclerView()
    }

    private fun init() {
        nameDao = NameDataBase.getNameDataBase(this@MainActivity).nameDao()
        btName.setOnClickListener(View.OnClickListener {
            val data = etName.text.toString()
            if (data.isNotEmpty()) {
                GlobalScope.launch {
                    nameDao?.insertName(NameEntity(data))
                }
                etName.text.clear()
            }
        })

        nameDao?.getAllName()?.observe(this, Observer {
            nameList.clear()
            nameList.addAll(it as ArrayList<NameEntity>)
            adapter?.notifyDataSetChanged()
        })
    }

    private fun setupRecyclerView() {
        //adding a layoutManager
        rvNames?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        //creating our adapter
        adapter = NameRecyclerViewAdapter(nameList, this)
        //adding separator
        rvNames?.addItemDecoration(
            DividerItemDecoration(
                rvNames?.context,
                DividerItemDecoration.VERTICAL
            )
        )
        //now adding the adapter to recyclerview
        rvNames?.adapter = adapter
    }

    override fun onEdit(nameEntity: NameEntity) {
        val dialogEdit = Dialog(this)
        dialogEdit.setContentView(R.layout.layout_edit_dialog)
        dialogEdit.show()
        dialogEdit.tvId?.text = "ID: ${nameEntity.id}"
        dialogEdit.etName?.setText(nameEntity.name)
        dialogEdit.btSend?.setOnClickListener(View.OnClickListener {
            val data = dialogEdit.etName?.text.toString()
            nameEntity.name = data
            if (data.isNotEmpty()) {
                GlobalScope.launch {
                    nameDao?.insertName(nameEntity)
                }
            }
            dialogEdit.dismiss()
        })
    }

    override fun onDelete(nameEntity: NameEntity) {
        GlobalScope.launch {
            nameDao?.deleteName(nameEntity)
        }
    }
}

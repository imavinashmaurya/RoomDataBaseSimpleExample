package com.avinash.roomdbdemo

import com.avinash.roomdbdemo.room.entity.NameEntity

interface ClickListener {
    fun onEdit(nameEntity: NameEntity)
    fun onDelete(nameEntity: NameEntity)
}
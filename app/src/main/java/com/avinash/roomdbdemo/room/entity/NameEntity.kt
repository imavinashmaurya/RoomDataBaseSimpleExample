package com.avinash.roomdbdemo.room.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NameTable")
data class NameEntity(
    @NonNull
    @ColumnInfo(name = "name")
    var name: String?,
    @NonNull
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
    val id: Int = 0
)
package com.avinash.roomdbdemo.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.avinash.roomdbdemo.room.entity.NameEntity

@Dao
interface NameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertName(name: NameEntity): Long

    @Query("SELECT * FROM NameTable")
    fun getAllName(): LiveData<List<NameEntity>>

    @Delete
    suspend fun deleteName(name: NameEntity)

    @Update
    suspend fun editName(name: NameEntity)
}
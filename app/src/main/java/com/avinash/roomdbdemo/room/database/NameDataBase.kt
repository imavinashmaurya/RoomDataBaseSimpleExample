package com.avinash.roomdbdemo.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.avinash.roomdbdemo.room.dao.NameDao
import com.avinash.roomdbdemo.room.entity.NameEntity


@Database(entities = arrayOf(NameEntity::class), version = 1, exportSchema = false)
abstract class NameDataBase : RoomDatabase() {

    abstract fun nameDao(): NameDao

    companion object {

        @Volatile
        private var INSTANCE: NameDataBase? = null

        fun getNameDataBase(context: Context): NameDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NameDataBase::class.java,
                    "NameDB"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
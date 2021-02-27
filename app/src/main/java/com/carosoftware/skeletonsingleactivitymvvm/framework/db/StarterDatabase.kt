package com.carosoftware.skeletonsingleactivitymvvm.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [StarterEntity::class],
    version = 1,
    exportSchema = false
)

abstract class StarterDatabase: RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "starter.db"
        private var instance: StarterDatabase? = null

        private fun create(context: Context): StarterDatabase =
            Room.databaseBuilder(context, StarterDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()

        fun getInstance(context: Context): StarterDatabase =
            (instance ?: create(context)).also { instance = it }

    }

    abstract fun starterDao(): StarterDao
    // other daos go here
}
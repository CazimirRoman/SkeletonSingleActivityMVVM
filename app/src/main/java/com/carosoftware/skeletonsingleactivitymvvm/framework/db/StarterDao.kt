package com.carosoftware.skeletonsingleactivitymvvm.framework.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface StarterDao {

    @Insert(onConflict = REPLACE)
    suspend fun addStarter(starter: StarterEntity)

    @Query("SELECT * FROM starter")
    fun getAllStarters(): List<StarterEntity>

    @Delete
    suspend fun removeStarter(starter: StarterEntity)

}
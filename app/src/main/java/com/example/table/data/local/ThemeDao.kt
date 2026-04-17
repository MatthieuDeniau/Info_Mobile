package com.example.table.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.table.domain.model.SettingsEntity
import com.example.table.domain.model.ThemeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ThemeDao {

    @Query("SELECT * FROM theme LIMIT 1")
    fun getTheme(): Flow<ThemeEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTheme(theme: ThemeEntity)
}


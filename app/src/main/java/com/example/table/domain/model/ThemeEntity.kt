package com.example.table.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "theme")
data class ThemeEntity(
    @PrimaryKey val id: Int = 1,
    val theme : Boolean
)

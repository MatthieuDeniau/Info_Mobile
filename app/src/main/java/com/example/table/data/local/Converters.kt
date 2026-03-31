package com.example.table.data.local

import androidx.room.TypeConverter
import java.time.LocalDate

class IntListConverter {

    @TypeConverter
    fun fromIntList(list: List<Int>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun toIntList(data: String): List<Int> {
        return if (data.isEmpty()) emptyList()
        else data.split(",").map { it.toInt() }
    }
}

class LocalDateConverter {

    @TypeConverter
    fun fromLocalDate(date: LocalDate?): String? {
        return date?.toString()
    }

    @TypeConverter
    fun toLocalDate(value: String?): LocalDate? {
        return value?.let { LocalDate.parse(it) }
    }
}

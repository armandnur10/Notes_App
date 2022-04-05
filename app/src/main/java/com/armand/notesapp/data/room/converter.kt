package com.armand.notesapp.data.room

import androidx.room.TypeConverter
import com.armand.notesapp.data.entity.Priority

class Converter {

    @TypeConverter
    fun fromPriority(priority: Priority): String{
        return priority.name
    }

    @TypeConverter
    fun toPriority(priority: String): Priority{
        return Priority.valueOf(priority)
    }
}
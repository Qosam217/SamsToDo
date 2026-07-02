package com.project.samstodo.database

import androidx.room.TypeConverter
import com.project.samstodo.models.TaskType
import java.util.Date

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date?{
        return value?.let(::Date)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long?{
        return date?.time
    }

    @TypeConverter
    fun fromTaskType(value: String): TaskType{
        return TaskType.valueOf(value)
    }

    @TypeConverter
    fun taskTypeToString(type: TaskType): String{
        return type.name
    }
}
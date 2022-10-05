package com.example.vix_schoters_dicksenalfersiusnovian.database

import androidx.room.TypeConverter
import com.example.vix_schoters_dicksenalfersiusnovian.models.Source


class Converters {
    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }

}
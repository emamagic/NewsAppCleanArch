package com.example.data.db.converter

import androidx.room.TypeConverter
import com.example.domain.entity.Source

class SourceConverter {

    @TypeConverter
    fun fromSource(source: Source) = source.name

    @TypeConverter
    fun toSource(name: String) = Source(name ,name)

}
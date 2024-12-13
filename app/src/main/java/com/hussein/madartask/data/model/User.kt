package com.hussein.madartask.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.hussein.madartask.domain.GenderConverter
import kotlinx.serialization.Serializable

@Entity(tableName = "users") // Specify the table name here
@Serializable
data class User(@PrimaryKey(autoGenerate = true) val id: Int=0,
                val name: String="",
                val age: String="",
                val jobTitle: String="",
                @TypeConverters(GenderConverter::class)
                @Serializable// Use the Type Converter
                val gender: Gender=Gender.MALE)


package com.hussein.madartask.domain

import androidx.room.TypeConverter
import com.hussein.madartask.data.model.Gender

class GenderConverter {

    @TypeConverter
    fun fromGender(gender: Gender): String { // Convert Gender to String for storage
        return gender.name
    }

    @TypeConverter
    fun toGender(genderString: String): Gender? {  // Convert String back to Gender
        return try {
            Gender.valueOf(genderString)  // Handles valid gender strings
        } catch (e: IllegalArgumentException) {
            null // Handle invalid or null strings (important for robustness)
        }
    }
}
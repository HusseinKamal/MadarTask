package com.hussein.madartask.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hussein.madartask.data.model.User
import com.hussein.madartask.domain.GenderConverter
import com.hussein.madartask.domain.UserDao

@Database(entities = [User::class], version = 1, exportSchema = false)
@TypeConverters(GenderConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao  // Your DAO abstract function
}

package com.example.testwork.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.testwork.domain.UserItem

@Database(
    entities = [
        UserItem::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
}

package com.ammaryasser.masb7ty.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [Tasbee7::class],
    version = 1,
    exportSchema = false
)
abstract class Tasabee7DB : RoomDatabase() {

    abstract fun dao(): Tasabee7Dao

    companion object {
        @Volatile
        private var Instance: Tasabee7DB? = null

        fun getDatabase(context: Context): Tasabee7DB {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    Tasabee7DB::class.java,
                    "masb7ty_app_db"
                )
                    .fallbackToDestructiveMigration(false)
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
package com.ammaryasser.masb7ty.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface Tasabee7Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(tasbee7: Tasbee7)

    @Delete
    suspend fun delete(tasbee7: Tasbee7)

    @Query("UPDATE $tableName SET count = 0")
    suspend fun clearAllCounters()

    @Query("UPDATE $tableName SET target = :value")
    suspend fun unifyAllTargets(value: Int)

    @Query("UPDATE $tableName SET count = :count WHERE id = :id")
    suspend fun updateCountOf(id: Int, count: Int)

    @Query("SELECT * FROM $tableName WHERE id = :id")
    fun getById(id: Int): Flow<Tasbee7>

    @Query("SELECT * FROM $tableName")
    fun getAll(): Flow<List<Tasbee7>>

    @Query("SELECT COUNT(id) FROM $tableName")
    fun countAll(): Flow<Int>

}
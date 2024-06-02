package com.ammaryasser.masb7ty.data

import android.content.Context


class Tasabee7Repository(private val dao: Tasabee7Dao) {

    suspend fun insertOrUpdate(tasbee7: Tasbee7) = dao.insertOrUpdate(tasbee7)

    suspend fun delete(tasbee7: Tasbee7) = dao.delete(tasbee7)

    suspend fun clearAllCounters() = dao.clearAllCounters()

    suspend fun unifyAllTargets(value: Int) = dao.unifyAllTargets(value)

    suspend fun updateCountOf(id: Int, count: Int) = dao.updateCountOf(id, count)

    fun getById(id: Int) = dao.getById(id)

    fun getAll() = dao.getAll()

    fun countAll() = dao.countAll()


    companion object {
        private var repo: Tasabee7Repository? = null

        fun build(context: Context): Tasabee7Repository {
            return repo ?: synchronized(this) {
                Tasabee7Repository(
                    Tasabee7DB.getDatabase(context).dao()
                )
                    .also { repo = it }
            }
        }
    }

}
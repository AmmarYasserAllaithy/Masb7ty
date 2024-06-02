package com.ammaryasser.masb7ty.data

import androidx.room.Entity
import androidx.room.PrimaryKey

import java.io.Serializable


const val tableName = "tasabee7"


@Entity(tableName = tableName)
data class Tasbee7(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var text: String,
    var target: Int,
    var count: Int = 0

) : Serializable
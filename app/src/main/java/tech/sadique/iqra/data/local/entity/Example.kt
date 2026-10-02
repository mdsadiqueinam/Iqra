package tech.sadique.iqra.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "examples")
data class Example(
    @PrimaryKey
    val id: String
)

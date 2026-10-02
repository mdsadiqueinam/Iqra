package tech.sadique.iqra.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meanings")
data class Meaning(
    @PrimaryKey
    val id: String
)

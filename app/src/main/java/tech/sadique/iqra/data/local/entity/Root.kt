package tech.sadique.iqra.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "roots")
data class Root(
    @PrimaryKey
    val id: String
)

package tech.sadique.iqra.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "meanings",
    foreignKeys = [
        ForeignKey(
            entity = Language::class,
            parentColumns = ["id"],
            childColumns = ["language"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [
        Index(value = ["language"])
    ]
)
data class Meaning(
    @PrimaryKey
    val id: String,
    val language: String,
    val sentence: String
)

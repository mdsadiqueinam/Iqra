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
        ),
        ForeignKey(
            entity = Example::class,
            parentColumns = ["id"],
            childColumns = ["exampleId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["language"]),
        Index(value = ["exampleId"])
    ]
)
data class Meaning(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val exampleId: Int,
    val language: String,
    val sentence: String
)

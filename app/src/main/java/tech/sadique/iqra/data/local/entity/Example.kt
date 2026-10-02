package tech.sadique.iqra.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "examples",
    foreignKeys = [
        ForeignKey(
            entity = Word::class,
            parentColumns = ["id"],
            childColumns = ["word"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["word"])
    ]
)
data class Example(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val word: String,
    // An Arabic sentence without harakat easy to do search
    val sentence: String,
    // An Arabic sentence with harakat
    val formed: String
)

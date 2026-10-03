package tech.sadique.iqra.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "word_meanings",
    foreignKeys = [
        ForeignKey(
            entity = Language::class,
            parentColumns = ["id"],
            childColumns = ["language"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = Word::class,
            parentColumns = ["id"],
            childColumns = ["wordId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["language"]),
        Index(value = ["wordId"])
    ]
)
data class WordMeaning(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val wordId: String,
    val language: String,
    val text: String
)

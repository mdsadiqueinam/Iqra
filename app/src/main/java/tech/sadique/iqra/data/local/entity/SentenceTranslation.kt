package tech.sadique.iqra.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "sentence_translations",
    foreignKeys = [
        ForeignKey(
            entity = Language::class,
            parentColumns = ["id"],
            childColumns = ["language"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = Sentence::class,
            parentColumns = ["id"],
            childColumns = ["sentenceId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["language"]),
        Index(value = ["sentenceId"])
    ]
)
data class SentenceTranslation(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val sentenceId: Int,
    val language: String,
    val text: String
)

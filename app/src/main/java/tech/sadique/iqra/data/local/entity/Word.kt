package tech.sadique.iqra.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "words",
    foreignKeys = [
        ForeignKey(
            entity = WordType::class,
            parentColumns = ["id"],
            childColumns = ["type"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = Root::class,
            parentColumns = ["id"],
            childColumns = ["root"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = WordCategory::class,
            parentColumns = ["id"],
            childColumns = ["category"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [
        Index(value = ["type"]),
        Index(value = ["root"]),
        Index(value = ["category"])
    ]
)
data class Word(
    @PrimaryKey
    val id: String,
    // An Arabic word with its pronunciation(harakat)
    val formed: String,
    val type: String,
    val root: String,
    val category: String,
    val isQuranic: Boolean = false
)

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
        )
    ],
    indices = [
        Index(value = ["type"]),
        Index(value = ["root"])
    ]
)
data class Word(
    @PrimaryKey
    val id: String,
    val type: String,
    val root: String
)

package tech.sadique.iqra.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "word_progress",
    foreignKeys = [
        ForeignKey(
            entity = Word::class,
            parentColumns = ["id"],
            childColumns = ["wordId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["nextReviewAt"]),
        Index(value = ["state"]),
        Index(value = ["isFavorite"])
    ]
)
data class WordProgress(
    @PrimaryKey
    val wordId: String,
    val state: String = State.NEW,
    val repetition: Int = 0,
    val intervalDays: Int = 0,
    val easeFactor: Float = DEFAULT_EASE_FACTOR,
    val correctCount: Int = 0,
    val incorrectCount: Int = 0,
    val lastReviewedAt: Long? = null,
    val nextReviewAt: Long = 0L,
    val isFavorite: Boolean = false
) {
    companion object {
        const val DEFAULT_EASE_FACTOR = 2.5f
        const val MINIMUM_EASE_FACTOR = 1.3f
    }

    object State {
        const val NEW = "NEW"
        const val LEARNING = "LEARNING"
        const val REVIEW = "REVIEW"
        const val MASTERED = "MASTERED"
    }
}

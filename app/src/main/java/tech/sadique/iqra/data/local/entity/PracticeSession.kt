package tech.sadique.iqra.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "practice_sessions")
data class PracticeSession(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val mode: String,
    val startedAt: Long,
    val completedAt: Long,
    val totalCount: Int,
    val correctCount: Int
) {
    object Mode {
        const val FLASHCARD = "FLASHCARD"
        const val QUIZ = "QUIZ"
    }
}

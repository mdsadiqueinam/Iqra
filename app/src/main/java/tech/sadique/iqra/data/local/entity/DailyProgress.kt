package tech.sadique.iqra.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_progress")
data class DailyProgress(
    // Primary key as ISO date string: "YYYY-MM-DD"
    @PrimaryKey
    val date: String,
    val wordsLearned: Int = 0,
    val wordsReviewed: Int = 0,
    val correctCount: Int = 0,
    val incorrectCount: Int = 0,
    val timeSpentSeconds: Long = 0L,
    val isGoalCompleted: Boolean = false
) {
    val totalReviews: Int get() = correctCount + incorrectCount
    val accuracy: Float get() = if (totalReviews > 0) (correctCount.toFloat() / totalReviews) else 0f
}

package tech.sadique.iqra.domain.srs

import tech.sadique.iqra.data.local.entity.WordProgress

object SpacedRepetitionEngine {
    private const val ONE_DAY_MILLIS = 24 * 60 * 60 * 1000L
    private const val TEN_MINUTES_MILLIS = 10 * 60 * 1000L
    private const val MASTERED_INTERVAL_DAYS = 21

    fun calculateNextReview(
        current: WordProgress,
        isCorrect: Boolean,
        currentTime: Long = System.currentTimeMillis()
    ): WordProgress {
        return if (isCorrect) {
            val newRepetition = current.repetition + 1
            val newIntervalDays = when (current.repetition) {
                0 -> 1
                1 -> 3
                else -> (current.intervalDays * current.easeFactor).toInt().coerceAtLeast(1)
            }
            val newState = if (newIntervalDays >= MASTERED_INTERVAL_DAYS) {
                WordProgress.State.MASTERED
            } else {
                WordProgress.State.REVIEW
            }
            current.copy(
                state = newState,
                repetition = newRepetition,
                intervalDays = newIntervalDays,
                correctCount = current.correctCount + 1,
                lastReviewedAt = currentTime,
                nextReviewAt = currentTime + (newIntervalDays * ONE_DAY_MILLIS)
            )
        } else {
            val newEaseFactor = (current.easeFactor - 0.2f)
                .coerceAtLeast(WordProgress.MINIMUM_EASE_FACTOR)
            current.copy(
                state = WordProgress.State.LEARNING,
                repetition = 0,
                intervalDays = 1,
                easeFactor = newEaseFactor,
                incorrectCount = current.incorrectCount + 1,
                lastReviewedAt = currentTime,
                nextReviewAt = currentTime + TEN_MINUTES_MILLIS
            )
        }
    }
}

package tech.sadique.iqra.domain.srs

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import tech.sadique.iqra.data.local.entity.WordProgress

class SpacedRepetitionEngineTest {

    private val baseTime = 1_000_000_000L
    private val oneDayMillis = 24 * 60 * 60 * 1000L
    private val tenMinutesMillis = 10 * 60 * 1000L

    @Test
    fun `first correct answer sets interval to 1 day and state to REVIEW`() {
        val initial = WordProgress(wordId = "word_1")
        val updated = SpacedRepetitionEngine.calculateNextReview(initial, isCorrect = true, currentTime = baseTime)

        assertEquals(1, updated.repetition)
        assertEquals(1, updated.intervalDays)
        assertEquals(WordProgress.State.REVIEW, updated.state)
        assertEquals(1, updated.correctCount)
        assertEquals(0, updated.incorrectCount)
        assertEquals(baseTime, updated.lastReviewedAt)
        assertEquals(baseTime + oneDayMillis, updated.nextReviewAt)
    }

    @Test
    fun `second consecutive correct answer sets interval to 3 days`() {
        val firstPass = WordProgress(
            wordId = "word_1",
            state = WordProgress.State.REVIEW,
            repetition = 1,
            intervalDays = 1,
            easeFactor = 2.5f,
            correctCount = 1
        )
        val updated = SpacedRepetitionEngine.calculateNextReview(firstPass, isCorrect = true, currentTime = baseTime)

        assertEquals(2, updated.repetition)
        assertEquals(3, updated.intervalDays)
        assertEquals(WordProgress.State.REVIEW, updated.state)
        assertEquals(2, updated.correctCount)
        assertEquals(baseTime + (3 * oneDayMillis), updated.nextReviewAt)
    }

    @Test
    fun `repeated correct answers scale interval and reach MASTERED at 21 days`() {
        var current = WordProgress(wordId = "word_1")
        var time = baseTime

        // Repetition 0 -> interval 1
        current = SpacedRepetitionEngine.calculateNextReview(current, isCorrect = true, currentTime = time)
        assertEquals(1, current.intervalDays)

        // Repetition 1 -> interval 3
        time += oneDayMillis
        current = SpacedRepetitionEngine.calculateNextReview(current, isCorrect = true, currentTime = time)
        assertEquals(3, current.intervalDays)

        // Repetition 2 -> interval 3 * 2.5 = 7
        time += 3 * oneDayMillis
        current = SpacedRepetitionEngine.calculateNextReview(current, isCorrect = true, currentTime = time)
        assertEquals(7, current.intervalDays)

        // Repetition 3 -> interval 7 * 2.5 = 17
        time += 7 * oneDayMillis
        current = SpacedRepetitionEngine.calculateNextReview(current, isCorrect = true, currentTime = time)
        assertEquals(17, current.intervalDays)
        assertEquals(WordProgress.State.REVIEW, current.state)

        // Repetition 4 -> interval 17 * 2.5 = 42 (>= 21 days, MASTERED)
        time += 17 * oneDayMillis
        current = SpacedRepetitionEngine.calculateNextReview(current, isCorrect = true, currentTime = time)
        assertTrue(current.intervalDays >= 21)
        assertEquals(WordProgress.State.MASTERED, current.state)
    }

    @Test
    fun `incorrect recall resets repetition and schedules immediate review in 10 minutes`() {
        val progressed = WordProgress(
            wordId = "word_1",
            state = WordProgress.State.REVIEW,
            repetition = 3,
            intervalDays = 7,
            easeFactor = 2.5f,
            correctCount = 3
        )
        val updated = SpacedRepetitionEngine.calculateNextReview(progressed, isCorrect = false, currentTime = baseTime)

        assertEquals(0, updated.repetition)
        assertEquals(1, updated.intervalDays)
        assertEquals(2.3f, updated.easeFactor, 0.001f)
        assertEquals(WordProgress.State.LEARNING, updated.state)
        assertEquals(3, updated.correctCount)
        assertEquals(1, updated.incorrectCount)
        assertEquals(baseTime + tenMinutesMillis, updated.nextReviewAt)
    }

    @Test
    fun `ease factor does not drop below MINIMUM_EASE_FACTOR`() {
        val lowEase = WordProgress(
            wordId = "word_1",
            easeFactor = 1.35f
        )
        val updated = SpacedRepetitionEngine.calculateNextReview(lowEase, isCorrect = false, currentTime = baseTime)

        assertEquals(WordProgress.MINIMUM_EASE_FACTOR, updated.easeFactor, 0.001f)
    }
}

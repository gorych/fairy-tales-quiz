package org.gorych.alice.skill.core.quiz

import io.mockk.every
import io.mockk.mockk
import org.gorych.alice.skill.core.api.RequestObject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.test.Test
import kotlin.test.assertFalse

class QuizHolderTest {

    private val quiz1 = mockk<Quiz> {
        every { name() } returns "Quiz1"
        every { bonusQuiz } returns false
    }
    private val quiz2 = mockk<Quiz> {
        every { name() } returns "Quiz2"
        every { bonusQuiz } returns false
    }
    private val bonusQuiz1 = mockk<Quiz> {
        every { name() } returns "Bonus1"
        every { bonusQuiz } returns true
    }

    private val quizzes = listOf(quiz1, quiz2, bonusQuiz1)
    private val quizHolder = QuizHolder(quizzes)

    @Test
    fun `getQuiz should return random normal quiz when session is new and no quiz in state`() {
        val request = mockRequest(isNewSession = true, quizName = null, isBonus = false)

        val result = quizHolder.getQuiz(request)

        assertFalse(result.bonusQuiz)
        assertTrue(listOf("Quiz1", "Quiz2").contains(result.name()))
    }

    @Test
    fun `getQuiz should return ONLY bonus quiz when bonus mode is active`() {
        val request = mockRequest(isNewSession = true, quizName = null, isBonus = true)

        val result = quizHolder.getQuiz(request)

        assertTrue(result.bonusQuiz)
        assertEquals("Bonus1", result.name())
    }

    @Test
    fun `getQuiz should exclude current quiz name when selecting new random quiz`() {
        val request = mockRequest(isNewSession = true, quizName = "Quiz1", isBonus = false)

        val result = quizHolder.getQuiz(request)

        assertEquals("Quiz2", result.name())
    }

    @Test
    fun `getQuiz should return existing quiz by name during active session`() {
        val request = mockRequest(isNewSession = false, quizName = "Quiz2")

        val result = quizHolder.getQuiz(request)

        assertEquals("Quiz2", result.name())
    }

    @Test
    fun `getQuiz should return DEFAULT_QUIZ when quiz name is not found in registry`() {
        val request = mockRequest(isNewSession = false, quizName = "NonExistentQuiz")

        val result = quizHolder.getQuiz(request)

        assertEquals(QuizHolder.DEFAULT_QUIZ.name(), result.name())
    }

    private fun mockRequest(
        isNewSession: Boolean,
        quizName: String?,
        isBonus: Boolean? = false
    ): RequestObject {
        return mockk {
            every { isNewSession() } returns isNewSession
            every { state?.application?.quizName } returns quizName
            every { state?.application?.bonusQuiz } returns isBonus
        }
    }
}
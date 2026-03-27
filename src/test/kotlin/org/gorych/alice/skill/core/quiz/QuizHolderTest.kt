package org.gorych.alice.skill.core.quiz

import io.mockk.every
import io.mockk.mockk
import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.fairytail.quiz.Quiz1
import org.gorych.alice.skill.fairytail.quiz.Quiz2
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class QuizHolderTest {
    private val quiz1 = Quiz1()
    private val quiz2 = Quiz2()
    private val quizzes = listOf(quiz1, quiz2)
    private val quizHolder = QuizHolder(quizzes)

    @Test
    fun `getQuiz should return random quiz when session is new and name is null`() {
        val request = mockRequest(isNewSession = true, quizName = null)

        val result = quizHolder.getQuiz(request)

        assert(quizzes.contains(result))
    }

    @Test
    fun `getQuiz should return different quiz when session is new and quizName is provided`() {
        val request = mockRequest(isNewSession = true, quizName = "Quiz1")

        val result = quizHolder.getQuiz(request)

        assertEquals("Quiz2", result.name())
        assertNotEquals("Quiz1", result.name())
    }

    @Test
    fun `getQuiz should return existing quiz by name when session is not new`() {
        val request = mockRequest(isNewSession = false, quizName = "Quiz2")

        val result = quizHolder.getQuiz(request)

        assertEquals(quiz2, result)
    }

    @Test
    fun `getQuiz should return DEFAULT_QUIZ when name is unknown`() {
        val request = mockRequest(isNewSession = false, quizName = "UnknownQuiz")

        val result = quizHolder.getQuiz(request)

        assertEquals(QuizHolder.DEFAULT_QUIZ, result)
    }

    private fun mockRequest(isNewSession: Boolean, quizName: String?): RequestObject {
        val mockRequest = mockk<RequestObject>()
        every { mockRequest.isNewSession() } returns isNewSession
        every { mockRequest.state?.application?.quizName } returns quizName
        return mockRequest
    }
}
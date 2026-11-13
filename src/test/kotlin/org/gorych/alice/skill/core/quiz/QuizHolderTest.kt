package org.gorych.alice.skill.core.quiz

import io.mockk.every
import io.mockk.mockk
import org.gorych.alice.skill.core.api.ApplicationState
import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.SessionState
import org.gorych.alice.skill.core.api.State
import org.gorych.alice.skill.core.mode.GameMode
import org.gorych.alice.skill.fairytail.quiz.Quiz1
import org.gorych.alice.skill.fairytail.quiz.Quiz2
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class QuizHolderTest {

    private lateinit var quizHolder: QuizHolder

    @BeforeEach
    fun setUp() {
        quizHolder = QuizHolder()
    }

    @Test
    fun `getQuiz should return random usual quiz when session is new`() {
        //given
        val request = mockk<RequestObject>()
        every { request.isNewSession() } returns true
        every { request.state } returns null
        every { request.gameMode() } returns GameMode.QUESTION_ANSWER

        //when
        val result = quizHolder.getQuiz(request)

        //then
        assertNotNull(result)
        assertFalse(result.bonusQuiz, "New session should pick a usual quiz, not bonus")
    }

    @Test
    fun `getQuiz should return bonus quiz when bonus flag is true and name is null`() {
        //given
        val request = mockk<RequestObject>()
        val appState = ApplicationState(bonusQuiz = true, quizName = null)
        val state = State(application = appState, session = SessionState())

        every { request.isNewSession() } returns false
        every { request.state } returns state
        every { request.gameMode() } returns GameMode.QUESTION_ANSWER

        //when
        val result = quizHolder.getQuiz(request)

        //then
        assertTrue(result.bonusQuiz, "Should return a bonus quiz")
    }

    @Test
    fun `getQuiz should return specific quiz by name from state`() {
        //given
        val targetQuizName = Quiz2::class.simpleName
        val request = mockk<RequestObject>()
        val appState = ApplicationState(quizName = targetQuizName)
        val state = State(application = appState, session = SessionState())

        every { request.isNewSession() } returns false
        every { request.state } returns state
        every { request.gameMode() } returns GameMode.QUESTION_ANSWER

        //when
        val result = quizHolder.getQuiz(request)

        //then
        assertEquals(targetQuizName, result.name())
    }

    @Test
    fun `getQuiz should return a stable quiz of the same mode if quiz name not found`() {
        //given
        val request = mockk<RequestObject>()
        val appState = ApplicationState(quizName = "NonExistentQuiz")
        val state = State(application = appState, session = SessionState())

        every { request.isNewSession() } returns false
        every { request.state } returns state
        every { request.gameMode() } returns GameMode.QUESTION_ANSWER

        //when
        val result = quizHolder.getQuiz(request)

        //then
        assertEquals(GameMode.QUESTION_ANSWER, result.gameMode)
        assertEquals(Quiz1::class.simpleName, result.name())
    }

    @Test
    fun `every game mode should have at least one quiz`() {
        //when
        val actual = quizHolder.availableModes

        //then
        assertEquals(GameMode.entries.toSet(), actual)
    }

    @Test
    fun `every game mode should resolve a quiz`() {
        GameMode.entries.forEach { mode ->
            val request = mockk<RequestObject>()
            val state = State(application = ApplicationState(quizName = null), session = SessionState())

            every { request.isNewSession() } returns true
            every { request.state } returns state
            every { request.gameMode() } returns mode

            assertEquals(mode, quizHolder.getQuiz(request).gameMode, "Mode $mode")
        }
    }
}

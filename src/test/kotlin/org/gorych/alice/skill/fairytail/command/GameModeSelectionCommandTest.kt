package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.ApplicationState
import org.gorych.alice.skill.core.api.NLU
import org.gorych.alice.skill.core.api.Request
import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.Session
import org.gorych.alice.skill.core.api.SessionState
import org.gorych.alice.skill.core.api.State
import org.gorych.alice.skill.core.mode.GameMode
import org.gorych.alice.skill.core.quiz.QuizSource
import org.gorych.alice.skill.fairytail.quiz.Quiz1
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

private val questionAnswerOnly = object : QuizSource {
    override fun getQuiz(requestObject: RequestObject) = Quiz1()
    override val availableModes: Set<GameMode> = setOf(GameMode.QUESTION_ANSWER)
}

private fun requestWith(intentId: String, availableTransitionCommands: Set<String>) = RequestObject(
    request = Request(
        command = "вопрос ответ",
        originalUtterance = "вопрос ответ",
        nlu = NLU(tokens = listOf("вопрос", "ответ"), intents = mapOf(intentId to "{}")),
    ),
    session = Session(new = false),
    state = State(
        session = SessionState(transitionCommands = availableTransitionCommands),
        application = ApplicationState(quizName = null),
    ),
)

class GameModeSelectionCommandTest {

    @Test
    fun `WHEN user selects an available mode THEN it is stored and the first question is returned`() {
        val command = GameModeSelectionCommand(questionAnswerOnly)
        val request = requestWith("g911.mode_quiz", setOf(GameModeSelectionCommand.name()))

        val response = command.execute(request)

        assertEquals(GameMode.QUESTION_ANSWER, response.applicationState?.gameMode)
        assertEquals(Quiz1::class.simpleName, response.applicationState?.quizName)
        assertEquals(1, response.sessionState.currentQuestion)
        assertEquals(setOf(NextQuestionCommand.name()), response.sessionState.transitionCommands)
        assertTrue(response.response.text.contains("Кто посадил репку?"))
        assertFalse(response.response.endSession)
    }

    @Test
    fun `WHEN the requested mode is not available THEN the command should not handle the request`() {
        val command = GameModeSelectionCommand(questionAnswerOnly)
        val request = requestWith("g911.mode_sound", setOf(GameModeSelectionCommand.name()))

        assertFalse(command.canHandle(request))
    }

    @Test
    fun `WHEN the menu was not offered THEN the command should not handle the request`() {
        val command = GameModeSelectionCommand(questionAnswerOnly)
        val request = requestWith("g911.mode_quiz", setOf(NextQuestionCommand.name()))

        assertFalse(command.canHandle(request))
    }

    @Test
    fun `WHEN the menu was offered and the mode is available THEN the command should handle the request`() {
        val command = GameModeSelectionCommand(questionAnswerOnly)
        val request = requestWith("g911.mode_quiz", setOf(GameModeSelectionCommand.name()))

        assertTrue(command.canHandle(request))
    }
}

package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.Button
import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.SessionState
import org.gorych.alice.skill.core.api.of
import org.gorych.alice.skill.core.mode.GameMode
import org.gorych.alice.skill.core.quiz.QuizSource
import org.gorych.alice.skill.fairytail.quiz.question_answer.Quiz1
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

private val quizzesWithSingleMode = object : QuizSource {
    override fun getQuiz(requestObject: RequestObject) = Quiz1()
    override val availableModes: Set<GameMode> = setOf(GameMode.QUESTION_ANSWER)
}

private fun modesHelpRequest(availableTransitionCommands: Set<String> = setOf()) = RequestObject.of(
    "g911.modes_help",
    sessionState = SessionState(transitionCommands = availableTransitionCommands),
)

class ModesHelpCommandTest {

    @Test
    fun `WHEN user asks about modes THEN every mode is described`() {
        val command = ModesHelpCommand(quizzesWithSingleMode)

        val response = command.execute(modesHelpRequest())

        val text = response.response.text
        assertTrue(text.contains("«Вопрос-ответ» — я задаю вопросы по сказкам"))
        assertTrue(text.contains("«Угадай по звуку» — я включаю аудиодорожку"))
        assertTrue(text.contains("«Правда или ложь» — я зачитываю утверждение"))
        assertTrue(text.contains("Какой режим выберешь?"))
        assertFalse(response.response.endSession)
    }

    @Test
    fun `WHEN user asks about modes THEN the menu of available modes is returned`() {
        val command = ModesHelpCommand(quizzesWithSingleMode)

        val response = command.execute(modesHelpRequest())

        val titles = response.response.buttons.map { it.title }
        assertTrue(titles.contains(Button.gameMode(GameMode.QUESTION_ANSWER).title))
        assertFalse(titles.contains(Button.gameMode(GameMode.GUESS_SOUND).title))
        assertEquals(
            setOf(GameModeSelectionCommand.name(), ModesHelpCommand.name()),
            response.sessionState.transitionCommands
        )
    }

    @Test
    fun `WHEN user asks about modes THEN the command should handle the request in any state`() {
        val command = ModesHelpCommand(quizzesWithSingleMode)

        assertTrue(command.canHandle(modesHelpRequest()))
        assertTrue(command.canHandle(modesHelpRequest(setOf(NextQuestionCommand.name()))))
    }
}

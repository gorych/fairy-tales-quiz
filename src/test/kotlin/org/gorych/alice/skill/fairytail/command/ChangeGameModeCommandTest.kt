package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.Button
import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.of
import org.gorych.alice.skill.core.mode.GameMode
import org.gorych.alice.skill.fairytail.quiz.Quiz1
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ChangeGameModeCommandTest {

    private val command = ChangeGameModeCommand { Quiz1() }

    @Test
    fun `WHEN user asks for another mode THEN the command should handle the request`() {
        assertTrue(command.canHandle(RequestObject.of("g911.another_mode")))
        assertFalse(command.canHandle(RequestObject.of("g911.mode_quiz")))
        assertFalse(command.canHandle(RequestObject.of("g911.greeting")))
    }

    @Test
    fun `WHEN user asks for another mode THEN the mode menu should be returned`() {
        val response = command.execute(RequestObject.of("g911.another_mode"))

        val titles = response.response.buttons.map { it.title }
        GameMode.entries.forEach { assertTrue(titles.contains(Button.gameMode(it).title), "Mode $it") }
        assertFalse(response.response.endSession)
        assertEquals(
            setOf(GameModeSelectionCommand.name(), ModesHelpCommand.name()),
            response.sessionState.transitionCommands
        )
    }

    @Test
    fun `WHEN user asks for another mode THEN the command should not require a game mode`() {
        assertFalse(command.needsGameMode())
    }
}

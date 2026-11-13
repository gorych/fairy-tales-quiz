package org.gorych.alice.skill.fairytail

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import tools.jackson.module.kotlin.jacksonObjectMapper

private const val CHOOSE_MODE_HINT = "выбрать режим"

private const val GAMEPLAY_REQUEST_WITHOUT_MODE = """
{
  "session": {"new": false},
  "request": {
    "command": "дед",
    "original_utterance": "дед",
    "nlu": {"tokens": ["дед"], "entities": [], "intents": {}},
    "type": "SimpleUtterance"
  },
  "state": {
    "session": {"currentQuestion": 1, "transitionCommands": ["NextQuestionCommand"]},
    "application": {"quizName": "Quiz1"}
  },
  "version": "1.0"
}
"""

private const val UNCLEAR_REQUEST_WITHOUT_MODE = """
{
  "session": {"new": false},
  "request": {
    "command": "абракадабра",
    "original_utterance": "абракадабра",
    "nlu": {"tokens": [], "entities": [], "intents": {}},
    "type": "SimpleUtterance"
  },
  "state": {
    "session": {},
    "application": {"quizName": "Quiz1"}
  },
  "version": "1.0"
}
"""

private const val GREETING_REQUEST_WITHOUT_MODE = """
{
  "session": {"new": false},
  "request": {
    "command": "привет",
    "original_utterance": "привет",
    "nlu": {"tokens": ["привет"], "entities": [], "intents": {"g911.greeting": {"slots": {}}}},
    "type": "SimpleUtterance"
  },
  "state": {
    "session": {},
    "application": {"quizName": "Quiz1"}
  },
  "version": "1.0"
}
"""

class GameModeGateTest {

    private val mapper = jacksonObjectMapper()

    @Test
    fun `WHEN gameplay command comes without a selected mode THEN the mode menu should be returned`() {
        val json = mapper.readTree(handle(GAMEPLAY_REQUEST_WITHOUT_MODE))

        val text = json.get("response").get("text").asString()
        val buttons = json.get("response").get("buttons").map { it.get("title").asString() }
        val transitionCommands = json.get("session_state").get("transitionCommands").map { it.asString() }

        assertTrue(text.contains(CHOOSE_MODE_HINT))
        assertTrue(buttons.contains("❓ Вопрос-ответ"))
        assertTrue(transitionCommands.contains("GameModeSelectionCommand"))
        assertFalse(json.get("response").get("end_session").asBoolean())
    }

    @Test
    fun `WHEN nothing is understood and there is no selected mode THEN the mode menu should be returned`() {
        val json = mapper.readTree(handle(UNCLEAR_REQUEST_WITHOUT_MODE))

        val text = json.get("response").get("text").asString()

        assertTrue(text.contains("Я вас не поняла"))
        assertTrue(text.contains("Какой режим выберешь?"))
        assertEquals(
            listOf("GameModeSelectionCommand", "ModesHelpCommand"),
            json.get("session_state").get("transitionCommands").map { it.asString() }
        )
    }

    @Test
    fun `WHEN utility command comes without a selected mode THEN it should run as usual`() {
        val json = mapper.readTree(handle(GREETING_REQUEST_WITHOUT_MODE))

        assertEquals("И тебе, привет!", json.get("response").get("text").asString())
    }
}

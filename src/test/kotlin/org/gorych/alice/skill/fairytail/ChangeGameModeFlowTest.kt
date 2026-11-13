package org.gorych.alice.skill.fairytail

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import tools.jackson.module.kotlin.jacksonObjectMapper

private const val ANOTHER_MODE_REQUEST = """
{
  "session": {"new": false},
  "request": {
    "command": "другой режим",
    "original_utterance": "другой режим",
    "nlu": {"tokens": ["другой", "режим"], "entities": [], "intents": {"g911.another_mode": {"slots": {}}}},
    "type": "SimpleUtterance"
  },
  "state": {
    "session": {
      "currentQuestion": 5,
      "rightAnswersCount": 3,
      "transitionCommands": ["NextQuestionCommand"]
    },
    "application": {"quizName": "Quiz1", "bonusQuiz": false, "gameMode": "QUESTION_ANSWER"}
  },
  "version": "1.0"
}
"""

private const val SELECT_TRUTH_OR_LIE_REQUEST = """
{
  "session": {"new": false},
  "request": {
    "command": "правда или ложь",
    "original_utterance": "правда или ложь",
    "nlu": {"tokens": ["правда", "или", "ложь"], "entities": [], "intents": {"g911.mode_truth_or_lie": {"slots": {}}}},
    "type": "SimpleUtterance"
  },
  "state": {
    "session": {
      "currentQuestion": 5,
      "rightAnswersCount": 3,
      "transitionCommands": ["GameModeSelectionCommand", "ModesHelpCommand"]
    },
    "application": {"quizName": "Quiz1", "bonusQuiz": false, "gameMode": "QUESTION_ANSWER"}
  },
  "version": "1.0"
}
"""

class ChangeGameModeFlowTest {

    private val mapper = jacksonObjectMapper()

    @Test
    fun `WHEN user asks for another mode in the middle of a game THEN the mode menu should be returned`() {
        val json = mapper.readTree(handle(ANOTHER_MODE_REQUEST))

        val buttons = json.get("response").get("buttons").map { it.get("title").asString() }
        val transitionCommands = json.get("session_state").get("transitionCommands").map { it.asString() }

        assertTrue(buttons.contains("❓ Вопрос-ответ"))
        assertTrue(buttons.contains("🔊 Угадай по звуку"))
        assertTrue(buttons.contains("🤔 Правда или ложь"))
        assertTrue(transitionCommands.contains("GameModeSelectionCommand"))
    }

    @Test
    fun `WHEN user switches to another mode THEN the new game should start from the first question`() {
        val json = mapper.readTree(handle(SELECT_TRUTH_OR_LIE_REQUEST))

        assertTrue(json.get("response").get("text").asString().contains("Правда, что репку посадил дед?"))
        assertEquals("TRUE_OR_FALSE", json.get("application_state").get("gameMode").asString())
        assertEquals("TruthOrLieQuiz1", json.get("application_state").get("quizName").asString())
        assertEquals(1, json.get("session_state").get("currentQuestion").asInt())
        assertEquals(0, json.get("session_state").get("rightAnswersCount").asInt())
    }
}

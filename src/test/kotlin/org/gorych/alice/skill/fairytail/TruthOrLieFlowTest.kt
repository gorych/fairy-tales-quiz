package org.gorych.alice.skill.fairytail

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import tools.jackson.module.kotlin.jacksonObjectMapper

private fun selectTruthOrLieRequest() = """
{
  "session": {"new": false},
  "request": {
    "command": "правда или ложь",
    "original_utterance": "правда или ложь",
    "nlu": {"tokens": ["правда", "или", "ложь"], "entities": [], "intents": {"g911.mode_truth": {"slots": {}}}},
    "type": "SimpleUtterance"
  },
  "state": {
    "session": {"transitionCommands": ["GameModeSelectionCommand", "ModesHelpCommand"]},
    "application": {}
  },
  "version": "1.0"
}
"""

private fun answerRequest(command: String, intentId: String) = """
{
  "session": {"new": false},
  "request": {
    "command": "$command",
    "original_utterance": "$command",
    "nlu": {"tokens": ["$command"], "entities": [], "intents": {"$intentId": {"slots": {}}}},
    "type": "SimpleUtterance"
  },
  "state": {
    "session": {"currentQuestion": 1, "transitionCommands": ["NextQuestionCommand"]},
    "application": {"quizName": "TruthOrLieQuiz1", "bonusQuiz": false, "gameMode": "TRUE_OR_FALSE"}
  },
  "version": "1.0"
}
"""

class TruthOrLieFlowTest {

    private val mapper = jacksonObjectMapper()

    @Test
    fun `WHEN user selects the truth or lie mode THEN the first statement should be returned`() {
        val json = mapper.readTree(handle(selectTruthOrLieRequest()))

        val text = json.get("response").get("text").asString()
        val buttons = json.get("response").get("buttons").map { it.get("title").asString() }

        assertTrue(text.contains("Правда, что репку посадил дед?"))
        assertTrue(buttons.contains("✅ Правда"))
        assertTrue(buttons.contains("❌ Ложь"))
        assertEquals("TRUE_OR_FALSE", json.get("application_state").get("gameMode").asString())
        assertEquals("TruthOrLieQuiz1", json.get("application_state").get("quizName").asString())
        assertEquals(1, json.get("session_state").get("currentQuestion").asInt())
    }

    @Test
    fun `WHEN user answers correctly THEN the truth should be confirmed and the next statement returned`() {
        val json = mapper.readTree(handle(answerRequest("правда", "g911.truth")))

        val text = json.get("response").get("text").asString()

        assertTrue(arrayOf("Верно!", "Правильно!", "Точно!").any { text.contains(it) }, text)
        assertTrue(text.contains("Репку посадил дед"))
        assertTrue(text.contains("Правда, что колобка съел волк?"))
        assertEquals(2, json.get("session_state").get("currentQuestion").asInt())
        assertEquals(1, json.get("session_state").get("rightAnswersCount").asInt())
    }

    @Test
    fun `WHEN user answers incorrectly THEN the truth should be revealed and the statement kept`() {
        val json = mapper.readTree(handle(answerRequest("ложь", "g911.lie")))

        val text = json.get("response").get("text").asString()

        assertTrue(arrayOf("Неверно.", "Нет-нет.", "Не так.").any { text.contains(it) }, text)
        assertTrue(text.contains("Репку посадил дед"))
        assertEquals(1, json.get("session_state").get("currentQuestion").asInt())
        assertEquals(0, json.get("session_state").get("rightAnswersCount").asInt())
    }
}

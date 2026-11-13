package org.gorych.alice.skill.fairytail

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import tools.jackson.module.kotlin.jacksonObjectMapper

private const val FIRST_SOUND_ID = "f2fdb7f5-f211-4bc3-b1e2-4e37bfefb694"
private const val SECOND_SOUND_ID = "a51487db-cc48-42f1-9b4b-95a8a2229dcd"

private fun selectSoundModeRequest() = """
{
  "session": {"new": false},
  "request": {
    "command": "угадай по звуку",
    "original_utterance": "угадай по звуку",
    "nlu": {"tokens": ["угадай", "по", "звуку"], "entities": [], "intents": {"g911.mode_sound": {"slots": {}}}},
    "type": "SimpleUtterance"
  },
  "state": {
    "session": {"transitionCommands": ["GameModeSelectionCommand"]},
    "application": {}
  },
  "version": "1.0"
}
"""

private fun answerSoundRequest(command: String) = """
{
  "session": {"new": false},
  "request": {
    "command": "$command",
    "original_utterance": "$command",
    "nlu": {"tokens": ["$command"], "entities": [], "intents": {}},
    "type": "SimpleUtterance"
  },
  "state": {
    "session": {"currentQuestion": 1, "transitionCommands": ["NextQuestionCommand"]},
    "application": {"quizName": "SoundQuiz1", "bonusQuiz": false, "gameMode": "GUESS_SOUND"}
  },
  "version": "1.0"
}
"""

class GuessSoundFlowTest {

    private val mapper = jacksonObjectMapper()

    @Test
    fun `WHEN user selects the sound mode THEN the first audio should be played without any card`() {
        val json = mapper.readTree(handle(selectSoundModeRequest()))

        val tts = json.get("response").get("tts").asString()

        assertTrue(tts.contains("dialogs-upload/8070abca-f39f-40ec-bc59-377070bed8b8/$FIRST_SOUND_ID.opus"), tts)
        assertTrue(tts.contains("Угадай сказку по звукам."))
        assertNull(json.get("response").get("card"))
        assertEquals("GUESS_SOUND", json.get("application_state").get("gameMode").asString())
        assertEquals("SoundQuiz1", json.get("application_state").get("quizName").asString())
    }

    @Test
    fun `WHEN user names the tale right THEN the next audio should be played`() {
        val json = mapper.readTree(handle(answerSoundRequest("репка")))

        val tts = json.get("response").get("tts").asString()

        assertTrue(tts.contains("$SECOND_SOUND_ID.opus"), tts)
        assertEquals(2, json.get("session_state").get("currentQuestion").asInt())
        assertEquals(1, json.get("session_state").get("rightAnswersCount").asInt())
    }

    @Test
    fun `WHEN user names the wrong tale THEN the same question should be kept`() {
        val json = mapper.readTree(handle(answerSoundRequest("колобок")))

        val text = json.get("response").get("text").asString()

        assertTrue(arrayOf("Неверно.", "Не узнал?", "Нет.").any { text.contains(it) }, text)
        assertEquals(1, json.get("session_state").get("currentQuestion").asInt())
        assertEquals(0, json.get("session_state").get("rightAnswersCount").asInt())
    }
}

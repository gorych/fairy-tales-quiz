package org.gorych.alice.skill.core.quiz

import org.gorych.alice.skill.core.api.Request
import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.mode.GameMode
import org.gorych.alice.skill.core.soundTag
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

private class TestSoundQuiz : SoundQuiz() {
    override val items = listOf(
        SoundQuestion(
            number = 1,
            soundId = "sound-id-1",
            answers = listOf("репка"),
            hints = listOf("Подсказка 1"),
        ),
        SoundQuestion(
            number = 2,
            soundId = "sound-id-2",
            answers = listOf("телефон"),
            hints = listOf(),
        ),
    )
}

class SoundQuizTest {

    private val quiz = TestSoundQuiz()

    @Test
    fun `WHEN constructed THEN game mode should be guess sound`() {
        assertEquals(GameMode.GUESS_SOUND, quiz.gameMode)
        assertFalse(quiz.bonusQuiz)
        assertEquals(2, quiz.countOfQuestions())
    }

    @Test
    fun `WHEN prompt is built THEN it should play the audio and ask about the tale`() {
        val prompt = quiz.prompt(1, "Слушай.")

        assertEquals("Слушай. Угадай сказку по звукам.", prompt.text)
        assertEquals("Слушай. ${soundTag("sound-id-1")} Угадай сказку по звукам.", prompt.tts)
        assertNull(prompt.card)
    }

    @Test
    fun `WHEN the user names the tale THEN the answer should be correct`() {
        assertTrue(quiz.isCorrectAnswer(RequestObject(Request(command = "репка")), 1))
        assertFalse(quiz.isCorrectAnswer(RequestObject(Request(command = "колобок")), 1))
    }

    @Test
    fun `WHEN hint number fits the hints THEN the hint should be returned`() {
        assertEquals("Подсказка 1", quiz.hintTo(1, 1))
    }

    @Test
    fun `WHEN hints are exhausted THEN the right answer should be returned`() {
        assertEquals("Правильный ответ - репка.", quiz.hintTo(1, 2))
        assertEquals("Правильный ответ - телефон.", quiz.hintTo(2, 1))
    }
}

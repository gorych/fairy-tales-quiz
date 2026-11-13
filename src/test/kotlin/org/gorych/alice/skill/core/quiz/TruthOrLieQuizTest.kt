package org.gorych.alice.skill.core.quiz

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.of
import org.gorych.alice.skill.core.mode.GameMode
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

private class TestTruthOrLieQuiz : TruthOrLieQuiz() {
    override val items = listOf(
        TruthStatement(
            number = 1,
            statement = "Правда, что репку посадил дед?",
            isTrue = true,
            explanation = "Репку посадил дед.",
            hints = listOf("Подсказка 1"),
        ),
        TruthStatement(
            number = 2,
            statement = "Правда, что колобка съел волк?",
            isTrue = false,
            explanation = "Колобка съела лиса.",
            hints = listOf(),
        ),
    )
}

class TruthOrLieQuizTest {

    private val quiz = TestTruthOrLieQuiz()

    @Test
    fun `WHEN constructed THEN game mode should be true or false`() {
        assertEquals(GameMode.TRUE_OR_FALSE, quiz.gameMode)
        assertFalse(quiz.bonusQuiz)
        assertEquals(2, quiz.countOfQuestions())
    }

    @Test
    fun `WHEN the statement is true and the user says truth THEN the answer should be correct`() {
        assertTrue(quiz.isCorrectAnswer(RequestObject.of("g911.truth"), 1))
        assertFalse(quiz.isCorrectAnswer(RequestObject.of("g911.lie"), 1))
    }

    @Test
    fun `WHEN the statement is false and the user says lie THEN the answer should be correct`() {
        assertTrue(quiz.isCorrectAnswer(RequestObject.of("g911.lie"), 2))
        assertFalse(quiz.isCorrectAnswer(RequestObject.of("g911.truth"), 2))
    }

    @Test
    fun `WHEN there is neither truth nor lie intent THEN the answer should not be correct`() {
        assertFalse(quiz.isCorrectAnswer(RequestObject.of("g911.greeting"), 1))
        assertFalse(quiz.isCorrectAnswer(RequestObject(), 1))
    }

    @Test
    fun `WHEN prompt is built THEN it should contain the statement and no card`() {
        val prompt = quiz.prompt(1, "Слушай утверждение.")

        assertEquals("Слушай утверждение. Правда, что репку посадил дед?", prompt.text)
        assertEquals("Слушай утверждение. Правда, что репку посадил дед?", prompt.tts)
        assertNull(prompt.card)
    }

    @Test
    fun `WHEN hint number fits the hints THEN the hint should be returned`() {
        assertEquals("Подсказка 1", quiz.hintTo(1, 1))
    }

    @Test
    fun `WHEN hints are exhausted THEN the right answer should be returned`() {
        assertEquals("Правильный ответ - правда.", quiz.hintTo(1, 2))
        assertEquals("Правильный ответ - ложь.", quiz.hintTo(2, 1))
    }

    @Test
    fun `WHEN the answer is checked THEN the explanation should be returned as feedback`() {
        assertEquals("Репку посадил дед.", quiz.correctAnswerNote(1))
        assertEquals("Колобка съела лиса.", quiz.wrongAnswerNote(2))
    }
}

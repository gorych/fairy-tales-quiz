package org.gorych.alice.skill.fairytail.quiz

import org.gorych.alice.skill.core.quiz.sound.SoundQuestion
import org.gorych.alice.skill.fairytail.quiz.sound.SoundQuiz1
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SoundQuiz1Test {

    private val quiz = SoundQuiz1()

    @Test
    fun `questions should be numbered sequentially`() {
        assertEquals((1..quiz.countOfQuestions()).toList(), quiz.items.map(SoundQuestion::number))
    }

    @Test
    fun `every question should reference an uploaded sound`() {
        quiz.items.forEach { assertTrue(it.soundId.isNotBlank(), "Question ${it.number}") }
    }

    @Test
    fun `every question should have answers`() {
        quiz.items.forEach { assertTrue(it.answers.isNotEmpty(), "Question ${it.number}") }
    }

    @Test
    fun `answers should have no blank or padded entries`() {
        quiz.items.forEach { question ->
            question.answers.forEach { answer ->
                assertEquals(answer.trim(), answer, "Question ${question.number}")
                assertTrue(answer.isNotBlank(), "Question ${question.number}")
            }
        }
    }

    @Test
    fun `every question should have at least one hint`() {
        quiz.items.forEach { assertTrue(it.hints.isNotEmpty(), "Question ${it.number}") }
    }

    @Test
    fun `it should contain the uploaded tales`() {
        assertEquals(5, quiz.countOfQuestions())
    }
}

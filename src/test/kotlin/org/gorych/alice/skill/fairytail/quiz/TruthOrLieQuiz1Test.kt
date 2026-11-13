package org.gorych.alice.skill.fairytail.quiz

import org.gorych.alice.skill.core.quiz.truth_or_lie.TruthStatement
import org.gorych.alice.skill.fairytail.quiz.truth_or_lie.TruthOrLieQuiz1
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TruthOrLieQuiz1Test {

    private val quiz = TruthOrLieQuiz1()

    @Test
    fun `questions should be numbered sequentially`() {
        assertEquals((1..quiz.countOfQuestions()).toList(), quiz.items.map(TruthStatement::number))
    }

    @Test
    fun `every statement should have a non blank explanation`() {
        quiz.items.forEach { assertTrue(it.explanation.isNotBlank(), "Statement ${it.number}") }
    }

    @Test
    fun `every statement should have a non blank text`() {
        quiz.items.forEach { assertTrue(it.statement.isNotBlank(), "Statement ${it.number}") }
    }

    @Test
    fun `there should be both true and false statements`() {
        assertTrue(quiz.items.any { it.isTrue })
        assertTrue(quiz.items.any { !it.isTrue })
    }

    @Test
    fun `it should have a known count of statements`() {
        assertEquals(12, quiz.countOfQuestions())
    }
}

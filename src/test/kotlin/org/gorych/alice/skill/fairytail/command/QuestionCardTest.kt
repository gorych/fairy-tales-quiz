package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.Request
import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.SessionState
import org.gorych.alice.skill.core.api.State
import org.gorych.alice.skill.core.api.of
import org.gorych.alice.skill.core.quiz.Quiz
import org.gorych.alice.skill.core.quiz.QuizItem
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

private class QuizWithImages : Quiz() {
    override val items = listOf(
        QuizItem(1, "Кто посадил репку?", listOf("дед"), listOf("Он живёт с бабкой."), imageId = "image-1"),
        QuizItem(2, "Кто съел колобка?", listOf("лиса"), listOf("Она рыжая."), imageId = "image-2")
    )
}

private class QuizWithoutImages : Quiz() {
    override val items = listOf(
        QuizItem(1, "Кто посадил репку?", listOf("дед"), listOf("Он живёт с бабкой.")),
        QuizItem(2, "Кто съел колобка?", listOf("лиса"), listOf("Она рыжая."))
    )
}

class QuestionCardTest {

    @Test
    fun `repeat question should return card with image of the current question`() {
        val request = RequestObject.of(
            "g911.repeat",
            sessionState = SessionState(currentQuestion = 1, transitionCommands = setOf(NextQuestionCommand.name()))
        )

        val response = RepeatQuestionCommand().execute(request, QuizWithImages())

        val card = response.response.card
        assertNotNull(card)
        assertEquals("image-1", card?.imageId)
        assertEquals("1. Кто посадил репку?", card?.description)
        assertTrue(RepeatQuestionCommand.BEFORE_QUESTION_PHRASES.contains(card?.title))
        assertTrue(response.response.text.contains("Кто посадил репку?"))
    }

    @Test
    fun `repeat question should not return card when question has no image`() {
        val request = RequestObject.of(
            "g911.repeat",
            sessionState = SessionState(currentQuestion = 1, transitionCommands = setOf(NextQuestionCommand.name()))
        )

        val response = RepeatQuestionCommand().execute(request, QuizWithoutImages())

        assertNull(response.response.card)
    }

    @Test
    fun `skip question should return card with image of the next question`() {
        val request = RequestObject.of(
            "g911.skip",
            sessionState = SessionState(currentQuestion = 1, transitionCommands = setOf(NextQuestionCommand.name()))
        )

        val response = SkipQuestionCommand().execute(request, QuizWithImages())

        val card = response.response.card
        assertNotNull(card)
        assertEquals("image-2", card?.imageId)
        assertEquals("2. Кто съел колобка?", card?.description)
    }

    @Test
    fun `first question should return card with image of the first question`() {
        val request = RequestObject.of(
            "g911.agreement",
            sessionState = SessionState(setOf(PlayingAgreementCommand.name()))
        )

        val response = PlayingAgreementCommand().execute(request, QuizWithImages())

        val card = response.response.card
        assertNotNull(card)
        assertEquals("image-1", card?.imageId)
        assertEquals("1. Кто посадил репку?", card?.description)
    }

    @Test
    fun `right answer should return card with image of the next question`() {
        val request = RequestObject(
            request = Request(command = "дед"),
            state = State(
                SessionState(currentQuestion = 1, transitionCommands = setOf(NextQuestionCommand.name())),
                null
            )
        )

        val response = NextQuestionCommand().execute(request, QuizWithImages())

        val card = response.response.card
        assertNotNull(card)
        assertEquals("image-2", card?.imageId)
        assertEquals("2. Кто съел колобка?", card?.description)
        assertTrue(response.response.text.contains("Кто съел колобка?"))
    }

    @Test
    fun `achievement should return achievement text`() {
        val request = RequestObject(
            request = Request(command = "дед"),
            state = State(
                SessionState(
                    currentQuestion = 1,
                    rightAnswersCount = 4,
                    transitionCommands = setOf(NextQuestionCommand.name())
                ),
                null
            )
        )

        val response = NextQuestionCommand().execute(request, QuizWithImages())

        assertTrue(response.response.text.contains("достижение"))
    }
}

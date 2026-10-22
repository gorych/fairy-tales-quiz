package org.gorych.alice.skill.core.api

import io.kotest.assertions.json.shouldContainJsonKey
import org.gorych.alice.skill.core.quiz.Quiz
import org.gorych.alice.skill.core.quiz.QuizItem
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import tools.jackson.module.kotlin.jacksonObjectMapper

private class QuizWithImage : Quiz() {
    override val items = listOf(
        QuizItem(1, "Кто посадил репку?", listOf("дед"), listOf("Он живёт с бабкой."), imageId = "image-1")
    )
}

private class QuizWithoutImage : Quiz() {
    override val items = listOf(
        QuizItem(1, "Кто посадил репку?", listOf("дед"), listOf("Он живёт с бабкой."))
    )
}

class ResponseObjectTest {

    private val mapper = jacksonObjectMapper()

    @Test
    fun `should not serialize card when there is no image`() {
        //when
        val json = mapper.writeValueAsString(ResponseValue("Текст ответа", endSession = false))

        //then
        assertFalse(json.contains("\"card\""), "Card must be omitted when it is absent: $json")
        json.shouldContainJsonKey("$.text")
    }

    @Test
    fun `should serialize card as BigImage`() {
        //when
        val json = mapper.writeValueAsString(
            ResponseValue(
                text = "Текст ответа",
                endSession = false,
                card = BigImageCard(imageId = "id-1", title = "Верно!", description = "Слушай следующий вопрос.")
            )
        )

        //then
        json.shouldContainJsonKey("$.text")
        json.shouldContainJsonKey("$.card.type")
        json.shouldContainJsonKey("$.card.image_id")
        json.shouldContainJsonKey("$.card.title")
        json.shouldContainJsonKey("$.card.description")
    }

    @Test
    fun `question response should contain card built from the question`() {
        //when
        val response = ResponseObject.ofQuestion(
            quiz = QuizWithImage(),
            questionNumber = 1,
            phrase = "Слушай вопрос.",
            buttons = emptyList(),
            sessionState = SessionState(),
            endSession = false
        )

        //then
        val card = response.response.card
        assertNotNull(card)
        assertEquals("image-1", card?.imageId)
        assertEquals("Слушай вопрос.", card?.title)
        assertEquals("1. Кто посадил репку?", card?.description)
        assertEquals("Слушай вопрос. Кто посадил репку?", response.response.text)
        assertEquals("Слушай вопрос. Кто посадил репку?", response.response.tts)
    }

    @Test
    fun `question response should not contain card when question has no image`() {
        //when
        val response = ResponseObject.ofQuestion(
            quiz = QuizWithoutImage(),
            questionNumber = 1,
            phrase = "Слушай вопрос.",
            buttons = emptyList(),
            sessionState = SessionState(),
            endSession = false
        )

        //then
        assertNull(response.response.card)
        assertEquals("Слушай вопрос. Кто посадил репку?", response.response.text)
    }
}

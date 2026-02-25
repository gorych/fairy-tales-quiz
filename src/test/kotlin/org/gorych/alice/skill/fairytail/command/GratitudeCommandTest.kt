package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.*
import org.gorych.alice.skill.core.command.GratitudeCommand
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GratitudeCommandTest {

    @Test
    fun `WHEN name call THEN should return class simple name`() {
        //given
        val command = GratitudeCommand()
        val expected = GratitudeCommand::class.java.simpleName

        //when
        val actual = command.name()

        //then
        assertEquals(expected, actual)
        assertEquals(command.name(), GratitudeCommand.name())
    }

    @ParameterizedTest(name = "Should return {0} when intents {2}")
    @CsvSource(
        "true,  g911.gratitude,              contain 'g911.gratitude' value",
        "true,  g911.gratitude&g911.parting, contain 'g911.gratitude' and 'g911.parting' values",
        "false, g911.agreement,              don't contain 'g911.gratitude' value",
        "false, empty,                       are empty",
    )
    fun `WHEN canHandle call THEN should return expected boolean`(
        expectedValue: Boolean,
        key: String,
        whenDescription: String
    ) {
        //given
        val command = GratitudeCommand()
        val requestObject = RequestObject.getByIntentKey(key)

        //when
        val result = command.canHandle(requestObject)

        //then
        assertEquals(expectedValue, result)
    }

    @ParameterizedTest(name = "Should return one of gratitude phrases when NLU tokens {1}")
    @CsvSource(
        value = [
            "спасибо;                      true;  contain 'спасибо' value",
            "благодарю;                    false; contain 'благодарю' value",
            "спасибочки;                   false; contain 'спасибочки' value",
            "большое_спасибо;              false; contain 'большое спасибо' value",
            "спасибо&благодарю&спасибочки; true;  contain 'спасибо благодарю спасибочки' value",
            "special_characters;           false; contain special characters only",
            "empty;                        false; don't contain any value",
            "empty&session_state;          true;  don't contain any value but has session state",
            "null;                         false; are NULL"
        ],
        delimiter = ';'
    )
    fun `WHEN execute call THEN should return expected text`(
        key: String,
        hasState: Boolean,
        whenDescription: String
    ) {
        //given
        val notEmptySessionState = SessionState(
            currentQuestion = 2,
            transitionCommands = setOf("PlayingAgreementCommand")
        )
        val expectedState = if (hasState) notEmptySessionState else SessionState()
        val command = GratitudeCommand()
        val requestObject = RequestObject.getByNluTokenKey(key)

        //when
        val result: ResponseObject = command.execute(requestObject)

        //then
        assertEquals(expectedState.currentQuestion, result.sessionState.currentQuestion)
        assertEquals(expectedState.transitionCommands, result.sessionState.transitionCommands)

        val hastLeastOneMatchingText: Boolean = arrayOf("И тебе, спасибо!", "Не за что!", "Как приятно это слышать!")
            .map { result.response.text.contains(it, ignoreCase = true) }
            .any { it }
        assertTrue(hastLeastOneMatchingText)
    }
}
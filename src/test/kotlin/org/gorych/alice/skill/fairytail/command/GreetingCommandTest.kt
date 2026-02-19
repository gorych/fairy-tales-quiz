package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.Test
import kotlin.test.assertEquals

class GreetingCommandTest {

    @Test
    fun `WHEN name call THEN should return class simple name`() {
        //given
        val command = GreetingCommand()
        val expected = GreetingCommand::class.java.simpleName

        //when
        val actual = command.name()

        //then
        assertEquals(expected, actual)
        assertEquals(command.name(), GreetingCommand.name())
    }

    @ParameterizedTest(name = "Should return {0} when intents {2}")
    @CsvSource(
        "true,  g911.greeting,              contain 'g911.greeting' value",
        "true,  g911.greeting&g911.parting, contain 'g911.greeting' and 'g911.parting' values",
        "false, g911.agreement,             don't contain 'g911.greeting' value",
        "false, empty,                      are empty",
    )
    fun `WHEN canHandle call THEN should return expected boolean`(
        expectedValue: Boolean,
        key: String,
        whenDescription: String
    ) {
        //given
        val command = GreetingCommand()
        val requestObject = RequestObject.Companion.getByIntentKey(key)

        //when
        val result = command.canHandle(requestObject)

        //then
        assertEquals(expectedValue, result)
    }

    @ParameterizedTest(name = "Should return {0} when NLU tokens {2}")
    @CsvSource(
        value = [
            "И тебе, привет!;              true;  привет;                           contain 'привет' value",
            "И тебе, здравствуй!;          false; здравствуй;                       contain 'здравствуй' value",
            "И вам, здравствуйте!;         false; здравствуйте;                     contain 'здравствуйте' value",
            "И вам доброго времени суток!; true;  здрАВСТвуйте;                     contain 'здрАВСТвуйте' value",
            "И вам доброго времени суток!; true;  hello;                            contain 'hello' value",
            "И тебе, привет!;              false; привет&здравствуй&здравствуйте;   contain 'привет, здравствуй, здравствуйте' values",
            "И вам доброго времени суток!; true;  special_characters&session_state; contain special characters only but has session state",
            "И вам доброго времени суток!; false; empty;                            don't contain any value",
            "И вам доброго времени суток!; false; null;                             are NULL"],
        delimiter = ';'
    )
    fun `WHEN execute call THEN should return expected text`(
        expectedValue: String,
        hasState: Boolean,
        key: String,
        whenDescription: String
    ) {
        //given
        val notEmptySessionState = SessionState(
            currentQuestion = 7,
            transitionCommands = setOf("NextQuestionCommand")
        )
        val expectedState = if (hasState) notEmptySessionState else SessionState()
        val command = GreetingCommand()
        val requestObject = RequestObject.Companion.getByNluTokenKey(key)

        //when
        val result: ResponseObject = command.execute(requestObject)

        //then
        assertEquals(false, result.response.endSession)
        assertEquals(expectedValue, result.response.text)

        assertEquals(expectedState.currentQuestion, result.sessionState.currentQuestion)
        assertEquals(expectedState.transitionCommands, result.sessionState.transitionCommands)
    }
}
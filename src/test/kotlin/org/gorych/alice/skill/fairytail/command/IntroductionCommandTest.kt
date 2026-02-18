package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.api.getBySessionKey
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.Test
import kotlin.test.assertEquals

class IntroductionCommandTest {

    @Test
    fun `WHEN name call THEN should return class simple name`() {
        //given
        val command = IntroductionCommand()
        val expected = IntroductionCommand::class.java.simpleName

        //when
        val actual = command.name()

        //then
        assertEquals(expected, actual)
        assertEquals(command.name(), IntroductionCommand.name())
    }

    @ParameterizedTest(name = "Should return {0} when session {2}")
    @CsvSource(
        "true,  new,     is new",
        "false, not_new, is not new",
        "false, null,    is NULL",
    )
    fun `WHEN canHandle call THEN should return expected boolean`(
        expectedValue: Boolean,
        key: String,
        whenDescription: String
    ) {
        //given
        val command = IntroductionCommand()
        val requestObject = RequestObject.Companion.getBySessionKey(key)

        //when
        val result = command.canHandle(requestObject)

        //then
        assertEquals(expectedValue, result)
    }

    @ParameterizedTest(name = "Should return introduction text when session is {1}")
    @CsvSource(
        "new,     new",
        "not_new, not new",
        "null,    NULL",
    )
    fun `WHEN execute call THEN should return expected text`(
        key: String,
        whenDescription: String
    ) {
        //given
        val command = IntroductionCommand()
        val requestObject = RequestObject.Companion.getBySessionKey(key)

        //when
        val result: ResponseObject = command.execute(requestObject)

        //then
        val expectedValue = "Привет! Я предлагаю проверить насколько хорошо ты знаешь сказки. Поиграем?"
        assertEquals(expectedValue, result.response.text)
        assertEquals(false, result.response.endSession)
    }

}
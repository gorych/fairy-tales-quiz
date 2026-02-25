package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.api.getByIntentKey
import org.gorych.alice.skill.core.api.getByNluTokenKey
import org.gorych.alice.skill.core.command.StopCommand
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.Test
import kotlin.test.assertEquals

class StopCommandTest {

    @Test
    fun `WHEN name call THEN should return class simple name`() {
        //given
        val command = StopCommand()
        val expected = StopCommand::class.java.simpleName

        //when
        val actual = command.name()

        //then
        assertEquals(expected, actual)
        assertEquals(command.name(), StopCommand.name())
    }


    @ParameterizedTest(name = "Should return {0} when intents {2}")
    @CsvSource(
        "true,  g911.stop,               contain 'g911.parting' value",
        "true,  g911.stop&g911.parting,  contain 'g911.parting' and 'g911.greeting' values",
        "false, g911.agreement,          don't contain 'g911.parting' value",
        "false, empty,                   are empty",
    )
    fun `WHEN canHandle call THEN should return expected boolean`(
        expectedValue: Boolean,
        key: String,
        whenDescription: String
    ) {
        //given
        val command = StopCommand()
        val requestObject = RequestObject.getByIntentKey(key)

        //when
        val result = command.canHandle(requestObject)

        //then
        assertEquals(expectedValue, result)
    }

    @ParameterizedTest(name = "Should return correct phrase when NLU tokens {1}")
    @CsvSource(
        value = [
            "стоп;               contain 'стоп' value",
            "хватит;             contain 'хватит' value",
            "special_characters; contain special characters only",
            "empty;              don't contain any value",
            "null;               are NULL",
        ],
        delimiter = ';'
    )
    fun `WHEN execute call THEN should return expected text`(
        key: String,
        whenDescription: String
    ) {
        //given
        val command = StopCommand()
        val requestObject = RequestObject.getByNluTokenKey(key)

        //when
        val result: ResponseObject = command.execute(requestObject)

        //then
        assertEquals(true, result.response.endSession)
        assertEquals("Поняла. Если станет скучно - заходи!", result.response.text)
    }

}
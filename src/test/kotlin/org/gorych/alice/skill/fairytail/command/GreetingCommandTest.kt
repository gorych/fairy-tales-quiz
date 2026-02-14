package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.getByIntentKey
import org.gorych.alice.skill.core.api.getByNluTokenKey
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.net.URLDecoder
import java.nio.charset.StandardCharsets.UTF_8
import kotlin.test.assertEquals

class GreetingCommandTest {

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
        "И тебе%2C привет!,            привет,                         contain 'привет' value",
        "И тебе%2C здравствуй!,        здравствуй,                     contain 'здравствуй' value",
        "И вам%2C здравствуйте!,       здравствуйте,                   contain 'здравствуйте' value",
        "И вам доброго времени суток!, здрАВСТвуйте,                   contain 'здрАВСТвуйте' value",
        "И вам доброго времени суток!, hello,                          contain 'hello' value",
        "И тебе%2C привет!,            привет&здравствуй&здравствуйте, contain 'привет, здравствуй, здравствуйте' values",
        "И вам доброго времени суток!, special_characters,             contain special characters only",
        "И вам доброго времени суток!, empty,                          don't contain any value",
        "И вам доброго времени суток!, null,                           are NULL",
    )
    fun `WHEN execute call THEN should return expected text`(
        expectedValue: String,
        key: String,
        whenDescription: String
    ) {
        //given
        val command = GreetingCommand()
        val requestObject = RequestObject.Companion.getByNluTokenKey(key)

        //when
        val result = command.execute(requestObject)

        //then
        assertEquals(URLDecoder.decode(expectedValue, UTF_8.name()), result)
    }
}
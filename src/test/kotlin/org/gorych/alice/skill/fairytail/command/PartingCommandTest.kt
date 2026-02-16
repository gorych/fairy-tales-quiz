package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.getByIntentKey
import org.gorych.alice.skill.core.api.getByNluTokenKey
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertEquals

class PartingCommandTest {

    @ParameterizedTest(name = "Should return {0} when intents {2}")
    @CsvSource(
        "true,  g911.parting,               contain 'g911.parting' value",
        "true,  g911.greeting&g911.parting, contain 'g911.parting' and 'g911.greeting' values",
        "false, g911.agreement,             don't contain 'g911.parting' value",
        "false, empty,                      are empty",
    )
    fun `WHEN canHandle call THEN should return expected boolean`(
        expectedValue: Boolean,
        key: String,
        whenDescription: String
    ) {
        //given
        val command = PartingCommand()
        val requestObject = RequestObject.Companion.getByIntentKey(key)

        //when
        val result = command.canHandle(requestObject)

        //then
        assertEquals(expectedValue, result)
    }

    @ParameterizedTest(name = "Should return {0} when NLU tokens {2}")
    @CsvSource(
        value = [
            "Пока. Заходи еще!;                    пока;               contain 'пока' value",
            "Прощай. Надеюсь, тебе понравилось!;   прощай;             contain 'прощай' value",
            "До новых встреч!;                     прОЩай;             contain 'прОЩай' value",
            "До новых встреч!;                     bye;                contain 'bye' value",
            "Пока. Заходи еще!;                    пока&прощай;        contain 'пока, прощай' values",
            "До новых встреч!;                     special_characters; contain special characters only",
            "До новых встреч!;                     empty;              don't contain any value",
            "До новых встреч!;                     null;               are NULL", ],
        delimiter = ';'
    )
    fun `WHEN execute call THEN should return expected text`(
        expectedValue: String,
        key: String,
        whenDescription: String
    ) {
        //given
        val command = PartingCommand()
        val requestObject = RequestObject.Companion.getByNluTokenKey(key)

        //when
        val result = command.execute(requestObject)

        //then
        assertEquals(expectedValue, result)
    }

}
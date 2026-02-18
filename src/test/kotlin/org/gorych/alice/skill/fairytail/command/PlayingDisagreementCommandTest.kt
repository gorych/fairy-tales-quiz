package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.api.getByIntentKey
import org.gorych.alice.skill.core.api.getBySessionStateKey
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.Test
import kotlin.test.assertEquals

class PlayingDisagreementCommandTest {

    @Test
    fun `WHEN name call THEN should return class simple name`() {
        //given
        val command = PlayingDisagreementCommand()
        val expected = PlayingDisagreementCommand::class.java.simpleName

        //when
        val actual = command.name()

        //then
        assertEquals(expected, actual)
        assertEquals(PlayingDisagreementCommand.name(), command.name())
    }

    @ParameterizedTest(name = "Should return {0} when intents {2}")
    @CsvSource(
        "true,  g911.disagreement,                contain 'g911.disagreement' value",
        "true,  g911.disagreement&g911.agreement, contain 'g911.disagreement' and 'g911.agreement' values",
        "false, g911.agreement,                   don't contain 'g911.disagreement' value",
        "false, empty,                            are empty",
    )
    fun `WHEN canHandle call THEN should return expected boolean`(
        expectedValue: Boolean,
        key: String,
        whenDescription: String
    ) {
        //given
        val command = PlayingDisagreementCommand()
        val requestObject = RequestObject.getByIntentKey(key)

        //when
        val actual = command.canHandle(requestObject)

        //then
        assertEquals(expectedValue, actual)
    }

    @ParameterizedTest(name = "Should return {2} when session state {3}")
    @CsvSource(
        value = [
            "disagreement_command&current_question;         false; Я вас не поняла, повторите, пожалуйста.; contains 'disagreement' transition command and current question is NOT NULL",
            "disagreement_command&current_question_is_null; true;  Жаль! А так хотелось поиграть. Если станет скучно, ты знаешь как меня найти; contains 'disagreement' transition command but current question is NULL",
            "agreement_command&current_question;            false; Я вас не поняла, повторите, пожалуйста.; doesn't contain 'disagreement' transition command but current question is NOT NULL",
        ],
        delimiter = ';'
    )
    fun `WHEN execute call THEN should return expected text`(
        key: String,
        expectedSessionFlag: Boolean,
        expectedText: String,
        whenDescription: String
    ) {
        //given
        val command = PlayingDisagreementCommand()
        val requestObject = RequestObject.getBySessionStateKey(key)

        //when
        val actual: ResponseObject = command.execute(requestObject)

        //then
        assertEquals(expectedText, actual.response.text)
        assertEquals(expectedSessionFlag, actual.response.endSession)
    }
}
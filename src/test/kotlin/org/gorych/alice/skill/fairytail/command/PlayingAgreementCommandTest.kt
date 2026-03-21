package org.gorych.alice.skill.fairytail.command

import org.gorych.alice.skill.core.api.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PlayingAgreementCommandTest {

    @Test
    fun `WHEN name call THEN should return class simple name`() {
        //given
        val command = PlayingAgreementCommand()
        val expected = PlayingAgreementCommand::class.java.simpleName

        //when
        val actual = command.name()

        //then
        assertEquals(expected, actual)
        assertEquals(PlayingAgreementCommand.name(), command.name())
    }

    @ParameterizedTest(name = "Should return {0} when intents {2}")
    @CsvSource(
        "true,  g911.agreement,                   contain 'g911.agreement' value",
        "false, g911.disagreement,                don't contain 'g911.agreement' value",
        "true,  g911.disagreement&g911.agreement, contain 'g911.disagreement' and 'g911.agreement' values",
        "false, empty,                            are empty",
    )
    fun `WHEN canHandle call THEN should return expected boolean`(
        expectedValue: Boolean,
        key: String,
        whenDescription: String
    ) {
        //given
        val command = PlayingAgreementCommand()
        val requestObject = RequestObject.getByIntentKey(key)

        //when
        val actual = command.canHandle(requestObject)

        //then
        assertEquals(expectedValue, actual)
    }

    @ParameterizedTest(name = "Should return {2} when session state {3}")
    @CsvSource(
        value = [
            "agreement_command&current_question;         3; Слушай следующий вопрос.; contains 'playing agreement' transition command and current question is NOT NULL",
            "agreement_command&current_question_is_null; 1; Отлично. Слушай первый вопрос. Кто посадил репку?; contains 'playing agreement' transition command but current question is NULL",
        ],
        delimiter = ';'
    )
    fun `WHEN execute call THEN should return expected text`(
        key: String,
        expectedQuestionNumber: Int,
        expectedText: String,
        whenDescription: String
    ) {
        //given
        val command = PlayingAgreementCommand()
        val requestObject = RequestObject.getBySessionStateKey(key)
        val expectedState = SessionState(transitionCommands = setOf("NextQuestionCommand"))

        //when
        val actual: ResponseObject = command.execute(requestObject)

        //then
        assertFalse { actual.response.endSession }
        assertTrue { actual.response.text.contains(expectedText) }
        assertEquals(expectedQuestionNumber, actual.sessionState.currentQuestion)
        assertEquals(expectedState.transitionCommands, actual.sessionState.transitionCommands)
    }
}
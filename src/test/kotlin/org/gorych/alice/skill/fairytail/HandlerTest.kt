package org.gorych.alice.skill.fairytail

import io.kotest.assertions.json.shouldEqualJson
import org.gorych.alice.skill.util.readJsonResourceFile
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class HandlerTest {

    @ParameterizedTest(name = "Should return valid response when request {0}")
    @CsvSource(
        "is not valid,                              not-valid.json",
        "contains all valid fields,                 valid.all-fields-present.json",
        "contains case sensitive intent,            valid.case-sensitive-intent.json",
        "contains new session and supported intent, valid.new-session&supported-intent.json",
        "contains not supported intent,             valid.not-supported-intent.json",
        "doesn't contain NLUs,                      valid.null-nlu.json",
        "doesn't contain request,                   valid.null-request.json",
        "doesn't contain session,                   valid.null-session.json",
        "contains several supported intents,        valid.several-supported-intents.json",
    )
    fun `WHEN handle call THEN should return expected response in JSON format`(
        responseMappingKey: String,
        inputFileName: String,
    ) {
        //given
        val inputJson = readJsonResourceFile(this.javaClass, "handler/in/$inputFileName")
        val expectedJson = readJsonResourceFile(this.javaClass, "handler/out/$inputFileName")

        //when
        val actualJson = handle(inputJson)

        //then
        expectedJson.shouldEqualJson(actualJson)
    }
}
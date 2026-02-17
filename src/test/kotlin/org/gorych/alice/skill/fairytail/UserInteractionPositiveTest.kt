package org.gorych.alice.skill.fairytail

import io.kotest.assertions.json.shouldEqualJson
import org.gorych.alice.skill.util.readJsonResourceFile
import kotlin.test.Test

private const val TEST_RESOURCES_DIR = "user_interaction_positive_test"

class UserInteractionPositiveTest {

    @Test
    fun `WHEN session is new THEN introduction text should be returned`() {
        executeTest("step1-introduction.json")
    }

    @Test
    fun `WHEN user sends'yes' THEN first question should be returned`() {
        executeTest("step2-yes.json")
    }

    @Test
    fun `WHEN user sends right answer to question #1 THEN second question should be returned`() {
        executeTest("step3-answer1.json")
    }

    @Test
    fun `WHEN user sends right answer to question #2 THEN third question should be returned`() {
        executeTest("step4-answer2.json")
    }

    @Test
    fun `WHEN user sends right answer to question #3 THEN fourth question should be returned`() {
        executeTest("step5-answer3.json")
    }

    @Test
    fun `WHEN user sends right answer to question #4 THEN fifth question should be returned`() {
        executeTest("step6-answer4.json")
    }

    @Test
    fun `WHEN user sends right answer to question #5 THEN final phrase should be returned`() {
        executeTest("step7-answer5.json")
    }

    private fun executeTest(fileName: String) {
        //given
        val inputJson = readInputFile(fileName)
        val expectedJson = readOutputFile(fileName)

        //when
        val actualJson = handle(inputJson)

        //then
        expectedJson.shouldEqualJson(actualJson)
    }

    private fun readInputFile(fileName: String) =
        readJsonResourceFile(this.javaClass, "$TEST_RESOURCES_DIR/in/$fileName")

    private fun readOutputFile(fileName: String) =
        readJsonResourceFile(this.javaClass, "$TEST_RESOURCES_DIR/out/$fileName")
}
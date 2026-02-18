package org.gorych.alice.skill.fairytail

import io.kotest.assertions.json.shouldContainJsonKey
import io.kotest.assertions.json.shouldEqualJson
import io.kotest.assertions.json.shouldEqualSpecifiedJson
import org.gorych.alice.skill.util.readJsonResourceFile
import tools.jackson.module.kotlin.jacksonObjectMapper
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

private const val TEST_RESOURCES_DIR = "user_interaction_positive_test"

class UserInteractionTest {

    @Test
    fun `WHEN session is new THEN introduction text should be returned`() {
        shouldEqualJsonTest("step1-introduction.json")
    }

    @Test
    fun `WHEN user sends 'yes' THEN first question should be returned`() {
        shouldEqualJsonTest("step2-yes.json")
    }

    @Test
    fun `WHEN user sends'no' THEN regret phrase should be returned and session closed`() {
        shouldEqualJsonTest("step2-no.json")
    }

    @Test
    fun `WHEN user sends right answer to question #1 THEN second question should be returned`() {
        shouldEqualSpecifiedJsonAndContainText(
            fileName = "step3-answer1.json",
            expectedText = arrayOf("Кто съел бабушку красной шапочки?", "следующий вопрос"),
        )
    }

    @Test
    fun `WHEN user sends wrong answer to Q1 THEN 'think again' phrase should be returned and session state must be kept`() {
        //given
        val inputJson = readInputFile("step3-wrong-answer1.json")
        val expectedJson = readOutputFile("step3-wrong-answer1.json")

        //when
        val actualJson = handle(inputJson)

        //then
        actualJson.shouldEqualSpecifiedJson(expectedJson)
        actualJson.shouldContainJsonKey("$.response.text")

        val responseText = jacksonObjectMapper()
            .readTree(actualJson).get("response").get("text")
            .toString()

        val hastLeastOneMatchingText: Boolean = arrayOf("подумай", "попытайся", "попробуй")
            .map { responseText.contains(it, ignoreCase = true) }
            .any { it }
        assertTrue(hastLeastOneMatchingText)
    }

    @Test
    fun `WHEN user sends right answer to question #2 THEN third question should be returned`() {
        shouldEqualSpecifiedJsonAndContainText(
            fileName = "step4-answer2.json",
            expectedText = arrayOf("Кто испёк колобка?", "следующий вопрос"),
        )
    }

    @Test
    fun `WHEN user sends right answer to question #3 THEN fourth question should be returned`() {
        shouldEqualSpecifiedJsonAndContainText(
            fileName = "step5-answer3.json",
            expectedText = arrayOf("Как звали курочку, которая снесла золотое яичко?", "следующий вопрос"),
        )
    }

    @Test
    fun `WHEN user sends right answer to question #4 THEN fifth question should be returned`() {
        shouldEqualSpecifiedJsonAndContainText(
            fileName = "step6-answer4.json",
            expectedText = arrayOf("Как звали собаку в сказке репка?", "следующий вопрос"),
        )
    }

    @Test
    fun `WHEN user sends right answer to question #5 THEN final phrase should be returned`() {
        shouldEqualJsonTest("step7-answer5.json")
    }

    private fun shouldEqualJsonTest(fileName: String) {
        //given
        val inputJson = readInputFile(fileName)
        val expectedJson = readOutputFile(fileName)

        //when
        val actualJson = handle(inputJson)

        //then
        expectedJson.shouldEqualJson(actualJson)
    }

    private fun shouldEqualSpecifiedJsonAndContainText(fileName: String, vararg expectedText: String) {
        //given
        val inputJson = readInputFile(fileName)
        val expectedJson = readOutputFile(fileName)

        //when
        val actualJson = handle(inputJson)

        //then
        actualJson.shouldEqualSpecifiedJson(expectedJson)
        actualJson.shouldContainJsonKey("$.response.text")

        val responseText = jacksonObjectMapper()
            .readTree(actualJson).get("response").get("text")
            .toString()

        val hasNotMatchingText: Boolean = expectedText
            .map { responseText.contains(it, ignoreCase = true) }
            .any { !it }
        assertFalse(hasNotMatchingText)
    }

    private fun readInputFile(fileName: String) =
        readJsonResourceFile(this.javaClass, "$TEST_RESOURCES_DIR/in/$fileName")

    private fun readOutputFile(fileName: String) =
        readJsonResourceFile(this.javaClass, "$TEST_RESOURCES_DIR/out/$fileName")
}
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
    fun `WHEN user sends right answer to question #6 THEN seventh question should be returned`() {
        shouldEqualSpecifiedJsonAndContainText(
            fileName = "step8-answer6.json",
            expectedText = arrayOf("Кто съел колобка?", "следующий вопрос"),
        )
    }

    @Test
    fun `WHEN user sends right answer to question #7 THEN eighth question should be returned`() {
        shouldEqualSpecifiedJsonAndContainText(
            fileName = "step9-answer7.json",
            expectedText = arrayOf(
                "Как звали девочку с длинными волосами, которая была заперта в башне?",
                "следующий вопрос"
            ),
        )
    }

    @Test
    fun `WHEN user sends right answer to question #8 THEN ninth question should be returned`() {
        shouldEqualSpecifiedJsonAndContainText(
            fileName = "step10-answer8.json",
            expectedText = arrayOf("В каком рассказе главными героями были девочки Лиля и Ленка?", "следующий вопрос"),
        )
    }

    @Test
    fun `WHEN user sends right answer to question #9 THEN tenth question should be returned`() {
        shouldEqualSpecifiedJsonAndContainText(
            fileName = "step11-answer9.json",
            expectedText = arrayOf("Как звали доктора, который лечил всех зверей?", "следующий вопрос"),
        )
    }

    @Test
    fun `WHEN user sends right answer to question #10 THEN eleventh question should be returned`() {
        shouldEqualSpecifiedJsonAndContainText(
            fileName = "step12-answer10.json",
            expectedText = arrayOf("Как называется сказка о мальчике грязнуле?", "следующий вопрос"),
        )
    }

    @Test
    fun `WHEN user sends right answer to question #12 THEN twelfth question should be returned`() {
        shouldEqualSpecifiedJsonAndContainText(
            fileName = "step13-answer11.json",
            expectedText = arrayOf("От кого убегала вся посуда, мебель и вещи?", "следующий вопрос"),
        )
    }

    @Test
    fun `WHEN user sends right answer to question #13 THEN thirteenth question should be returned`() {
        shouldEqualSpecifiedJsonAndContainText(
            fileName = "step14-answer12.json",
            expectedText = arrayOf("Ниф-ниф, наф-наф, а кто третий?", "следующий вопрос"),
        )
    }

    @Test
    fun `WHEN user sends right answer to question #14 THEN fourteenth question should be returned`() {
        shouldEqualSpecifiedJsonAndContainText(
            fileName = "step15-answer13.json",
            expectedText = arrayOf("Кто помог зайцу выгнать лису в сказке заюшкина избушка?", "следующий вопрос"),
        )
    }

    @Test
    fun `WHEN user sends right answer to last THEN final phrase should be returned`() {
        shouldEqualJsonTest("step16-answer14.json")
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
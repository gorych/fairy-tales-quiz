package org.gorych.alice.skill

import com.fasterxml.jackson.annotation.JsonProperty
import tools.jackson.databind.JsonNode
import tools.jackson.module.kotlin.jacksonObjectMapper

val quiz = mapOf(
    Pair("Кто посадил репку?", listOf("дедка", "деда", "дед")),
    Pair("Кто съел бабушку красной шапочки?", listOf("волк")),
    Pair("Кто испёк колобка?", listOf("баба", "бабка", "бабушка")),
    Pair("Как звали курочку, которая снесла золотое яичко?", listOf("ряба")),
    Pair("Как звали собаку в сказке репка?", listOf("жучка")),
)

data class ResponseValue(
    val text: String,
    @JsonProperty("end_session") val endSession: Boolean,
)

data class Response(
    val response: ResponseValue,
    val version: String = "1.0",
) {
    companion object {
        fun of(text: String, endSession: Boolean): Response {
            return Response(ResponseValue(text, endSession))
        }
    }
}

fun handle(input: Map<String, Any>): String {
    val mapper = jacksonObjectMapper()

    val requestNode = mapper.valueToTree<JsonNode>(input)

    val command = requestNode["request"]?.get("command")?.asString()?.lowercase() ?: ""
    val isNewSession = requestNode["session"]?.get("new")?.asBoolean() ?: false

    val responseText = when {
        isNewSession -> "Привет. Я предлагаю проверить насколько хорошо ты знаешь сказки. Ты согласна?"
        command.contains("привет") -> "И тебе привет!"
        command.contains("пока") -> "До встречи!"
        else -> "Я вас не поняла, повторите, пожалуйста."
    }

    val response = mapper.writeValueAsString(
        Response.of(responseText, command.contains("пока"))
    )
    println("Response= $response")

    return response
}
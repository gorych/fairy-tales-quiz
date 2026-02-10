package org.gorych.alice.skill

import tools.jackson.databind.JsonNode
import tools.jackson.module.kotlin.jacksonObjectMapper

val quiz = mapOf(
    Pair("Кто посадил репку?", listOf("дедка", "деда", "дед")),
    Pair("Кто съел бабушку красной шапочки?", listOf("волк")),
    Pair("Кто испёк колобка?", listOf("баба", "бабка", "бабушка")),
    Pair("Как звали курочку, которая снесла золотое яичко?", listOf("ряба")),
    Pair("Как звали собаку в сказке репка?", listOf("жучка")),
)

fun handle(input: Map<String, Any>): Map<String, Any> {
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

    val responseMap = mapOf(
        "response" to mapOf(
            "text" to responseText,
            "tts" to responseText,
            "end_session" to command.contains("пока")
        ),
        "version" to "1.0.0"
    )

    return responseMap
}
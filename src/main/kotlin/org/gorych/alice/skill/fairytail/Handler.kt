package org.gorych.alice.skill.fairytail

import org.gorych.alice.skill.api.RequestObject
import org.gorych.alice.skill.api.ResponseObject
import tools.jackson.module.kotlin.jacksonObjectMapper
import tools.jackson.module.kotlin.readValue

fun handle(input: String): String {
    println("Raw input: $input")

    val mapper = jacksonObjectMapper()

    val requestObject: RequestObject = mapper.readValue(input)
    println("Request object: $requestObject")

    val command = requestObject.request?.command ?: ""
    val isNewSession = requestObject.session?.new ?: false

    /*
    * YANDEX.CONFIRM — согласие;
    YANDEX.REJECT — отказ;
    YANDEX.HELP — запрос подсказки;
    YANDEX.REPEAT — просьба повторить последний ответ навыка
    *
    * */

    val responseText = when {
        isNewSession -> "Привет! Я предлагаю проверить насколько хорошо ты знаешь сказки. Поиграем?"
        command.contains("привет") -> "И тебе привет!"
        command.contains("пока") -> "До встречи!"
        else -> "Я вас не поняла, повторите, пожалуйста."
    }

    val response = mapper.writeValueAsString(
        ResponseObject.of(responseText, command.contains("пока"))
    )
    println("Response object: $response")

    return response
}
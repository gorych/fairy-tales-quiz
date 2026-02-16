package org.gorych.alice.skill.fairytail

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.command.Command
import org.gorych.alice.skill.fairytail.command.GreetingCommand
import org.gorych.alice.skill.fairytail.command.IntroductionCommand
import org.gorych.alice.skill.fairytail.command.PartingCommand
import tools.jackson.module.kotlin.jacksonObjectMapper
import tools.jackson.module.kotlin.readValue
import java.lang.System.err

//The order is important here
private val commandRegistry: List<Command> = listOf(
    IntroductionCommand(),

    GreetingCommand(),
    PartingCommand()
)

fun handle(input: String): String {
    println("Raw input: $input")
    val mapper = jacksonObjectMapper()

    var response: String
    try {
        val requestObject: RequestObject = mapper.readValue(input)
        println("Request object: $requestObject")

        val responseObject = processRequest(requestObject)
        response = mapper.writeValueAsString(responseObject)

        return response
    } catch (t: Throwable) {
        err.println("Error while request processing. Exception= $t")
        response = mapper.writeValueAsString(
            ResponseObject.of("Извините, произошла техническая ошибка. Попробуйте еще раз.", false)
        )
    }

    println("Response object: $response")

    return response
}

private fun processRequest(requestObject: RequestObject): ResponseObject {
    requestObject.request?.nlu?.intents

    val command: Command? = commandRegistry.firstOrNull { it.canHandle(requestObject) }
    val responseObject = when {
        command != null -> {
            val responseText = command.execute(requestObject)
            ResponseObject.of(responseText, command.isClosingPhrase())
        }

        else -> {
            ResponseObject.of("Я вас не поняла, повторите, пожалуйста.", false)
        }
    }
    return responseObject
}
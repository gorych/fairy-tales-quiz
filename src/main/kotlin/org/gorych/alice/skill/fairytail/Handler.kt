package org.gorych.alice.skill.fairytail

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.command.Command
import org.gorych.alice.skill.fairytail.command.*
import tools.jackson.module.kotlin.jacksonObjectMapper
import tools.jackson.module.kotlin.readValue
import java.lang.System.err

//The order is important here
private val commandRegistry: List<Command> = listOf(
    IntroductionCommand(),

    GreetingCommand(),

    PlayingAgreementCommand(),
    PlayingDisagreementCommand(),

    GratitudeCommand(),

    RepeatQuestionCommand(),
    NextQuestionCommand(),

    StopCommand(),
    PartingCommand(),
)

fun handle(input: String): String {
    println("Raw input: $input")
    val mapper = jacksonObjectMapper()

    var requestObject = RequestObject()
    var response: String
    try {
        requestObject = mapper.readValue(input)
        println("Request object: $requestObject")

        val responseObject = processRequest(requestObject)
        response = mapper.writeValueAsString(responseObject)

        return response
    } catch (t: Throwable) {
        err.println("Error while request processing. Exception= $t")
        response = mapper.writeValueAsString(
            ResponseObject.ofTechnicalError(requestObject.state?.session)
        )
    }

    println("Response object: $response")

    return response
}

private fun processRequest(requestObject: RequestObject): ResponseObject {
    val command: Command? = commandRegistry.firstOrNull { it.canHandle(requestObject) }
    return when {
        command != null -> {
            command.execute(requestObject)
        }

        else -> {
            ResponseObject.ofUnclearCommand(requestObject.state?.session)
        }
    }
}
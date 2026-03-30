package org.gorych.alice.skill.fairytail

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.command.*
import org.gorych.alice.skill.core.quiz.QuizHolder
import org.gorych.alice.skill.fairytail.command.*
import org.gorych.alice.skill.fairytail.quiz.*
import tools.jackson.module.kotlin.jacksonObjectMapper
import tools.jackson.module.kotlin.readValue
import java.lang.System.err

//The order is important here
private val commandRegistry: List<Command> = listOf(
    PingCommand(),
    HelpCommand(),
    WhatCanYouDoCommand(),

    IntroductionCommand(),

    GreetingCommand(),
    PartingCommand(),
    GratitudeCommand(),
    StopCommand(),
    RateCommand(),

    PlayingAgreementCommand(),
    PlayingDisagreementCommand(),

    HintCommand(),
    RepeatQuestionCommand(),
    SkipQuestionCommand(),
    NextQuestionCommand(),
)

private val quizHolder = QuizHolder(
    listOf(
        Quiz1(),
        Quiz2(),
        Quiz3(),

        BonusQuiz1(),
        BonusQuiz2(),
    )
)

fun handle(input: String): String {
    val mapper = jacksonObjectMapper()

    var requestObject = RequestObject()
    var response: String
    try {
        requestObject = mapper.readValue(input)

        val responseObject = processRequest(requestObject)
        response = mapper.writeValueAsString(responseObject)

        return response
    } catch (t: Throwable) {
        err.println("Error while request processing. Exception= $t")
        response = mapper.writeValueAsString(
            ResponseObject.ofTechnicalError(requestObject)
        )
    }

    return response
}

private fun processRequest(requestObject: RequestObject): ResponseObject {
    val command: Command? = commandRegistry
        .map { wrapIfLoggingNotDisabled(it) }
        .firstOrNull { it.canHandle(requestObject) }
    return when {
        command != null -> {
            val quiz = quizHolder.getQuiz(requestObject)
            return command.execute(requestObject, quiz)
        }

        else -> {
            ResponseObject.ofUnclearCommand(requestObject)
        }
    }
}

private fun wrapIfLoggingNotDisabled(command: Command): Command {
    val loggingDisabled: Boolean = System.getenv("loggingDisabled").toBoolean()
    return when {
        loggingDisabled -> command
        else -> LoggedCommand(command)
    }
}
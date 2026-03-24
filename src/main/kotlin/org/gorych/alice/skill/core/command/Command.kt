package org.gorych.alice.skill.core.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.quiz.Quiz

interface Command {

    fun name(): String

    fun canHandle(requestObject: RequestObject): Boolean

    fun execute(requestObject: RequestObject, quiz: Quiz): ResponseObject

    fun log(message: String) = println("${name()}: $message")
}
package org.gorych.alice.skill.core.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject
import org.gorych.alice.skill.core.quiz.Quiz

private const val TRUNCATED_USER_ID_LENGTH = 8

class LoggedCommand(private val innerCommand: Command) : Command {
    override fun name() = innerCommand.name()

    override fun canHandle(requestObject: RequestObject) = innerCommand.canHandle(requestObject)

    override fun execute(requestObject: RequestObject, quiz: Quiz): ResponseObject {
        log("execute: start. User id: ${truncatedUserId(requestObject)}")
        return innerCommand.execute(requestObject, quiz)
    }

    private fun truncatedUserId(requestObject: RequestObject): String? {
        val userId = requestObject.session?.userId
        return when {
            userId == null -> null
            userId.length < TRUNCATED_USER_ID_LENGTH -> userId
            else -> userId.substring(0, TRUNCATED_USER_ID_LENGTH)
        }
    }
}
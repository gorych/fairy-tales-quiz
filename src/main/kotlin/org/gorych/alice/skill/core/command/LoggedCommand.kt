package org.gorych.alice.skill.core.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject

class LoggedCommand(private val innerCommand: Command) : Command {
    override fun name() = innerCommand.name()

    override fun canHandle(requestObject: RequestObject) = innerCommand.canHandle(requestObject)

    override fun execute(requestObject: RequestObject): ResponseObject {
        log("execute: start. User id: ${truncatedUserId(requestObject)}")
        return innerCommand.execute(requestObject)
    }

    private fun truncatedUserId(requestObject: RequestObject): String? {
        val userId = requestObject.session?.userId
        return when {
            userId == null -> null
            userId.length < 8 -> userId
            else -> userId.substring(0, 8)
        }
    }
}
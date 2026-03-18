package org.gorych.alice.skill.core.command

import org.gorych.alice.skill.core.api.RequestObject
import org.gorych.alice.skill.core.api.ResponseObject

class LoggedCommand(private val innerCommand: Command) : Command {
    override fun name() = innerCommand.name()

    override fun canHandle(requestObject: RequestObject) = innerCommand.canHandle(requestObject)

    override fun execute(requestObject: RequestObject): ResponseObject {
        log("execute: start")
        return innerCommand.execute(requestObject)
    }
}